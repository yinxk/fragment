package fragment.sequence.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SegmentBuffer {
    private final String sequenceName;
    private final Segment[] segments;
    private volatile int currentPos;
    private volatile boolean nextReady;
    private volatile boolean initOk;
    private final AtomicBoolean threadRunning;
    private final ReadWriteLock lock;
    private volatile boolean valueOutOfBounds;
    private volatile BigInteger size;
    private volatile BigInteger dbSize;
    private volatile long updateTimestamp;

    public SegmentBuffer(String sequenceName) {
        segments = new Segment[]{new Segment(this), new Segment(this)};
        currentPos = 0;
        nextReady = false;
        initOk = false;
        threadRunning = new AtomicBoolean(false);
        lock = new ReentrantReadWriteLock();
        valueOutOfBounds = false;
        this.sequenceName = sequenceName;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public Segment[] getSegments() {
        return segments;
    }

    public Segment getCurrent() {
        return segments[currentPos];
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public int nextPos() {
        return (currentPos + 1) % 2;
    }

    public void switchPos() {
        currentPos = nextPos();
    }

    public boolean notInitOk() {
        return !initOk;
    }

    public void setInitOk(boolean initOk) {
        this.initOk = initOk;
    }

    public boolean isNextReady() {
        return nextReady;
    }

    public void setNextReady(boolean nextReady) {
        this.nextReady = nextReady;
    }

    public AtomicBoolean getThreadRunning() {
        return threadRunning;
    }

    public Lock rLock() {
        return lock.readLock();
    }

    public Lock wLock() {
        return lock.writeLock();
    }

    public boolean isValueOutOfBounds() {
        return valueOutOfBounds;
    }

    public void setValueOutOfBounds(boolean valueOutOfBounds) {
        this.valueOutOfBounds = valueOutOfBounds;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public BigInteger getDbSize() {
        return dbSize;
    }

    public void setDbSize(BigInteger dbSize) {
        this.dbSize = dbSize;
    }

    public long getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(long updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @Override
    public String toString() {
        return "SegmentBuffer{" +
                "sequenceName='" + sequenceName + '\'' +
                ", segments=" + Arrays.toString(segments) +
                ", currentPos=" + currentPos +
                ", nextReady=" + nextReady +
                ", initOk=" + initOk +
                ", threadRunning=" + threadRunning +
                ", valueOutOfBounds=" + valueOutOfBounds +
                ", size=" + size +
                ", dbSize=" + dbSize +
                ", updateTimestamp=" + updateTimestamp +
                '}';
    }
}
