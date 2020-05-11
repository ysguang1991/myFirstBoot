package myboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @GetMapping("/test")
    public String  test(){
        return "111";
    }
}
