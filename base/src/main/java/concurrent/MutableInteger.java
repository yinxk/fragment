package concurrent;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程不安全
 */
@Slf4j
public class MutableInteger {
    private int value;

    public int getValue() {
        log.info("{} <== {}", Thread.currentThread().getName(), value);
        return value;
    }

    public void setValue(int value) {
        log.info("{} ==> {}", Thread.currentThread().getName(), value);
        this.value = value;
    }

    public static void main(String[] args) {
        MutableInteger mutableInteger = new MutableInteger();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    try {
                        Thread.sleep(115);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mutableInteger.setValue(new Random().nextInt());
                }
            }).start();
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    try {
                        Thread.sleep(115);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mutableInteger.getValue();
                }
            }).start();
        }
    }
}
