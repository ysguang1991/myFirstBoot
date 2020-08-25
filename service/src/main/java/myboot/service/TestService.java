package myboot.service;

//import com.google.common.util.concurrent.ThreadFactoryBuilder;

import myboot.config.DataSouceConfig;
import myboot.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.ThreadPool;

import javax.annotation.Resource;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


@RestController
public class TestService {
    @Autowired
    private DataSouceConfig config;

//    @Autowired
//    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test")
    public String test() {
        return String.valueOf(config.maxActive);
//        Logger.getGlobal().info("111");
//        redisTemplate.opsForValue().set("test", "ysg");
//
//        return redisTemplate.opsForValue().get("test");
    }

//    @Resource
//    TestMapper testMapper;
//
//    @GetMapping("/test_insert")
//    public Integer testInsert() {
//
//        int ysg = testMapper.insert("ysg", 18);
//        return ysg;
//    }

    public static void main(String[] args) {

//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("demo-pool-%d").build();
//        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//
//        Semaphore semaphore = new Semaphore(3);
//        AtomicInteger test = new AtomicInteger();
//        for (int i = test.get(); i < 100; i++) {
//            singleThreadPool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        semaphore.acquire();
//                        if(test.get()%3==0){
//                           // Thread.sleep(1000);
//                        }
//                        System.out.println("第" + test.getAndAdd(1) + "次调用");
//                    } catch (InterruptedException t) {
//                        System.out.println("第" + test.get() + "次调用失败");
//                    } finally {
//                        semaphore.release();
//                    }
//                }
//            });
//        }
//        singleThreadPool.shutdown();

        ExecutorService executors = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        AtomicInteger test = new AtomicInteger();
        for (int i = test.get(); i < 100; i++) {
            executors.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第" + test.getAndAdd(1) + "次调用");
                    countDownLatch.countDown();
                }
            });
        }
        System.out.println("等待中!");
        try {
            countDownLatch.await();
        } catch (InterruptedException t) {
            System.out.println("线程终止失败!");
            executors.shutdown();
        }
        System.out.println("释放线程池!");
        executors.shutdown();
    }
}
