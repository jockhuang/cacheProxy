package com.hjide.cache.proxy.impl;

import com.hjide.cache.proxy.ListCacheProxy;

import java.util.List;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public  class DefaultListCacheProxy
        extends BaseCacheProxyImpl implements ListCacheProxy {
    @Override
    public Long lpush(String key, String... value) {
        return getClientProxy().lpush(key,value);
    }

    @Override
    public Long rpush(String key, String... value) {
        return getClientProxy().rpush(key,value);
    }

    @Override
    public String lpop(String key) {
        return getClientProxy().lpop(key);
    }

    @Override
    public String rpop(String key) {
        return getClientProxy().rpop(key);
    }

    @Override
    public long llen(String key) {
        return getClientProxy().llen(key);
    }

    @Override
    public String lindex(String key, long index) {
        return getClientProxy().lindex(key,index);
    }

    @Override
    public List<String> lrange(String key, long begin, long end) {
        return getClientProxy().lrange(key,begin,end);
    }

    @Override
    public void ltrim(String key, long begin, long end) {
        getClientProxy().ltrim(key,begin,end);
    }

    @Override
    public void lset(String key, long index, String value) {
        getClientProxy().lset(key,index,value);
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return getClientProxy().lrem(key,count,value);
    }

    @Override
    public Long lpushx(String key, String value) {
        return getClientProxy().lpushx(key,value);
    }

    @Override
    public Long rpushx(String key, String value) {
        return getClientProxy().rpushx(key,value);
    }
}
