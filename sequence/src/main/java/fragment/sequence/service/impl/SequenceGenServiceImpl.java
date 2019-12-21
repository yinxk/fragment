package fragment.sequence.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fragment.sequence.dao.SequenceDao;
import fragment.sequence.exception.SequenceException;
import fragment.sequence.exception.SequenceNotFoundException;
import fragment.sequence.exception.SequenceOverflowException;
import fragment.sequence.model.Segment;
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
    private final static Map<String, Segment> cache = new ConcurrentHashMap<>();

    /**
     * 步长
     */
    private final static BigInteger step = BigInteger.ONE;

    private final static BigInteger minSegmentSize = new BigInteger("100");
    private final static BigInteger maxSegmentSize = new BigInteger("100000");


    /**
     * 初始化, 暂时先不考虑定时从数据库加载新加的序列
     */
    private void init() {
        List<SequenceModel> sequenceModelList = sequenceDao.findAll();
        for (SequenceModel model : sequenceModelList) {
            Segment segment = new Segment(BigInteger.ZERO, BigInteger.ZERO);
            segment.setReady(false);
            cache.put(model.getSequenceName(), segment);
        }
    }

    /**
     * 从数据库更新号段
     *
     * @param sequenceName 序列名
     * @param segment      号段对象
     */
    private void updateSegmentFromDb(String sequenceName, Segment segment) {
        SequenceModel model = sequenceDao.findBySequenceName(sequenceName);
        if (model == null || model.getSequenceName() == null) {
            throw new SequenceNotFoundException();
        }
        BigInteger lastValue = model.getLastNumber();
        BigInteger oldValue = lastValue;
        if (lastValue.compareTo(model.getMaxValue()) == 0) {
            if (model.isCycleFlag()) {
                lastValue = model.getMinValue().subtract(step);
            } else {
                throw new SequenceOverflowException();
            }
        }
        BigInteger segmentMaxValue = lastValue.add(model.getSegmentSize());

        if (segmentMaxValue.compareTo(model.getMaxValue()) > 0) {
            segmentMaxValue = model.getMaxValue();
        }
        int updateCount = sequenceDao.updateSequenceByNameAndOldValue(sequenceName, oldValue, segmentMaxValue);
        if (updateCount == 1) {
            segment.setOldValue(lastValue);
            segment.setSegmentMaxValue(segmentMaxValue);
            segment.setReady(true);
        }
    }

    /**
     * 从号段获取下一个值
     *
     * @param segment 号段
     * @return 下一个值
     */
    private BigInteger nextValFromSegment(Segment segment) {
        BigInteger newValue = segment.getOldValue().add(step);
        if (newValue.compareTo(segment.getSegmentMaxValue()) == 0) {
            segment.setReady(false);
        }
        segment.setOldValue(newValue);
        return newValue;
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
            Segment segment = cache.get(sequenceName);
            if (!segment.isReady()) {
                synchronized (segment) {
                    if (!segment.isReady()) {
                        try {
                            updateSegmentFromDb(sequenceName, segment);
                            logger.info("更新数据完成, {}, {}", sequenceName, segment);
                        } catch (Exception e) {
                            logger.warn("从数据库更新号段出错 ", e);
                        }
                    }
                }
            }
            synchronized (segment) {
                return nextValFromSegment(segment);
            }
        }
        throw new SequenceNotFoundException();
    }


}
