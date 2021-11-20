package chip.date20211120.third;

/**
 * 模拟分布式ID
 */
public class DistributedIdService {

    public Long nextId() {
        return System.nanoTime();
    }
}
