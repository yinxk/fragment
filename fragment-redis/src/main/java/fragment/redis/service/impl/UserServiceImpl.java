package fragment.redis.service.impl;

import fragment.redis.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Map<String, Object> getInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "yxk");
        map.put("sex", 1);
        map.put("email", "jdilwi@xx.com");

        return map;
    }
}
