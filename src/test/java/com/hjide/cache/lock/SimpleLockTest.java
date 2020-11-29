package com.hjide.cache.lock;

import com.hjide.cache.CacheProxy;
import com.hjide.lock.SimpleConcurrentLock;
import com.hjide.lock.exception.ConcurrentLockException;
import com.hjide.lock.redis.RedisAcquireLock;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuxianjun on 2016/1/22.
 */
public class SimpleLockTest extends BaseTest{


    @BeforeClass
    public static void setUp() throws Exception {
        setUp(new String[]{"spring-config-lock.xml"});
    }


    SimpleConcurrentLock<String> redisConcurrentLock;

    public CacheProxy testCache;

    SimpleConcurrentLock<String> cacheLock;


    @Test
    public void testLock() throws ConcurrentLockException {
        System.out.println(cacheLock);
        System.out.println(redisConcurrentLock);
        if (redisConcurrentLock.getAcquireLock() instanceof RedisAcquireLock) {
            RedisAcquireLock concurrentLock = (RedisAcquireLock) redisConcurrentLock.getAcquireLock();
            testCache = concurrentLock.getCacheProxy();
        }

        final TestLockHandler lockHandler = new TestLockHandler();
        System.out.println("测试前redis数据:"+testCache.get(lockHandler.getLockKey()));
        System.out.println("测试前redis数据失效时间："+testCache.ttl(lockHandler.getLockKey()));
//        System.out.println(redisConcurrentLock.lockHandle(lockHandler));

        redisConcurrentLock.lockHandle(lockHandler, 1, TimeUnit.SECONDS);



        redisConcurrentLock.lockHandle(lockHandler);


    }

    public void setRedisConcurrentLock(SimpleConcurrentLock<String> redisConcurrentLock) {
        this.redisConcurrentLock = redisConcurrentLock;
    }

    public void setTestCache(CacheProxy testCache) {
        this.testCache = testCache;
    }

    public void setCacheLock(SimpleConcurrentLock<String> cacheLock) {
        this.cacheLock = cacheLock;
    }
}
