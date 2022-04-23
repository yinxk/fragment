package top.yinxiaokang.jedis.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {

    private static final JedisPool JEDIS_POOL;

    static {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(7);
        config.setMaxIdle(7);
        config.setMinIdle(5);
        config.setMaxWaitMillis(1000);
        JEDIS_POOL = new JedisPool(config, "192.168.102.105");
    }

    public static Jedis getJedis() {
        return JEDIS_POOL.getResource();
    }
}
