package fragment.sequence.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import fragment.sequence.dao.SequenceDao;
import fragment.sequence.exception.SequenceException;
import fragment.sequence.exception.SequenceNotFoundException;
import fragment.sequence.model.Segment;
import fragment.sequence.model.SegmentBuffer;
import fragment.sequence.model.SequenceModel;
import fragment.sequence.service.SequenceGenService;


@Service
public class SequenceGenServiceImpl implements SequenceGenService {

    private final static Logger logger = LoggerFactory.getLogger(SequenceGenServiceImpl.class);

    @Autowired
    private SequenceDao sequenceDao;

    /**
     * 缓存
     */
    private final static Map<String, SegmentBuffer> cache = new ConcurrentHashMap<>();

    private final static ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r);
        t.setName("check-sequence-cache-thread");
        t.setDaemon(true);
        return t;
    });

    private ExecutorService service = new ThreadPoolExecutor(2, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new UpdateThreadFactory());

    public static class UpdateThreadFactory implements ThreadFactory {

        private static int threadInitNumber = 0;

        private static synchronized int nextThreadNum() {
            return threadInitNumber++;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "Thread-Segment-Update-" + nextThreadNum());
        }
    }


    private void updateCacheFromDbAtEveryMinute() {
        scheduledExecutor.scheduleWithFixedDelay(this::updateCacheFromDb, 60, 60, TimeUnit.SECONDS);
    }

    private void updateCacheFromDb() {
        if (logger.isInfoEnabled()) {
            logger.info("update cache from db");
        }
        try {
            List<String> dbSequenceNames = sequenceDao.findAllSequenceName();
            if (CollectionUtils.isEmpty(dbSequenceNames)) {
                return;
            }
            List<String> cachedSequenceNames = new ArrayList<>(cache.keySet());
            List<String> insertSequenceNames = new ArrayList<>(dbSequenceNames);
            List<String> removeSequenceNames = new ArrayList<>(cachedSequenceNames);
            // 将数据库新加入的sequenceName添加入缓存
            insertSequenceNames.removeAll(cachedSequenceNames);
            for (String sequenceName : insertSequenceNames) {
                SegmentBuffer buffer = new SegmentBuffer();
                buffer.setSequenceName(sequenceName);
                cache.put(sequenceName, buffer);
                if (logger.isInfoEnabled()) {
                    logger.info("Add sequenceName {} from db to cache, SegmentBuffer {}", sequenceName, buffer);
                }
            }
            // 将数据库中不存在的sequenceName从缓存中删除
            removeSequenceNames.removeAll(dbSequenceNames);
            for (String sequenceName : removeSequenceNames) {
                cache.remove(sequenceName);
                if (logger.isInfoEnabled()) {
                    logger.info("Remove sequenceName {} from cache", sequenceName);
                }
            }
        } catch (Exception e) {
            if (logger.isWarnEnabled()) {
                logger.warn("update cache from db exception", e);
            }
        }
    }

    private void init() {
        updateCacheFromDb();
        // updateCacheFromDbAtEveryMinute();
    }

    @Override
    public BigInteger nextVal(String sequenceName) throws SequenceException {
        if (cache.size() == 0) {
            synchronized (cache) {
                if (cache.size() == 0) {
                    init();
                }
            }
        }
        if (cache.containsKey(sequenceName)) {
            SegmentBuffer buffer = cache.get(sequenceName);
            if (buffer.isNotInitOk()) {
                synchronized (buffer) {
                    if (buffer.isNotInitOk()) {
                        Segment current = buffer.getCurrent();
                        try {
                            updateSegmentFromDb(sequenceName, current);
                            if (logger.isInfoEnabled()) {
                                logger.info("Init buffer. Update sequence {} {} from db", sequenceName, current);
                            }
                            buffer.setInitOk(true);
                        } catch (Exception e) {
                            if (logger.isWarnEnabled()) {
                                logger.warn("Init buffer {} exception", current, e);
                            }
                        }
                    }
                }
            }
            synchronized (buffer) {
                return getNextValFromSegmentBuffer(cache.get(sequenceName));
            }
        }
        throw new SequenceNotFoundException();
    }

    @Transactional
    public void updateSegmentFromDb(String sequenceName, Segment segment) {
        SequenceModel model = sequenceDao.findBySequenceName(sequenceName);
        if (model == null || model.getSequenceName() == null) {
            throw new SequenceNotFoundException();
        }
        segment.toSegment(model);
        sequenceDao.updateSequenceLastNumberByName(sequenceName, segment.getSegmentSize());
    }

    public BigInteger getNextValFromSegmentBuffer(final SegmentBuffer buffer) {
        while (true) {
            buffer.rLock().lock();
            try {
                final Segment segment = buffer.getCurrent();
                if (!buffer.isNextReady() && segment.isIdleLessThanIdleNumber() && buffer.getThreadRunning().compareAndSet(false, true)) {
                    service.execute(() -> {
                        Segment next = buffer.getSegments()[buffer.nextPos()];
                        boolean updateOk = false;
                        try {
                            buffer.wLock().lock();
                            updateSegmentFromDb(buffer.getSequenceName(), next);
                            updateOk = true;
                            if (logger.isInfoEnabled()) {
                                logger.info("update segment {} from db {}", buffer.getSequenceName(), next);
                            }
                        } catch (Exception e) {
                            if (logger.isWarnEnabled()) {
                                logger.warn(buffer.getSequenceName() + " updateSegmentFromDb exception", e);
                            }
                        } finally {
                            buffer.getThreadRunning().set(false);
                            if (updateOk) {
                                buffer.setNextReady(true);
                            }
                            buffer.wLock().unlock();
                        }
                    });
                }
                BigInteger value = segment.getNextValueAndUpdate();
                if (value.compareTo(segment.getSegmentMaxValue()) <= 0) {
                    return value;
                }
            } finally {
                buffer.rLock().unlock();
            }
            waitAndSleep(buffer);
            buffer.wLock().lock();
            try {
                final Segment segment = buffer.getCurrent();
                BigInteger value = segment.getNextValueAndUpdate();
                if (value.compareTo(segment.getSegmentMaxValue()) <= 0) {
                    return value;
                }
                if (buffer.isNextReady()) {
                    buffer.switchPos();
                    buffer.setNextReady(false);
                }
            } finally {
                buffer.wLock().unlock();
            }
        }
    }

    private void waitAndSleep(SegmentBuffer buffer) {
        int roll = 0;
        while (buffer.getThreadRunning().get()) {
            roll += 1;
            if (roll > 10000) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    break;
                } catch (InterruptedException e) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("Thread {} Interrupted", Thread.currentThread().getName());
                    }
                    break;
                }
            }
        }
    }


}
