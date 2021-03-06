package com.hjide.cache.client;

import com.hjide.cache.common.ScoredValue;
import lombok.Cleanup;
import lombok.NonNull;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisCacheCloudImpl extends AbstractCacheClient
{
    @NonNull
    private JedisPool jedisPool;

    private JedisPool getJdRedisClient()
    {
        return jedisPool;
    }

    public void setJdRedisClient(JedisPool jdRedisClient)
    {
        this.jedisPool = jdRedisClient;
    }

    @Override
    public void set(String key, String value)
    {
        @Cleanup Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
    }

    @Override
    public String getSet(String key, String value)
    {
        @Cleanup Jedis jedis = jedisPool.getResource();
        return jedis.getSet(key, value);
    }

    @Override
    public String get(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.get(key);
    }

    @Override
    public boolean exists(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.exists(key);
    }

    @Override
    public Long decrBy(String key, long step)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.decrBy(key, step);
    }

    @Override
    public Long decr(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.decr(key);
    }

    @Override
    public Long incrBy(String key, long step)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.incrBy(key, step);
    }

    @Override
    public Long incr(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.incr(key);
    }

    @Override
    public boolean hset(String key, String field, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hset(key, field, value) > 0;
    }

    @Override
    public void hmset(String key, Map hash)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        jdRedisClient.hmset(key, hash);
    }

    @Override
    public String hget(String key, String field)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hget(key, field);
    }

    @Override
    public Boolean hsetnx(String key, String field, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hsetnx(key, field, value) > 0;
    }

    @Override
    public List<String> hmget(String key, String... fields)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hmget(key, fields);
    }

    @Override
    public Long hincrBy(String key, String field, long delta)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hincrBy(key, field, delta);
    }

    @Override
    public Map<String, String> hgetAll(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hgetAll(key);
    }

    @Override
    public Long sadd(String key, String... values)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.sadd(key, values);
    }

    @Override
    public Set<String> smembers(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.smembers(key);
    }

    @Override
    public Long srem(String key, String... values)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.srem(key, values);
    }

    @Override
    public String spop(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.spop(key);
    }

    @Override
    public Long scard(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.scard(key);
    }

    @Override
    public Boolean sismember(String key, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.sismember(key, value);
    }

    @Override
    public String srandmember(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.srandmember(key);
    }

    @Override
    public Boolean zadd(String key, double score, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zadd(key, score, value) > 0;
    }

    @Override
    public Long zadd(String key, Map<String, Double> tupleMap)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zadd(key, tupleMap);
    }

    @Override
    public Set<String> zRange(String key, long begin, long end)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zrange(key, begin, end);
    }

    @Override
    public Long zrem(String key, String... values)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zrem(key, values);
    }

    @Override
    public Double zincrby(String key, double increment, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zincrby(key, increment, value);
    }

    @Override
    public Long zrank(String key, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zrank(key, value);
    }

    @Override
    public Long zrevrank(String key, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zrevrank(key, value);
    }

    @Override
    public Set<String> zrevrange(String key, long begin, long end)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zrevrange(key, begin, end);
    }

    @Override
    public Set<ScoredValue> zrangeWithScores(String key, long begin, long end)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> returnSet = jdRedisClient.zrangeWithScores(key, begin, end);
        return convertTuple(returnSet);
    }

    @Override
    public Set<ScoredValue> zrevrangeWithScores(String key, long min, long max)
    {
        @Cleanup Jedis jedis = getJdRedisClient().getResource();
        Set<Tuple> returnSet = jedis.zrevrangeWithScores(key, min, max);
        return convertTuple(returnSet);
    }

    private Set<ScoredValue> convertTuple(Set<Tuple> returnSet)
    {
        Set<ScoredValue> tupleSet = new HashSet<ScoredValue>();
        for (Tuple stringTuple : returnSet)
        {
            tupleSet.add(new ScoredValue(stringTuple.getElement(), stringTuple.getScore()));
        }
        return tupleSet;
    }

    @Override
    public Long zcard(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zcard(key);
    }

    @Override
    public Double zscore(String key, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zscore(key, value);
    }

    @Override
    public Long zcount(String key, double min, double max)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zrevrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max, int offset, int count)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zrevrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<ScoredValue> zrangeByScoreWithScores(String key, double min, double max)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> tupleSet = jdRedisClient.zrangeByScoreWithScores(key, min, max);
        return convertTuple(tupleSet);
    }

    @Override
    public Set<ScoredValue> zrevrangeByScoreWithScores(String key, double min, double max)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> tupleSet = jdRedisClient.zrevrangeByScoreWithScores(key, min, max);
        return convertTuple(tupleSet);
    }

    @Override
    public Set<ScoredValue> zrangeByScoreWithScores(String key, double min, double max, int offset, int count)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> tupleSet = jdRedisClient.zrangeByScoreWithScores(key, min, max, offset, count);
        return convertTuple(tupleSet);
    }

    @Override
    public Set<ScoredValue> zrevrangeByScoreWithScores(String key, double min, double max, int offset, int count)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> tupleSet = jdRedisClient.zrevrangeByScoreWithScores(key, min, max, offset, count);
        return convertTuple(tupleSet);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zremrangeByScore(key, min, max);
    }

    @Override
    public boolean hexists(String key, String field)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hexists(key, field);
    }

    @Override
    public Long del(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.del(key);
    }

    public String type(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.type(key);
    }

    public String type(byte[] key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.type(key);
    }

    @Override
    public Long hdel(String key, String... field)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hdel(key, field);
    }

    @Override
    public Long hlen(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hkeys(key);
    }

    @Override
    public List<String> hvals(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hvals(key);
    }

    @Override
    public boolean expire(String key, int expiredTime, TimeUnit unit)
    {
        Long time = unit.toSeconds(expiredTime);
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.expire(key, time.intValue()) > 0;
    }

    public boolean expireAt(String key, Date date)
    {
        if (date == null)
        {
            return false;
        }
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        Long unixTime = date.getTime() / 1000L;
        return jdRedisClient.expireAt(key, unixTime.intValue()) > 0;
    }

    public long ttl(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.ttl(key);
    }

    @Override
    public Long lpush(String key, String... value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.lpush(key, value);
    }

    @Override
    public String rpop(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.rpop(key);
    }

    @Override
    public Long lpushx(String key, String... value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.lpushx(key, value);
    }

    @Override
    public Long rpushx(String key, String... value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.rpushx(key, value);
    }

    @Override
    public Long rpush(String key, String... value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.rpush(key, value);
    }

    @Override
    public String lpop(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.lpop(key);
    }

    @Override
    public boolean setnx(String key, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.setnx(key, value) > 0;
    }

    @Override
    public boolean setnx(String key, TimeUnit unit, int expiredTime, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        String s = jdRedisClient.set(key, value, "nx", "ex", unit.toSeconds(expiredTime));
        return "OK".equals(s);
    }

    @Override
    public Object getObject(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        try
        {
            byte[] data = jdRedisClient.get(key.getBytes());
            if (data == null)
                return null;
            else
                return toObject(data);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("getObject error." + ex.getMessage());
        }
    }

    @Override
    public void setObject(String key, Object value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        try
        {
            jdRedisClient.set(key.getBytes(), toByteArray(value));
        }
        catch (Exception ex)
        {
            throw new RuntimeException("setObject error." + ex.getMessage());
        }

    }

    @Override
    public void setObject(String key, TimeUnit unit, int expiredTime, Object value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        try
        {
            jdRedisClient.del(key);
            jdRedisClient.set(key.getBytes(),
                toByteArray(value),
                "nx".getBytes(),
                "px".getBytes(),
                unit.toMillis(expiredTime));
        }
        catch (Exception ex)
        {
            throw new RuntimeException("setObject error." + ex.getMessage());
        }

    }

    @Override
    public boolean setex(String key, TimeUnit unit, int expiredTime, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        try
        {
            Long seconds = unit.toSeconds(expiredTime);
            jdRedisClient.setex(key, seconds.intValue(), value);
            return true;
        }
        catch (Exception ex)
        {
            throw new RuntimeException("setex error." + ex.getMessage());
        }
    }

    @Override
    public Long llen(String key)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        try
        {
            return jdRedisClient.llen(key);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("llen error." + ex.getMessage());
        }
    }

    @Override
    public List<String> lrange(String key, long begin, long end)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.lrange(key, begin, end);
    }

    @Override
    public void ltrim(String key, long begin, long end)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        jdRedisClient.ltrim(key, begin, end);
    }

    @Override
    public String lindex(String key, long index)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        try
        {
            return jdRedisClient.lindex(key, index);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("lindex error." + ex.getMessage());
        }
    }

    @Override
    public void lset(String key, long index, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        jdRedisClient.lset(key, index, value);
    }

    @Override
    public Long lrem(String key, long count, String value)
    {
        @Cleanup Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.lrem(key, count, value);
    }

}
