package com.hjide.cache.lock;

import com.hjide.cache.CacheProxy;
import com.hjide.lock.redis.RedisAcquireLock;
import com.hjide.lock.ConcurrentLock;
import com.hjide.lock.SimpleConcurrentLock;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: xuxianjun
 * Date: 14-11-27
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
public class RedisConcurrentLockTest extends BaseTest {


    int threadNum = 30;

    @BeforeClass
    public static void setUp() throws Exception {
        //, "spring-config-redis.xml"
        setUp(new String[]{"spring-config-lock.xml"});
    }

    SimpleConcurrentLock<String> redisConcurrentLock;

    public CacheProxy testCache;

    @Test
    public void testLock() {
        System.out.println(redisConcurrentLock);
        if (redisConcurrentLock.getAcquireLock() instanceof RedisAcquireLock) {
            RedisAcquireLock concurrentLock = (RedisAcquireLock) redisConcurrentLock.getAcquireLock();
            testCache = concurrentLock.getCacheProxy();
        }
        final TestLockHandler lockHandler = new TestLockHandler();
        System.out.println("测试前redis数据:"+testCache.get(lockHandler.getLockKey()));
        System.out.println("测试前redis数据失效时间："+testCache.ttl(lockHandler.getLockKey()));
//        System.out.println(redisConcurrentLock.lockHandle(lockHandler));

        System.out.println("-----------------不等待执行开始!");
        concurrentLock(redisConcurrentLock,lockHandler,0);
        System.out.println("-----------------不等待执行结束!");


        System.out.println("******************等待执行开始!");
        concurrentLock(redisConcurrentLock,lockHandler,1000);

        System.out.println("******************等待执行结束!");

    }


    public void concurrentLock(ConcurrentLock<String, String> lock,TestLockHandler lockHandler,long timeOut){
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        CyclicBarrier start = new CyclicBarrier(threadNum);


        CountDownLatch end = new CountDownLatch(threadNum);
        AtomicInteger errorTime = new AtomicInteger();
        for (int j = 0; j < threadNum; j++) {
            executorService.submit(
                    new HandlerRunnable(start, end, errorTime, lockHandler,lock,timeOut)
            );
        }

        try {
            end.await();
        } catch (InterruptedException e) {

        }
        System.out.println("获取锁失败数：" + errorTime.get());
        System.out.println(DateFormatUtils.
                format(new Date(), "yyyy-MM-dd HH:mm:ss SSS")+"  获取锁失败数：" + errorTime.get());
    }

    class HandlerRunnable implements Runnable {

        CyclicBarrier start;
        CountDownLatch end;
        AtomicInteger errorTime;
        TestLockHandler lockHandler;
        ConcurrentLock<String, String> redisConcurrentLock;
        long timeOut;

        HandlerRunnable(CyclicBarrier start, CountDownLatch end,
                        AtomicInteger errorTime, TestLockHandler lockHandler,
                        ConcurrentLock<String, String> redisConcurrentLock,long timeOut
        ) {
            this.start = start;
            this.end = end;
            this.errorTime = errorTime;
            this.lockHandler = lockHandler;
            this.redisConcurrentLock = redisConcurrentLock;
            this.timeOut = timeOut;
        }



        public void run() {
            try {
                start.await();
                if(timeOut<0) {
                    System.out.println(DateFormatUtils.
                            format(new Date(), "yyyy-MM-dd HH:mm:ss SSS") + " " + redisConcurrentLock.lockHandle(lockHandler));
                }else {
                    System.out.println(DateFormatUtils.
                            format(new Date(), "yyyy-MM-dd HH:mm:ss SSS") + " " +
                            redisConcurrentLock.lockHandle(lockHandler,timeOut, TimeUnit.SECONDS));
                }
            } catch (Exception e) {
                errorTime.incrementAndGet();
            } finally {
//                        System.out.println(endHandler.incrementAndGet());
                end.countDown();
            }

        }
    }


    public void setRedisConcurrentLock(SimpleConcurrentLock<String> redisConcurrentLock) {
        this.redisConcurrentLock = redisConcurrentLock;
    }

    public void setTestCache(CacheProxy testCache) {
        this.testCache = testCache;
    }
}
