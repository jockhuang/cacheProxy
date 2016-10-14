package com.hjide.cache.client;

import com.hjide.cache.common.CacheTuple;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * author: yanghua.yi
 * date: 2015/1/9
 * time: 17:07
 */
public interface CacheClientProxy {

    //key方法
    public Long del(String key);

    public boolean expire(String key, int expiredTime, TimeUnit unit);

    public boolean expireAt(String key, Date date);

    public long ttl(String key);

    public String type(String key);

    public String type(byte[] key);

    public boolean exists(String key);

    //string方法
    public void set(String key, String value);

    public String getSet(String key, String value);

    public String get(String key);

    public Long decrBy(String key, long step);

    public Long decr(String key);

    public Long incrBy(String key, long step);

    public Long incr(String key);

    public boolean setnx(String key, String value);

    public boolean setnx(String key, TimeUnit unit, int expiredTime, String value);

    public boolean setex(String key, TimeUnit unit, int expiredTime, String value);

    //object

    public Object getObject(String key);

    public void setObject(String key, Object value);

    public void setObject(String key, TimeUnit unit, int expiredTime,Object value);

    //list接口
    Long rpush(String key, String... value);

    Long lpush(String key, String... value);

    Long llen(String key);

    List<String> lrange(String key, long begin, long end);

    void ltrim(String key, long begin, long end);

    String lindex(String key, long index);

    void lset(String key, long index, String value);

    Long lrem(String key, long count, String value);

    String lpop(String key);

    String rpop(String key);

    Long lpushx(String key, String value);

    Long rpushx(String key, String value);

    //map方法
    boolean hset(String key, String field, String value);

    String hget(String key, String field);

    Boolean hsetnx(String key, String field, String value);

    void hmset(String key, Map hash);

    List<String> hmget(String key, String... fields);

    Long hincrBy(String key, String field, long delta);

    boolean hexists(String key, String field);

    Long hdel(String key, String... field);

    Long hlen(String key);

    Set<String> hkeys(String key);

    List<String> hvals(String key);

    Map<String, String> hgetAll(String key);

    //set
    Long sadd(String key, String... values);

    Set<String> smembers(String key);

    Long srem(String key, String... values);

    String spop(String key);

    Long scard(String key);

    Boolean sismember(String key, String value);

    String srandmember(String key);

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
