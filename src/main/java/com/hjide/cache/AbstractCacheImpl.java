package com.hjide.cache;

import com.hjide.cache.client.CacheClientProxy;
import com.hjide.cache.common.ScoredValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class AbstractCacheImpl
    implements CacheProxy
{

    protected Log log = LogFactory.getLog(AbstractCacheImpl.class);

    protected abstract CacheClientProxy getClientProxy();

    public String type(String key)
    {
        return getClientProxy().type(key);
    }

    public String type(byte[] key)
    {
        return getClientProxy().type(key);
    }

    @Override
    public boolean exists(String key)
    {
        return getClientProxy().exists(key);
    }

    @Override
    public Long del(String key)
    {
        return getClientProxy().del(key);
    }

    @Override
    public boolean expire(String key, int expiredTime, TimeUnit unit)
    {
        return getClientProxy().expire(key, expiredTime, unit);
    }

    public boolean expireAt(String key, Date date)
    {
        return getClientProxy().expireAt(key, date);
    }

    public long ttl(String key)
    {
        return getClientProxy().ttl(key);
    }

    @Override
    public void set(String key, String value)
    {
        getClientProxy().set(key, value);
    }

    @Override
    public String get(String key)
    {
        return getClientProxy().get(key);
    }

    @Override
    public boolean setex(String key, TimeUnit unit, int expiredTime, String value)
    {
        return getClientProxy().setex(key, unit, expiredTime, value);
    }

    @Override
    public String getSet(String key, String value)
    {
        return getClientProxy().getSet(key, value);
    }

    @Override
    public Long decrBy(String key, long step)
    {
        return getClientProxy().decrBy(key, step);
    }

    @Override
    public Long decr(String key)
    {
        return getClientProxy().decr(key);
    }

    @Override
    public Long incrBy(String key, long step)
    {
        return getClientProxy().incrBy(key, step);
    }

    @Override
    public Long incr(String key)
    {
        return getClientProxy().incr(key);
    }

    @Override
    public boolean setnx(String key, String value)
    {
        return getClientProxy().setnx(key, value);
    }

    @Override
    public boolean hset(String key, String field, String value)
    {
        return getClientProxy().hset(key, field, value);
    }

    @Override
    public void hmset(String key, Map<String, String> hash)
    {
        getClientProxy().hmset(key, hash);
    }

    @Override
    public String hget(String key, String field)
    {
        return getClientProxy().hget(key, field);
    }

    @Override
    public List<String> hmget(String key, String... fields)
    {
        return getClientProxy().hmget(key, fields);
    }

    @Override
    public Map<String, String> hgetAll(String key)
    {
        return getClientProxy().hgetAll(key);
    }

    @Override
    public boolean hexists(String key, String field)
    {
        return getClientProxy().hexists(key, field);
    }

    @Override
    public Long hdel(String key, String... field)
    {
        return getClientProxy().hdel(key, field);
    }

    @Override
    public Long lpush(String key, String... value)
    {
        return getClientProxy().lpush(key, value);
    }

    @Override
    public Long rpush(String key, String... value)
    {
        return getClientProxy().rpush(key, value);
    }

    @Override
    public String lpop(String key)
    {
        return getClientProxy().lpop(key);
    }

    @Override
    public String rpop(String key)
    {
        return getClientProxy().rpop(key);
    }

    @Override
    public long llen(String key)
    {
        return getClientProxy().llen(key);
    }

    @Override
    public String lindex(String key, long index)
    {
        return getClientProxy().lindex(key, index);
    }

    @Override
    public List<String> lrange(String key, long begin, long end)
    {
        return getClientProxy().lrange(key, begin, end);
    }

    @Override
    public void ltrim(String key, long begin, long end)
    {
        getClientProxy().ltrim(key, begin, end);
    }

    @Override
    public void lset(String key, long index, String value)
    {
        getClientProxy().lset(key, index, value);
    }

    @Override
    public Long lrem(String key, long count, String value)
    {
        return getClientProxy().lrem(key, count, value);
    }

    @Override
    public Long lpushx(String key, String value)
    {
        return getClientProxy().lpushx(key, value);
    }

    @Override
    public Long rpushx(String key, String value)
    {
        return getClientProxy().rpushx(key, value);
    }

    @Deprecated
    @Override
    public void setObject(String key, Object value, TimeUnit unit, int expiredTime)
    {
        getClientProxy().setObject(key, unit, expiredTime, value);
    }

    @Override
    public Object getObject(String key)
    {
        return getClientProxy().getObject(key);
    }

    @Override
    public void setObject(String key, Object value)
    {
        getClientProxy().setObject(key, value);
    }

    @Override
    public Long sadd(String key, String... values)
    {
        return getClientProxy().sadd(key, values);
    }

    @Override
    public Set<String> smembers(String key)
    {
        return getClientProxy().smembers(key);
    }

    @Override
    public Long srem(String key, String... values)
    {
        return getClientProxy().srem(key, values);
    }

    @Override
    public String spop(String key)
    {
        return getClientProxy().spop(key);
    }

    @Override
    public Long scard(String key)
    {
        return getClientProxy().scard(key);
    }

    @Override
    public boolean setnx(String key, TimeUnit unit, int expiredTime, String value)
    {
        return getClientProxy().setnx(key, unit, expiredTime, value);
    }

    @Override
    public Boolean sismember(String key, String value)
    {
        return getClientProxy().sismember(key, value);
    }

    @Override
    public String srandmember(String key)
    {
        return getClientProxy().srandmember(key);
    }

    @Override
    public Boolean zadd(String key, double score, String value)
    {
        return getClientProxy().zadd(key, score, value);
    }

    @Override
    public Long zadd(String key, Map<String, Double> tupleMap)
    {
        return getClientProxy().zadd(key, tupleMap);
    }

    @Override
    public Set<String> zRange(String key, long begin, long end)
    {
        return getClientProxy().zRange(key, begin, end);
    }

    @Override
    public Long zrem(String key, String... values)
    {
        return getClientProxy().zrem(key, values);
    }

    @Override
    public Double zincrby(String key, double increment, String value)
    {
        return getClientProxy().zincrby(key, increment, value);
    }

    @Override
    public Long zrank(String key, String value)
    {
        return getClientProxy().zrank(key, value);
    }

    @Override
    public Long zrevrank(String key, String value)
    {
        return getClientProxy().zrevrank(key, value);
    }

    @Override
    public Set<String> zrevrange(String key, long begin, long end)
    {
        return getClientProxy().zrevrange(key, begin, end);
    }

    @Override
    public Set<ScoredValue> zrangeWithScores(String key, long begin, long end)
    {
        return getClientProxy().zrangeWithScores(key, begin, end);
    }

    @Override
    public Set<ScoredValue> zrevrangeWithScores(String key, long min, long max)
    {
        return getClientProxy().zrevrangeWithScores(key, min, max);
    }

    @Override
    public Long zcard(String key)
    {
        return getClientProxy().zcard(key);
    }

    @Override
    public Double zscore(String key, String value)
    {
        return getClientProxy().zscore(key, value);
    }

    @Override
    public Long zcount(String key, double min, double max)
    {
        return getClientProxy().zcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max)
    {
        return getClientProxy().zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max)
    {
        return getClientProxy().zrevrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count)
    {
        return getClientProxy().zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max, int offset, int count)
    {
        return getClientProxy().zrevrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<ScoredValue> zrangeByScoreWithScores(String key, double min, double max)
    {
        return getClientProxy().zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<ScoredValue> zrevrangeByScoreWithScores(String key, double min, double max)
    {
        return getClientProxy().zrevrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<ScoredValue> zrangeByScoreWithScores(String key, double min, double max, int offset, int count)
    {
        return getClientProxy().zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<ScoredValue> zrevrangeByScoreWithScores(String key, double min, double max, int offset, int count)
    {
        return getClientProxy().zrevrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max)
    {
        return getClientProxy().zremrangeByScore(key, max, max);
    }

}
