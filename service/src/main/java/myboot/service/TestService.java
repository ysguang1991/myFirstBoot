package myboot.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class TestService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test")
    public String test() {
        redisTemplate.opsForValue().set("test", "ysg");

        return redisTemplate.opsForValue().get("test");
    }

    public static void main(String[] args) {



        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        Semaphore semaphore = new Semaphore(3);
        AtomicInteger test = new AtomicInteger();
        for (int i = test.get(); i < 100; i++) {
            singleThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        if(test.get()%3==0){
                           // Thread.sleep(1000);
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
        singleThreadPool.shutdown();
    }
}
