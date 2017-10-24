package com.hjide.cache.client;

import com.hjide.cache.common.ScoredValue;
import com.lambdaworks.redis.Limit;
import com.lambdaworks.redis.Range;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.SetArgs;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;
import com.lambdaworks.redis.codec.ByteArrayCodec;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisLettuceClientImpl extends AbstractCacheClient
{

    private RedisCommands<String, String> commands ;

    private RedisCommands<byte[], byte[]> byteCommands;

    public void setClient(RedisClient client)
    {

        StatefulRedisConnection<String, String> connection = client.connect();

        StatefulRedisConnection<byte[], byte[]> connection2 = client.connect(new ByteArrayCodec());

        commands = connection.sync();

        byteCommands = connection2.sync();

    }

    @Override
    public Long del(String key)
    {
        return commands.del(key);
    }

    @Override
    public boolean expire(String key, int expiredTime, TimeUnit unit)
    {
        return commands.expire(key, unit.toSeconds(expiredTime));
    }

    @Override
    public boolean expireAt(String key, Date date)
    {
        return commands.expireat(key, date);
    }

    @Override
    public long ttl(String key)
    {
        return commands.ttl(key);
    }

    @Override
    public String type(String key)
    {
        return commands.type(key);
    }

    @Override
    public String type(byte[] key)
    {
        return byteCommands.type(key);
    }

    @Override
    public boolean exists(String key)
    {
        return commands.exists(key);
    }

    @Override
    public void set(String key, String value)
    {
        commands.set(key, value);
    }

    @Override
    public String getSet(String key, String value)
    {
        return commands.getset(key, value);
    }

    @Override
    public String get(String key)
    {
        return commands.get(key);
    }

    @Override
    public Long decrBy(String key, long step)
    {
        return commands.decrby(key, step);
    }

    @Override
    public Long decr(String key)
    {
        return commands.decr(key);
    }

    @Override
    public Long incrBy(String key, long step)
    {
        return commands.incrby(key, step);
    }

    @Override
    public Long incr(String key)
    {
        return commands.incr(key);
    }

    @Override
    public boolean setnx(String key, String value)
    {
        return commands.setnx(key, value);
    }

    @Override
    public boolean setnx(String key, TimeUnit unit, int expiredTime, String value)
    {
        SetArgs setArgs = new SetArgs();
        setArgs.nx();
        setArgs.ex(unit.toSeconds(expiredTime));
        return "OK".equals(commands.set(key, value, setArgs));
    }

    @Override
    public boolean setex(String key, TimeUnit unit, int expiredTime, String value)
    {
        return "OK".equals(commands.setex(key, unit.toSeconds(expiredTime), value));
    }

    @Override
    public Object getObject(String key)
    {
        try
        {
            byte[] data = byteCommands.get(key.getBytes());
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
        try
        {
            byteCommands.set(key.getBytes(), toByteArray(value));
        }
        catch (Exception ex)
        {
            throw new RuntimeException("setObject error." + ex.getMessage());
        }
    }

    @Override
    public void setObject(String key, TimeUnit unit, int expiredTime, Object value)
    {
        try
        {
            byteCommands.del(key.getBytes());
            //todo
            // byteCommands.set(key.getBytes(), toByteArray(value),"nx".getBytes(),"px".getBytes(),unit.toMillis(expiredTime));
        }
        catch (Exception ex)
        {
            throw new RuntimeException("setObject error." + ex.getMessage());
        }

    }

    @Override
    public Long rpush(String key, String... value)
    {
        return commands.rpush(key, value);
    }

    @Override
    public Long lpush(String key, String... value)
    {
        return commands.lpush(key, value);
    }

    @Override
    public Long llen(String key)
    {
        return commands.llen(key);
    }

    @Override
    public List<String> lrange(String key, long begin, long end)
    {
        return commands.lrange(key, begin, end);
    }

    @Override
    public void ltrim(String key, long begin, long end)
    {
        commands.ltrim(key, begin, end);
    }

    @Override
    public String lindex(String key, long index)
    {
        return commands.lindex(key, index);
    }

    @Override
    public void lset(String key, long index, String value)
    {
        commands.lset(key, index, value);
    }

    @Override
    public Long lrem(String key, long count, String value)
    {
        return commands.lrem(key, count, value);
    }

    @Override
    public String lpop(String key)
    {
        return commands.lpop(key);
    }

    @Override
    public String rpop(String key)
    {
        return commands.rpop(key);
    }

    @Override
    public Long lpushx(String key, String... value)
    {
        return commands.lpushx(key, value);
    }

    @Override
    public Long rpushx(String key, String... value)
    {
        return commands.rpushx(key, value);
    }

    @Override
    public boolean hset(String key, String field, String value)
    {
        return commands.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field)
    {
        return commands.hget(key, field);
    }

    @Override
    public Boolean hsetnx(String key, String field, String value)
    {
        return commands.hsetnx(key, field, value);
    }

    @Override
    public void hmset(String key, Map hash)
    {
        commands.hmset(key, hash);
    }

    @Override
    public List<String> hmget(String key, String... fields)
    {
        return commands.hmget(key, fields);
    }

    @Override
    public Long hincrBy(String key, String field, long delta)
    {
        return commands.hincrby(key, field, delta);
    }

    @Override
    public boolean hexists(String key, String field)
    {
        return commands.hexists(key, field);
    }

    @Override
    public Long hdel(String key, String... field)
    {
        return commands.hdel(key, field);
    }

    @Override
    public Long hlen(String key)
    {
        return commands.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key)
    {
        Set<String> sets = new HashSet<String>();
        sets.addAll(commands.hkeys(key));
        return sets;
    }

    @Override
    public List<String> hvals(String key)
    {
        return commands.hvals(key);
    }

    @Override
    public Map<String, String> hgetAll(String key)
    {
        return commands.hgetall(key);
    }

    @Override
    public Long sadd(String key, String... values)
    {
        return commands.sadd(key, values);
    }

    @Override
    public Set<String> smembers(String key)
    {
        return commands.smembers(key);
    }

    @Override
    public Long srem(String key, String... values)
    {
        return commands.srem(key, values);
    }

    @Override
    public String spop(String key)
    {
        return commands.spop(key);
    }

    @Override
    public Long scard(String key)
    {
        return commands.scard(key);
    }

    @Override
    public Boolean sismember(String key, String value)
    {
        return commands.sismember(key, value);
    }

    @Override
    public String srandmember(String key)
    {
        return commands.srandmember(key);
    }

    @Override
    public Boolean zadd(String key, double score, String value)
    {
        return 1 == commands.zadd(key, score, value);
    }

    @Override
    public Long zadd(String key, Map<String, Double> tupleMap)
    {
        return commands.zadd(key, tupleMap);
    }

    @Override
    public Set<String> zRange(String key, long begin, long end)
    {
        Set<String> set = new HashSet<String>();
        set.addAll(commands.zrange(key, begin, end));
        return set;
    }

    @Override
    public Long zrem(String key, String... values)
    {
        return commands.zrem(key, values);
    }

    @Override
    public Double zincrby(String key, double increment, String value)
    {
        return commands.zincrby(key, increment, value);
    }

    @Override
    public Long zrank(String key, String value)
    {
        return commands.zrank(key, value);
    }

    @Override
    public Long zrevrank(String key, String value)
    {
        return commands.zrevrank(key, value);
    }

    @Override
    public Set<String> zrevrange(String key, long begin, long end)
    {
        Set<String> set = new HashSet<String>();
        set.addAll(commands.zrevrange(key, begin, end));
        return set;
    }

    private Set<ScoredValue> convertTuple(List<com.lambdaworks.redis.ScoredValue<String>> returnSet)
    {
        Set<ScoredValue> tupleSet = new HashSet<>();
        for (com.lambdaworks.redis.ScoredValue<String> stringScoredValue : returnSet)
        {
            tupleSet.add(new ScoredValue(stringScoredValue.value, stringScoredValue.score));
        }
        return tupleSet;
    }

    @Override
    public Set<ScoredValue> zrangeWithScores(String key, long begin, long end)
    {
        List<com.lambdaworks.redis.ScoredValue<String>> list = commands.zrangeWithScores(key, begin, end);

        return convertTuple(list);
    }

    @Override
    public Set<ScoredValue> zrevrangeWithScores(String key, long min, long max)
    {
        List<com.lambdaworks.redis.ScoredValue<String>> list = commands.zrevrangeWithScores(key, min, max);
        return convertTuple(list);
    }

    @Override
    public Long zcard(String key)
    {
        return commands.zcard(key);
    }

    @Override
    public Double zscore(String key, String value)
    {
        return commands.zscore(key, value);
    }

    @Override
    public Long zcount(String key, double min, double max)
    {
        return commands.zcount(key, Range.create(min, max));
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max)
    {
        Set<String> set = new HashSet<String>();
        set.addAll(commands.zrangebyscore(key, Range.create(min, max)));
        return set;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max)
    {
        Set<String> set = new HashSet<String>();
        set.addAll(commands.zrevrangebyscore(key, Range.create(min, max)));
        return set;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count)
    {
        Set<String> set = new HashSet<String>();
        set.addAll(commands.zrangebyscore(key, Range.create(min, max), Limit.create(offset, count)));
        return set;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max, int offset, int count)
    {
        Set<String> set = new HashSet<String>();
        set.addAll(commands.zrevrangebyscore(key, Range.create(min, max), Limit.create(offset, count)));
        return set;
    }

    @Override
    public Set<ScoredValue> zrangeByScoreWithScores(String key, double min, double max)
    {
        List<com.lambdaworks.redis.ScoredValue<String>> list =
            commands.zrangebyscoreWithScores(key, Range.create(min, max));
        return convertTuple(list);
    }

    @Override
    public Set<ScoredValue> zrevrangeByScoreWithScores(String key, double min, double max)
    {
        List<com.lambdaworks.redis.ScoredValue<String>> list =
            commands.zrevrangebyscoreWithScores(key, Range.create(min, max));
        return convertTuple(list);
    }

    @Override
    public Set<ScoredValue> zrangeByScoreWithScores(String key, double min, double max, int offset, int count)
    {
        List<com.lambdaworks.redis.ScoredValue<String>> list =
            commands.zrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count));
        return convertTuple(list);
    }

    @Override
    public Set<ScoredValue> zrevrangeByScoreWithScores(String key, double min, double max, int offset, int count)
    {
        List<com.lambdaworks.redis.ScoredValue<String>> list =
            commands.zrevrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count));
        return convertTuple(list);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max)
    {
        return commands.zremrangebyscore(key, Range.create(min, max));
    }

}
