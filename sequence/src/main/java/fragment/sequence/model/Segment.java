package fragment.sequence.model;

import java.math.BigInteger;

public class Segment {
    private BigInteger oldValue;

    private BigInteger segmentMaxValue;

    private volatile boolean ready;

    public Segment(BigInteger oldValue, BigInteger segmentMaxValue) {
        this.oldValue = oldValue;
        this.segmentMaxValue = segmentMaxValue;
    }

    public BigInteger getOldValue() {
        return oldValue;
    }

    public void setOldValue(BigInteger oldValue) {
        this.oldValue = oldValue;
    }

    public BigInteger getSegmentMaxValue() {
        return segmentMaxValue;
    }

    public void setSegmentMaxValue(BigInteger segmentMaxValue) {
        this.segmentMaxValue = segmentMaxValue;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "oldValue=" + oldValue +
                ", segmentMaxValue=" + segmentMaxValue +
                ", ready=" + ready +
                '}';
    }
}
