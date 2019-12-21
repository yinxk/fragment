package fragment.sequence.model;

import java.io.Serializable;
import java.math.BigInteger;

public class SequenceModel implements Serializable {

    private static final long serialVersionUID = -4303310849153133741L;

    private String sequenceName;
    private BigInteger minValue;
    private BigInteger maxValue;
    private boolean cycleFlag;
    private BigInteger lastNumber;
    private BigInteger segmentSize;

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public BigInteger getMinValue() {
        return minValue;
    }

    public void setMinValue(BigInteger minValue) {
        this.minValue = minValue;
    }

    public BigInteger getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigInteger maxValue) {
        this.maxValue = maxValue;
    }

    public boolean isCycleFlag() {
        return cycleFlag;
    }

    public void setCycleFlag(boolean cycleFlag) {
        this.cycleFlag = cycleFlag;
    }

    public BigInteger getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(BigInteger lastNumber) {
        this.lastNumber = lastNumber;
    }

    public BigInteger getSegmentSize() {
        return segmentSize;
    }

    public void setSegmentSize(BigInteger segmentSize) {
        this.segmentSize = segmentSize;
    }

}
