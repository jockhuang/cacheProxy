package com.hjide.cache.test;

import com.hjide.cache.CacheProxy;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by jock on 15/12/24.
 */
public class LFCacheTest extends BaseTest{

    @Resource(name = "testCache")
    private CacheProxy cacheProxy;

    @Test
    public void getCache() {
        cacheProxy.set("test", "testvalue");
        Assert.assertEquals("testvalue", cacheProxy.get("test"));
        System.out.println(cacheProxy.get("test"));
    }
}
