package com.hjide.cache.proxy.impl;

import com.hjide.cache.proxy.HashCacheProxy;

import java.util.List;
import java.util.Map;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public  class DefaultHashCacheProxy  extends BaseCacheProxyImpl implements HashCacheProxy {

    @Override
    public boolean hset(String key, String field, String value) {
        return getClientProxy().hset(key, field, value);
    }

    @Override
    public void hmset(String key, Map<String, String> hash) {
        getClientProxy().hmset(key, hash);
    }

    @Override
    public String hget(String key, String field) {
        return getClientProxy().hget(key, field);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return getClientProxy().hmget(key,fields);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return getClientProxy().hgetAll(key);
    }

    @Override
    public boolean hexists(String key, String field) {
        return getClientProxy().hexists(key,field);
    }

    @Override
    public Long hdel(String key, String... field) {
        return getClientProxy().hdel(key,field);
    }
}
