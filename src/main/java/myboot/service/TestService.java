package myboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TestService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test")
    public String test() {
        redisTemplate.opsForValue().set("test", "ysg");

        return redisTemplate.opsForValue().get("test");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Semaphore semaphore = new Semaphore(3);
        AtomicInteger test = new AtomicInteger();
        for (int i = test.get(); i < 100; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        if(test.get()%3==0){
                            Thread.sleep(1000);
                        }
                        System.out.println("第" + test.getAndAdd(1) + "次调用");
                    } catch (InterruptedException t) {
                        System.out.println("第" + test.get() + "次调用失败");
                    } finally {
                        semaphore.release();
                    }
                }
            });
        }
    }
}
