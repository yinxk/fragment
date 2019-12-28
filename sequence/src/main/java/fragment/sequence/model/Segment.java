package fragment.sequence.model;

import java.math.BigInteger;

import fragment.sequence.exception.SequenceOutOfBoundsException;

public class Segment {
    private final static BigInteger STEP = BigInteger.ONE;
    private final static BigInteger MIN_SEGMENT_SIZE = new BigInteger("100");
    private final static BigInteger MAX_SEGMENT_SIZE = new BigInteger("600000");
    private final static BigInteger DIVISOR = new BigInteger("2");
    private static final long SEGMENT_DURATION = 20 * 60 * 1000L;
    private final static BigInteger LOAD_FACTOR = new BigInteger("2");
    private volatile BigInteger lastValue = BigInteger.ZERO;
    private volatile BigInteger segmentSize = BigInteger.ZERO;
    private volatile BigInteger segmentMaxValue = BigInteger.ZERO;
    private volatile BigInteger idleNumber;
    private final SegmentBuffer buffer;

    public Segment(SegmentBuffer buffer) {
        this.buffer = buffer;
    }

    public void update(SequenceModel model) {
        BigInteger maxValue = model.getMaxValue();
        this.lastValue = model.getLastNumber();
        if (this.lastValue.compareTo(maxValue) == 0) {
            if (model.isCycleFlag()) {
                lastValue = model.getMinValue().subtract(STEP);
            } else {
                throw new SequenceOutOfBoundsException(model.getSequenceName());
            }
        }
        BigInteger dbSize = model.getSegmentSize();
        if (dbSize.compareTo(maxValue) > 0) {
            dbSize = maxValue;
        }
        if (dbSize.compareTo(MIN_SEGMENT_SIZE) < 0) {
            dbSize = MIN_SEGMENT_SIZE;
        }
        if (dbSize.compareTo(MAX_SEGMENT_SIZE) > 0) {
            dbSize = MAX_SEGMENT_SIZE;
        }
        this.buffer.setDbSize(dbSize);
        if (model.isDynamicSize()) {
            long duration = System.currentTimeMillis() - buffer.getUpdateTimestamp();
            BigInteger nextSize = buffer.getSize();
            if (buffer.notInitOk() || buffer.getUpdateTimestamp() == 0) {
                nextSize = buffer.getDbSize();
            } else if (duration < SEGMENT_DURATION) {
                if (nextSize.multiply(LOAD_FACTOR).compareTo(MAX_SEGMENT_SIZE) <= 0) {
                    nextSize = nextSize.multiply(LOAD_FACTOR);
                }
            } else {
                nextSize = nextSize.divide(LOAD_FACTOR);
                if (nextSize.compareTo(buffer.getDbSize()) < 0) {
                    nextSize = buffer.getDbSize();
                }
            }
            this.buffer.setSize(nextSize);
            this.buffer.setUpdateTimestamp(System.currentTimeMillis());
        } else {
            this.buffer.setSize(dbSize);
        }

        segmentMaxValue = lastValue.add(this.buffer.getSize());
        if (segmentMaxValue.compareTo(maxValue) > 0) {
            segmentMaxValue = maxValue;
        }
        this.segmentSize = this.buffer.getSize();
        idleNumber = segmentSize.divide(DIVISOR);
    }

    public synchronized BigInteger getNextValueAndUpdate() {
        lastValue = lastValue.add(STEP);
        return lastValue;
    }

    public BigInteger getSegmentSize() {
        return segmentSize;
    }

    public BigInteger getSegmentMaxValue() {
        return segmentMaxValue;
    }

    public BigInteger getLastValue() {
        return lastValue;
    }

    public boolean isIdleLessThanIdleNumber() {
        return segmentMaxValue.subtract(lastValue).compareTo(idleNumber) <= 0;
    }

    public SegmentBuffer getBuffer() {
        return buffer;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "lastValue=" + lastValue +
                ", segmentSize=" + segmentSize +
                ", segmentMaxValue=" + segmentMaxValue +
                ", idleNumber=" + idleNumber +
                '}';
    }
}
