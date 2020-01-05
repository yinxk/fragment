package fragment.sequence.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import fragment.sequence.dao.SequenceDao;
import fragment.sequence.exception.*;
import fragment.sequence.model.Segment;
import fragment.sequence.model.SegmentBuffer;
import fragment.sequence.model.SequenceModel;
import fragment.sequence.service.SequenceGenService;


@Service
public class SequenceGenServiceImpl implements SequenceGenService {

    private final static Logger logger = LoggerFactory.getLogger(SequenceGenServiceImpl.class);

    @Autowired
    private SequenceDao sequenceDao;
    private final static long SEQUENCE_TIME_OUT_PERIOD = 3 * 1000L;
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

    private final static ExecutorService service =
            new ThreadPoolExecutor(2, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new UpdateThreadFactory());

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


    private void updateCacheFromDbAtEveryTenMin() {
        scheduledExecutor.scheduleWithFixedDelay(this::updateCacheFromDb, 10, 10, TimeUnit.MINUTES);
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
                SegmentBuffer buffer = new SegmentBuffer(sequenceName);
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

    @PostConstruct
    private void init() {
        updateCacheFromDb();
        updateCacheFromDbAtEveryTenMin();
    }

    @Override
    public BigInteger nextVal(String sequenceName) throws SequenceException {
        if (cache.containsKey(sequenceName)) {
            SegmentBuffer buffer = cache.get(sequenceName);
            if (buffer.notInitOk()) {
                synchronized (buffer) {
                    if (buffer.notInitOk()) {
                        Segment current = buffer.getCurrent();
                        try {
                            if (!updateSegmentFromDb(sequenceName, current)) {
                                throw new SequenceBufferInitException(sequenceName);
                            }
                            if (logger.isInfoEnabled()) {
                                logger.info("Init buffer. Update sequence {} {} from db", sequenceName, current);
                            }
                            buffer.setInitOk(true);
                        } catch (SequenceOutOfBoundsException e) {
                            throw e;
                        } catch (Exception e) {
                            throw new SequenceBufferInitException(sequenceName, e);
                        }
                    }
                }
            }
            return getNextValFromSegmentBuffer(cache.get(sequenceName));
        }
        throw new SequenceNotFoundException(sequenceName);
    }

    @Transactional
    public boolean updateSegmentFromDb(String sequenceName, Segment segment) {
        SequenceModel model = sequenceDao.findBySequenceName(sequenceName);
        if (model == null || model.getSequenceName() == null) {
            return false;
        }
        segment.update(model);
        return 1 == sequenceDao.updateSequenceLastNumberByNameAndNewValue(sequenceName, segment.getSegmentMaxValue());
    }

    public BigInteger getNextValFromSegmentBuffer(final SegmentBuffer buffer) {
        long start = System.currentTimeMillis();
        while (true) {
            buffer.rLock().lock();
            try {
                final Segment segment = buffer.getCurrent();
                if (buffer.isValueOutOfBounds()) {
                    throw new SequenceOutOfBoundsException(buffer.getSequenceName());
                }
                if (!buffer.isNextReady() && segment.isIdleLessThanIdleNumber() && buffer.getThreadRunning().compareAndSet(false, true)) {
                    service.execute(() -> {
                        Segment next = buffer.getSegments()[buffer.nextPos()];
                        boolean updateOk = false;
                        try {
                            updateOk = updateSegmentFromDb(buffer.getSequenceName(), next);
                            if (updateOk && logger.isInfoEnabled()) {
                                logger.info("update segment {} from db {}", buffer.getSequenceName(), next);
                            }
                        } catch (SequenceOutOfBoundsException e) {
                            buffer.setValueOutOfBounds(true);
                            if (logger.isWarnEnabled()) {
                                logger.warn("update segment, sequence out of bounds ", e);
                            }
                        } catch (Exception e) {
                            if (logger.isWarnEnabled()) {
                                logger.warn(buffer.getSequenceName() + " updateSegmentFromDb exception", e);
                            }
                        } finally {
                            buffer.wLock().lock();
                            buffer.getThreadRunning().set(false);
                            if (updateOk) {
                                buffer.setNextReady(true);
                            }
                            buffer.wLock().unlock();
                        }
                    });
                }
                synchronized (segment) {
                    if (segment.getLastValue().compareTo(segment.getSegmentMaxValue()) < 0) {
                        return segment.getNextValueAndUpdate();
                    }
                }
                if (System.currentTimeMillis() - start > SEQUENCE_TIME_OUT_PERIOD) {
                    throw new SequenceTimeOutException(buffer.getSequenceName());
                }
            } finally {
                buffer.rLock().unlock();
            }
            waitAndSleep(buffer);
            buffer.wLock().lock();
            try {
                final Segment segment = buffer.getCurrent();
                synchronized (segment) {
                    if (segment.getLastValue().compareTo(segment.getSegmentMaxValue()) < 0) {
                        return segment.getNextValueAndUpdate();
                    }
                }
                if (buffer.isNextReady()) {
                    buffer.switchPos();
                    buffer.setNextReady(false);
                } else if (System.currentTimeMillis() - start > SEQUENCE_TIME_OUT_PERIOD) {
                    throw new SequenceTimeOutException(buffer.getSequenceName());
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
