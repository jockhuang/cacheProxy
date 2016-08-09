package com.hjide.cache.proxy.impl;

import com.hjide.cache.proxy.StringCacheProxy;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public class DaultStringCacheProxy extends BaseCacheProxyImpl implements StringCacheProxy {


    @Override
    public void set(String key, String value) {
        getClientProxy().set(key,value);
    }

    @Override
    public String get(String key) {
        return getClientProxy().get(key);
    }

    @Override
    public boolean setex(String key, TimeUnit unit, int expiredTime, String value) {
        return getClientProxy().setex(key,unit,expiredTime,value);
    }

    @Override
    public String getSet(String key, String value) {
        return getClientProxy().getSet(key,value);
    }

    @Override
    public Long decrBy(String key, long step) {
        return getClientProxy().decrBy(key, step);
    }

    @Override
    public Long decr(String key) {
        return getClientProxy().decr(key);
    }

    @Override
    public Long incrBy(String key, long step) {
        return getClientProxy().incrBy(key,step);
    }

    @Override
    public Long incr(String key) {
        return getClientProxy().incr(key);
    }

    @Override
    public boolean setnx(String key, String value) {
        return getClientProxy().setnx(key,value);
    }

    @Override
    public boolean setnx(String key, TimeUnit unit, int expiredTime, String value) {
        return getClientProxy().setnx(key,unit,expiredTime,value);
    }
}
