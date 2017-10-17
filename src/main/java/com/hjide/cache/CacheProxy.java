package com.hjide.cache;

import com.hjide.cache.proxy.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * author: yanghua.yi
 * date: 2015/1/9
 * time: 17:07
 */
public interface CacheProxy extends
    StringCacheProxy, HashCacheProxy, ListCacheProxy, ObjectCacheProxy, SetCacheProxy, ZSortCacheProxy
{

    public boolean exists(String key);

    public String type(String key);

    public String type(byte[] key);

    public Long del(String key);

    public long ttl(String key);

    public boolean expire(String key, int expiredTime, TimeUnit unit);

    public boolean expireAt(String key, Date date);

}
