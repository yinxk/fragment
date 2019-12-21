package fragment.sequence.model;

import java.math.BigInteger;

import fragment.sequence.exception.SequenceOverflowException;

public class Segment {
    private final static BigInteger step = BigInteger.ONE;

    private final static BigInteger MIN_SEGMENT_SIZE = new BigInteger("2");

    private final static BigInteger MAX_SEGMENT_SIZE = new BigInteger("9999");

    private BigInteger lastValue = BigInteger.ZERO;

    private BigInteger segmentSize = BigInteger.ZERO;

    private BigInteger segmentMaxValue = BigInteger.ZERO;

    private BigInteger idleNumber = MIN_SEGMENT_SIZE.divide(SegmentBuffer.getNextSegmentIdleDivisor());

    private final SegmentBuffer buffer;

    public Segment(SegmentBuffer buffer) {
        this.buffer = buffer;
    }

    public void toSegment(SequenceModel model) {
        BigInteger maxValue = model.getMaxValue();
        this.lastValue = model.getLastNumber();
        if (this.lastValue.compareTo(maxValue) == 0) {
            if (model.isCycleFlag()) {
                lastValue = model.getMinValue().subtract(step);
            } else {
                throw new SequenceOverflowException();
            }
        }
        this.segmentSize = model.getSegmentSize();
        if (segmentSize.compareTo(MIN_SEGMENT_SIZE) < 0) {
            segmentSize = MIN_SEGMENT_SIZE;
        } else if (segmentSize.compareTo(MAX_SEGMENT_SIZE) > 0) {
            segmentSize = MAX_SEGMENT_SIZE;
        }
        segmentMaxValue = lastValue.add(segmentSize);
        if (segmentMaxValue.compareTo(maxValue) > 0) {
            segmentMaxValue = maxValue;
        }
        idleNumber = segmentSize.divide(SegmentBuffer.getNextSegmentIdleDivisor());
    }

    public BigInteger getNextValueAndUpdate() {
        lastValue = lastValue.add(step);
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
                '}';
    }
}
