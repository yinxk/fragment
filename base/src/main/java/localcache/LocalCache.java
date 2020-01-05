package localcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocalCache<K, V> {
    private static final int DEFAULT_TIMEOUT = 120;

    private Map<K, V> cacheMap;

    private ScheduledExecutorService service;


    public LocalCache() {
        this(6);
    }

    public LocalCache(int corePoolSize) {
        cacheMap = new ConcurrentHashMap<>();
        service = Executors.newScheduledThreadPool(corePoolSize);
    }

    /**
     * 增加缓存
     */
    public void put(K key, V value) {
        put(key, value, DEFAULT_TIMEOUT);
    }

    /**
     * 增加缓存
     */
    public void put(K key, V value, long delaySeconds) {
        cacheMap.put(key, value);
        service.schedule(new CleanWorkerTask(key), delaySeconds, TimeUnit.SECONDS);
    }


    /**
     * 获取缓存
     */
    public V get(K key) {
        return cacheMap.get(key);
    }

    /**
     * 查询缓存是否包含key
     */
    public boolean containsKey(K key) {
        return cacheMap.containsKey(key);
    }

    /**
     * 删除缓存
     */
    public void remove(K key) {
        cacheMap.remove(key);
        System.out.println("当前线程: " + Thread.currentThread().getName() + " ,容器大小: " + size() + ", 结束时间: " + System.currentTimeMillis());
    }


    /**
     * 返回缓存大小
     */
    public int size() {
        return cacheMap.size();
    }

    /**
     * 清除所有缓存
     */
    public void clear() {
        if (size() > 0) {
            cacheMap.clear();
        }
    }


    /**
     * 清除缓存任务类
     */
    class CleanWorkerTask implements Runnable {

        private K key;

        CleanWorkerTask(K key) {
            this.key = key;
        }

        @Override
        public void run() {
            remove(key);
        }
    }
}
