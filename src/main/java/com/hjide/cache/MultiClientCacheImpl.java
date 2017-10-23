package com.hjide.cache;

import com.hjide.cache.common.ScoredValue;
import com.hjide.cache.enums.ErrorCode;
import com.hjide.cache.exception.CacheException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MultiClientCacheImpl implements CacheProxy, InitializingBean
{

    protected Map<String, CacheProxy> slaveCacheProxyMap = new ConcurrentHashMap<String, CacheProxy>();

    Log log = LogFactory.getLog(MultiClientCacheImpl.class);

    volatile CacheProxy masterClient;

    volatile CountDownLatch masterSlaveSwapLock = null;

    private Map<String, CacheProxy> cacheProxyMap;

    /**
     * 主集群springBean id
     */
    private volatile String masterProxy;

    @Override
    public void afterPropertiesSet()
        throws Exception
    {

        initCache();

    }

    void initCache()
    {
        masterSlaveSwapLock = new CountDownLatch(1);
        try
        {
            initMasterClient();
            initSlaveCache();
        }
        finally
        {
            masterSlaveSwapLock.countDown();
            masterSlaveSwapLock = null;
        }

    }

    void initSlaveCache()
    {
        slaveCacheProxyMap.putAll(cacheProxyMap);
        slaveCacheProxyMap.remove(masterProxy);
    }

    /**
     * 初始化主缓存
     */
    void initMasterClient()
    {

        if (cacheProxyMap == null || cacheProxyMap.size() == 0)
        {
            throw new CacheException(ErrorCode.parameter_error, "Not effective cache proxy;");
        }
        CacheProxy client = cacheProxyMap.get(masterProxy);
        if (client != null)
        {
            masterClient = client;
        }
        else
        {
            for (CacheProxy proxy : cacheProxyMap.values())
            {
                if (proxy != null)
                {
                    masterClient = proxy;
                }
            }
        }
        if (masterClient == null)
            throw new CacheException(ErrorCode.parameter_error, "Not effective cache proxy;");
    }

    public void setCacheProxyMap(Map<String, CacheProxy> cacheProxyMap)
    {
        this.cacheProxyMap = cacheProxyMap;
    }

    public void setMasterProxy(String masterProxy)
    {
        this.masterProxy = masterProxy;
    }

    /**
     * 获取主缓存客户端
     *
     * @return
     */
    protected CacheProxy getMasterClient()
    {
        if (masterSlaveSwapLock != null)
        {
            try
            {
                masterSlaveSwapLock.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return masterClient;
    }

    @Override
    public boolean expire(String key, int expiredTime, TimeUnit unit)
    {

        boolean s = getMasterClient().expire(key, expiredTime, unit);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.expire(key, expiredTime, unit);
            }
        }
        return s;
    }

    public boolean expireAt(String key, Date date)
    {
        boolean s = getMasterClient().expireAt(key, date);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                s = s && clientProxy.expireAt(key, date);
            }
        }
        return s;
    }

    public long ttl(String key)
    {
        return getMasterClient().ttl(key);
    }

    public String type(String key)
    {
        return getMasterClient().type(key);
    }

    public String type(byte[] key)
    {
        return getMasterClient().type(key);
    }

    @Override
    public boolean exists(String key)
    {
        return getMasterClient().exists(key);
    }

    @Override
    public Long del(String key)
    {
        Long s = getMasterClient().del(key);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.del(key);
            }
        }
        return s;
    }

    @Override
    public void set(String key, String value)
    {
        getMasterClient().set(key, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.set(key, value);
            }
        }
    }

    @Override
    public void setObject(String key, Object value, TimeUnit unit, int expiredTime)
    {
        throw new CacheException(ErrorCode.parameter_error, "not support operate;");
    }

    @Override
    public String get(String key)
    {
        return getMasterClient().get(key);
    }

    @Override
    public String getSet(String key, String value)
    {
        String msg = getMasterClient().getSet(key, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.getSet(key, value);
            }
        }
        return msg;
    }

    @Override
    public Long decrBy(String key, long step)
    {
        Long s = getMasterClient().decrBy(key, step);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.decrBy(key, step);
            }
        }
        return s;
    }

    @Override
    public Long decr(String key)
    {
        Long s = getMasterClient().decr(key);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.decr(key);
            }
        }
        return s;
    }

    @Override
    public Long incrBy(String key, long step)
    {
        Long s = getMasterClient().incrBy(key, step);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.incrBy(key, step);
            }
        }
        return s;
    }

    @Override
    public Long incr(String key)
    {
        Long s = getMasterClient().incr(key);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                s = clientProxy.incr(key);
            }
        }
        return s;
    }

    @Override
    public boolean hset(String key, String field, String value)
    {
        boolean result = getMasterClient().hset(key, field, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            result = true;
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.hset(key, field, value);
            }
        }
        return result;

    }

    @Override
    public void hmset(String key, Map<String, String> hash)
    {
        getMasterClient().hmset(key, hash);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.hmset(key, hash);
            }
        }
    }

    @Override
    public String hget(String key, String field)
    {
        return getMasterClient().hget(key, field);
    }

    @Override
    public List<String> hmget(String key, String... fields)
    {
        return getMasterClient().hmget(key, fields);
    }

    @Override
    public Map<String, String> hgetAll(String key)
    {
        return getMasterClient().hgetAll(key);
    }

    @Override
    public boolean hexists(String key, String field)
    {
        return getMasterClient().hexists(key, field);
    }

    @Override
    public Long hdel(String key, String... field)
    {
        Long s = getMasterClient().hdel(key, field);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.hdel(key, field);
            }
        }
        return s;
    }

    @Override
    public Long lpush(String key, String... value)
    {
        Long s = getMasterClient().lpush(key, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.lpush(key, value);
            }
        }
        return s;
    }

    @Override
    public Long rpush(String key, String... value)
    {
        Long s = getMasterClient().rpush(key, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.rpush(key, value);
            }
        }
        return s;
    }

    @Override
    public String lpop(String key)
    {
        String s = getMasterClient().lpop(key);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.lpop(key);
            }
        }
        return s;
    }

    @Override
    public String rpop(String key)
    {
        String s = getMasterClient().rpop(key);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.rpop(key);
            }
        }
        return s;
    }

    @Override
    public boolean setnx(String key, String value)
    {
        boolean s = getMasterClient().setnx(key, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.setnx(key, value);
            }
        }
        return s;
    }

    @Override
    public boolean setnx(String key, TimeUnit unit, int expiredTime, String value)
    {
        boolean s = getMasterClient().setnx(key, unit, expiredTime, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.setnx(key, unit, expiredTime, value);
            }
        }
        return s;
    }

    @Override
    public Object getObject(String key)
    {
        return getMasterClient().getObject(key);
    }

    @Override
    public void setObject(String key, Object value)
    {
        getMasterClient().setObject(key, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.setObject(key, value);
            }
        }
    }

    @Override
    public boolean setex(String key, TimeUnit unit, int expiredTime, String value)
    {
        boolean s = getMasterClient().setex(key, unit, expiredTime, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.setex(key, unit, expiredTime, value);
            }
        }
        return s;
    }

    @Override
    public long llen(String key)
    {
        return getMasterClient().llen(key);
    }

    @Override
    public String lindex(String key, long index)
    {
        return getMasterClient().lindex(key, index);
    }

    @Override
    public List<String> lrange(String key, long begin, long end)
    {
        return getMasterClient().lrange(key, begin, end);
    }

    @Override
    public void ltrim(String key, long begin, long end)
    {
        getMasterClient().ltrim(key, begin, end);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.ltrim(key, begin, end);
            }
        }
    }

    @Override
    public void lset(String key, long index, String value)
    {
        getMasterClient().lset(key, index, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.lset(key, index, value);
            }
        }
    }

    @Override
    public Long lrem(String key, long count, String value)
    {
        Long r = getMasterClient().lrem(key, count, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.lrem(key, count, value);
            }
        }
        return r;
    }

    @Override
    public Long lpushx(String key, String value)
    {
        Long r = getMasterClient().lpushx(key, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.lpushx(key, value);
            }
        }
        return r;
    }

    @Override
    public Long rpushx(String key, String value)
    {
        Long r = getMasterClient().rpushx(key, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.rpushx(key, value);
            }
        }
        return r;
    }

    @Override
    public Long sadd(String key, String... values)
    {
        Long r = getMasterClient().sadd(key, values);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.sadd(key, values);
            }
        }
        return r;
    }

    @Override
    public Set<String> smembers(String key)
    {
        return getMasterClient().smembers(key);
    }

    @Override
    public Long srem(String key, String... values)
    {
        Long r = getMasterClient().srem(key, values);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.srem(key, values);
            }
        }
        return r;
    }

    @Override
    public String spop(String key)
    {
        String s = getMasterClient().spop(key);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.spop(key);
            }
        }
        return s;
    }

    @Override
    public Long scard(String key)
    {
        return getMasterClient().scard(key);
    }

    @Override
    public Boolean sismember(String key, String value)
    {
        return getMasterClient().sismember(key, value);
    }

    @Override
    public String srandmember(String key)
    {
        return getMasterClient().srandmember(key);
    }

    @Override
    public Boolean zadd(String key, double score, String value)
    {
        Boolean b = getMasterClient().zadd(key, score, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.zadd(key, score, value);
            }
        }
        return b;
    }

    @Override
    public Long zadd(String key, Map<String, Double> tupleMap)
    {
        Long l = getMasterClient().zadd(key, tupleMap);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.zadd(key, tupleMap);
            }
        }
        return l;
    }

    @Override
    public Set<String> zRange(String key, long begin, long end)
    {
        return getMasterClient().zRange(key, begin, end);
    }

    @Override
    public Long zrem(String key, String... values)
    {
        Long l = getMasterClient().zrem(key, values);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.zrem(key, values);
            }
        }
        return l;
    }

    @Override
    public Double zincrby(String key, double increment, String value)
    {
        Double d = getMasterClient().zincrby(key, increment, value);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.zincrby(key, increment, value);
            }
        }
        return d;
    }

    @Override
    public Long zrank(String key, String value)
    {
        return getMasterClient().zrank(key, value);
    }

    @Override
    public Long zrevrank(String key, String value)
    {
        return getMasterClient().zrevrank(key, value);
    }

    @Override
    public Set<String> zrevrange(String key, long begin, long end)
    {
        return getMasterClient().zrevrange(key, begin, end);
    }

    @Override
    public Set<ScoredValue> zrangeWithScores(String key, long begin, long end)
    {
        return getMasterClient().zrangeWithScores(key, begin, end);
    }

    @Override
    public Set<ScoredValue> zrevrangeWithScores(String key, long min, long max)
    {
        return getMasterClient().zrevrangeWithScores(key, min, max);
    }

    @Override
    public Long zcard(String key)
    {
        return getMasterClient().zcard(key);
    }

    @Override
    public Double zscore(String key, String value)
    {
        return getMasterClient().zscore(key, value);
    }

    @Override
    public Long zcount(String key, double min, double max)
    {
        return getMasterClient().zcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max)
    {
        return getMasterClient().zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max)
    {
        return getMasterClient().zrevrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count)
    {
        return getMasterClient().zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max, int offset, int count)
    {
        return getMasterClient().zrevrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<ScoredValue> zrangeByScoreWithScores(String key, double min, double max)
    {
        return getMasterClient().zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<ScoredValue> zrevrangeByScoreWithScores(String key, double min, double max)
    {
        return getMasterClient().zrevrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<ScoredValue> zrangeByScoreWithScores(String key, double min, double max, int offset, int count)
    {
        return getMasterClient().zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<ScoredValue> zrevrangeByScoreWithScores(String key, double min, double max, int offset, int count)
    {
        return getMasterClient().zrevrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max)
    {
        Long l = getMasterClient().zremrangeByScore(key, min, max);
        if (MapUtils.isNotEmpty(slaveCacheProxyMap))
        {
            for (CacheProxy clientProxy : slaveCacheProxyMap.values())
            {
                clientProxy.zremrangeByScore(key, min, max);
            }
        }
        return l;
    }

}
