package com.hjide.cache.test;

import com.hjide.cache.CacheProxy;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by jock on 15/12/31.
 */
public class MultiCacheTest extends BaseTest {
    @Resource(name = "multiCache")
    private CacheProxy multiCache;

    @Resource
    private CacheProxy mixCache;
    @Resource
    private CacheProxy testCache;

    @Test
    public void getCache() {
        multiCache.set("test", "testvalue");
        Assert.assertEquals("testvalue", multiCache.get("test"));
        System.out.println("==========");
        System.out.println(mixCache.get("test"));
        System.out.println(testCache.get("test"));
        Assert.assertEquals("testvalue", mixCache.get("test"));
        Assert.assertEquals("testvalue", testCache.get("test"));
        multiCache.del("test");
        System.out.println("====del===");
        System.out.println(mixCache.get("test"));
        System.out.println(testCache.get("test"));
        Assert.assertNull(mixCache.get("test"));
        Assert.assertNull(testCache.get("test"));

    }
}
