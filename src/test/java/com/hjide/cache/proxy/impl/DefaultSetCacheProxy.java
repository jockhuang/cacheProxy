package com.hjide.cache.proxy.impl;

import com.hjide.cache.proxy.SetCacheProxy;

import java.util.Set;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public  class DefaultSetCacheProxy
        extends BaseCacheProxyImpl implements SetCacheProxy {

    @Override
    public Long sadd(String key, String... values) {
        return getClientProxy().sadd(key,values);
    }

    @Override
    public Set<String> smembers(String key) {
        return getClientProxy().smembers(key);
    }

    @Override
    public Long srem(String key, String... values) {
        return getClientProxy().srem(key,values);
    }

    @Override
    public String spop(String key) {
        return getClientProxy().spop(key);
    }

    @Override
    public Long scard(String key) {
        return getClientProxy().scard(key);
    }

    @Override
    public Boolean sismember(String key, String value) {
        return getClientProxy().sismember(key,value);
    }

    @Override
    public String srandmember(String key) {
        return getClientProxy().srandmember(key);
    }
}
