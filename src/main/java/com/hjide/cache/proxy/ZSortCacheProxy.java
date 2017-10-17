package com.hjide.cache.proxy;

import com.hjide.cache.common.CacheTuple;

import java.util.Map;
import java.util.Set;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public interface ZSortCacheProxy
{

    //zset
    Boolean zadd(String key, double score, String value);

    Long zadd(String key, Map<String, Double> tupleMap);

    Set<String> zRange(String key, long begin, long end);

    Long zrem(String key, String... values);

    Double zincrby(String key, double increment, String value);

    Long zrank(String key, String value);

    Long zrevrank(String key, String value);

    Set<String> zrevrange(String key, long begin, long end);

    Set<CacheTuple> zrangeWithScores(String key, long begin, long end);

    Set<CacheTuple> zrevrangeWithScores(String key, long min, long max);

    Long zcard(String key);

    Double zscore(String key, String value);

    Long zcount(String key, double min, double max);

    Set<String> zrangeByScore(String key, double min, double max);

    Set<String> zrevrangeByScore(String key, double min, double max);

    Set<String> zrangeByScore(String key, double min, double max, int offset, int count);

    Set<String> zrevrangeByScore(String key, double min, double max, int offset, int count);

    Set<CacheTuple> zrangeByScoreWithScores(String key, double min, double max);

    Set<CacheTuple> zrevrangeByScoreWithScores(String key, double min, double max);

    Set<CacheTuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count);

    Set<CacheTuple> zrevrangeByScoreWithScores(String key, double min, double max, int offset, int count);

    Long zremrangeByScore(String key, double min, double max);
}
