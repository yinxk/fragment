package top.yinxiaokang.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import top.yinxiaokang.jedis.config.JedisConnectionFactory;

public class JedisTest {

    private Jedis jedis;


    @Before
    public void create() {
        // jedis = new Jedis("192.168.102.105", 6379);
        jedis = JedisConnectionFactory.getJedis();
        jedis.select(0);
        System.out.println(">>>>>>>>>");
        System.out.println(jedis.keys("*"));
    }

    @After
    public void keys() {
        System.out.println(jedis.keys("*"));
        System.out.println("<<<<<<<<");
        jedis.close();
    }


    @Test
    public void test1() {

        System.out.println(jedis.set("test", "testval"));
    }
    @Test
    public void test2() {

        System.out.println(jedis.mset("test", "testval", "m1", "v1", "m2", "v2"));
    }

}
