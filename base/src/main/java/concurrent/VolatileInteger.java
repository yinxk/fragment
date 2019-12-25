package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程安全
 */
@Slf4j
public class VolatileInteger {
    private volatile int value;

    public int getValue() {
        log.info("{} <== {}", Thread.currentThread().getName(), value);
        return value;
    }

    public synchronized void add() {
        this.value = value + 1;
        log.info("{} ==> {}", Thread.currentThread().getName(), value);
    }

    public static void main(String[] args) {
        int threadNumber = 20;
        int runTimes = 200_0000;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        VolatileInteger mutableInteger = new VolatileInteger();
        for (int i = 0; i < runTimes; i++) {
            executorService.submit(mutableInteger::add);
        }
        executorService.shutdown();
        while (true) {
            if (!executorService.isTerminated()) {
                continue;
            }
            mutableInteger.getValue();
            break;
        }
    }
}
