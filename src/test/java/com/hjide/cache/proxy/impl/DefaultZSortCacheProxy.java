package com.hjide.cache.proxy.impl;

import com.hjide.cache.proxy.ZSortCacheProxy;
import com.hjide.cache.common.CacheTuple;

import java.util.Map;
import java.util.Set;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public  class DefaultZSortCacheProxy extends BaseCacheProxyImpl implements ZSortCacheProxy {

    @Override
    public Boolean zadd(String key, double score, String value) {
        return getClientProxy().zadd(key,score,value);
    }

    @Override
    public Long zadd(String key, Map<String,Double> tupleMap) {
        return getClientProxy().zadd(key,tupleMap);
    }

    @Override
    public Set<String> zRange(String key, long begin, long end) {
        return getClientProxy().zRange(key,begin,end);
    }

    @Override
    public Long zrem(String key, String... values) {
        return getClientProxy().zrem(key,values);
    }

    @Override
    public Double zincrby(String key, double increment, String value) {
        return getClientProxy().zincrby(key,increment,value);
    }

    @Override
    public Long zrank(String key, String value) {
        return getClientProxy().zrank(key,value);
    }

    @Override
    public Long zrevrank(String key, String value) {
        return getClientProxy().zrevrank(key,value);
    }

    @Override
    public Set<String> zrevrange(String key, long begin, long end) {
        return getClientProxy().zrevrange(key,begin,end);
    }

    @Override
    public Set<CacheTuple> zrangeWithScores(String key, long begin, long end) {
        return getClientProxy().zrangeWithScores(key,begin,end);
    }

    @Override
    public Set<CacheTuple> zrevrangeWithScores(String key, long min, long max) {
        return getClientProxy().zrevrangeWithScores(key,min, max);
    }

    @Override
    public Long zcard(String key) {
        return getClientProxy().zcard(key);
    }

    @Override
    public Double zscore(String key, String value) {
        return getClientProxy().zscore(key,value);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return getClientProxy().zcount(key,min,max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return getClientProxy().zrangeByScore(key,min,max);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max) {
        return getClientProxy().zrevrangeByScore(key,min,max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return getClientProxy().zrangeByScore(key,min,max,offset,count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max, int offset, int count) {
        return getClientProxy().zrevrangeByScore(key,min,max,offset,count);
    }

    @Override
    public Set<CacheTuple> zrangeByScoreWithScores(String key, double min, double max) {
        return getClientProxy().zrangeByScoreWithScores(key,min,max);
    }

    @Override
    public Set<CacheTuple> zrevrangeByScoreWithScores(String key, double min, double max) {
        return getClientProxy().zrevrangeByScoreWithScores(key,min,max);
    }

    @Override
    public Set<CacheTuple> zrangeByScoreWithScores(String key, double min, double max,int offset, int count) {
        return getClientProxy().zrangeByScoreWithScores(key,min,max,offset,count);
    }

    @Override
    public Set<CacheTuple> zrevrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return getClientProxy().zrevrangeByScoreWithScores(key,min,max,offset,count);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        return getClientProxy().zremrangeByScore(key,max,max);
    }
}
