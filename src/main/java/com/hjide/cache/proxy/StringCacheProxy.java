package com.hjide.cache.proxy;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public interface StringCacheProxy {

    //String相关方法
    public void set(String key, String value);

    public String get(String key);

    public boolean setex(String key, TimeUnit unit, int expiredTime, String value);

    public String getSet(String key, String value);

    public Long decrBy(String key, long step);

    public Long decr(String key);

    public Long incrBy(String key, long step);

    public Long incr(String key);

    public boolean setnx(String key, String value);

    public boolean setnx(String key, TimeUnit unit, int expiredTime, String value);

}
