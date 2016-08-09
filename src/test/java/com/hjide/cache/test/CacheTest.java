package com.hjide.cache.test;

import com.hjide.cache.CacheProxy;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by jock on 15/12/24.
 */
public class CacheTest extends BaseTest {

    @Resource(name = "mixCache")
    private CacheProxy cacheProxy;

    @Test
    public void getCache() {
        cacheProxy.set("test", "testvalue");
        cacheProxy.sadd("key","values");
        cacheProxy.scard("key");
        cacheProxy.spop("key");
        Assert.assertEquals("testvalue", cacheProxy.get("test"));
        System.out.println(cacheProxy.get("test"));
    }


    @Test
    public void setNxTest() {
        cacheProxy.del("a");
        Assert.assertTrue(cacheProxy.setnx("a", TimeUnit.SECONDS,1,"a"));
        Assert.assertFalse(cacheProxy.setnx("a", TimeUnit.SECONDS,1,"a"));
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(cacheProxy.setnx("a", TimeUnit.SECONDS,1,"a"));
        cacheProxy.del("a");

        System.out.println(cacheProxy.setnx("a", TimeUnit.SECONDS,1,"a"));
        System.out.println(cacheProxy.setnx("a", TimeUnit.SECONDS,1,"a"));
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(cacheProxy.setnx("a", TimeUnit.SECONDS,1,"a"));
        System.out.println(cacheProxy.get("a"));
    }


}
