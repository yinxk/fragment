package fragment.redis;

import com.alibaba.fastjson.JSON;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.TypeReference;
import fragment.redis.config.MockConfig;
import fragment.redis.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = FragmentRedisApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final static String KEY = "test:list:key1";

    @Test
    public void test1() {


        List<UserDto> userDtoList = JMockData.mock(new TypeReference<List<UserDto>>() {
        }, MockConfig.getMockConfig());


        List<Object> objects = redisTemplate.executePipelined((RedisCallback<Long>) redisConnection -> {
            for (UserDto userDto : userDtoList) {
                redisConnection.lPush(KEY.getBytes(), JSON.toJSONString(userDto).getBytes());
            }
            return null;
        });
        System.out.println(objects);
    }

    @Test
    public void test2() {
        List<String> range = redisTemplate.opsForList().range(KEY, -100, 1000);
        for (String s : range) {

            UserDto userDto = JSON.parseObject(s, UserDto.class);
            System.out.println(userDto);
        }
    }
}
