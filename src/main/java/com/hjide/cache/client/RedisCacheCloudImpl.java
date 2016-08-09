package com.hjide.cache.client;

import com.hjide.cache.common.CacheTuple;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * author: yanghua.yi
 * date: 2015/1/13
 * time: 18:32
 */
public class RedisCacheCloudImpl implements CacheClientProxy {
    protected JedisPool jedisPool;

    public JedisPool getJdRedisClient() {
        return jedisPool;
    }

    public void setJdRedisClient(JedisPool jdRedisClient) {
        this.jedisPool = jdRedisClient;
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        jedisPool.returnResource(jedis);
    }

    @Override
    public String getSet(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String s =  jedis.getSet(key, value);
        jedisPool.returnResource(jedis);
        return s;
    }

    @Override
    public String get(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        String s = jdRedisClient.get(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return s;
    }

    @Override
    public boolean exists(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        boolean re = jdRedisClient.exists(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long decrBy(String key, long step) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.decrBy(key, step);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long decr(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.decr(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long incrBy(String key, long step) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.incrBy(key, step);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long incr(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.incr(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public boolean hset(String key, String field, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        boolean re = jdRedisClient.hset(key, field, value) > 0;
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public void hmset(String key, Map hash) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        jdRedisClient.hmset(key, hash);
        getJdRedisClient().returnResource(jdRedisClient);
    }

    @Override
    public String hget(String key, String field) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        String re = jdRedisClient.hget(key, field);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Boolean hsetnx(String key, String field, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Boolean re = jdRedisClient.hsetnx(key,field,value) > 0;
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.hmget(key, fields);
    }

    @Override
    public Long hincrBy(String key, String field,  long delta) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.hincrBy(key,field,delta);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Map<String,String> re = jdRedisClient.hgetAll(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long sadd(String key, String... values) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.sadd(key,values);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Set<String> smembers(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<String> re = jdRedisClient.smembers(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long srem(String key, String... values) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.srem(key,values);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public String spop(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        String re = jdRedisClient.spop(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long scard(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.scard(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Boolean sismember(String key, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Boolean re = jdRedisClient.sismember(key,value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public String srandmember(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        String re = jdRedisClient.srandmember(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Boolean zadd(String key,  double score, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Boolean re = jdRedisClient.zadd(key,score,value)>0;
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long zadd(String key, Map< String,Double> tupleMap) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.zadd(key,tupleMap);
    }

    @Override
    public Set<String> zRange(String key, long begin, long end)  {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<String> re = jdRedisClient.zrange(key,begin,end);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long zrem(String key, String... values) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.zrem(key,values);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Double zincrby(String key,  double increment, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Double re = jdRedisClient.zincrby(key, increment, value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long zrank(String key, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.zrank(key,value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long zrevrank(String key, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.zrevrank(key,value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Set<String> zrevrange(String key,  long begin,  long end) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<String> re = jdRedisClient.zrevrange(key,begin,end);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Set<CacheTuple> zrangeWithScores(String key, long begin, long end) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> returnSet = jdRedisClient.zrangeWithScores(key, begin, end);
        return convertTuple(returnSet);
    }

    @Override
    public Set<CacheTuple> zrevrangeWithScores(String key, long min, long max) {
        Jedis jedis = getJdRedisClient().getResource();
        Set<Tuple> returnSet = jedis.zrevrangeWithScores(key, min, max);
        getJdRedisClient().returnResource(jedis);
        return convertTuple(returnSet);
    }

    Set<CacheTuple> convertTuple(Set<Tuple> returnSet){
        Set<CacheTuple> tupleSet = new HashSet<CacheTuple>();
        for (Tuple stringTuple : returnSet) {
            tupleSet.add(new CacheTuple(stringTuple.getElement(), stringTuple.getScore()));
        }
        return tupleSet;
    }

    @Override
    public Long zcard(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.zcard(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Double zscore(String key, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Double re = jdRedisClient.zscore(key,value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long zcount(String key,  double min,  double max) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.zcount(key,min,max);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }


    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<String> re = jdRedisClient.zrangeByScore(key,min,max);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }


    @Override
    public Set<String> zrevrangeByScore(String key,  double min,  double max) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<String> re = jdRedisClient.zrevrangeByScore(key,min,max);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Set<String> zrangeByScore(String key,  double min,  double max,  int offset, int count) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<String> re = jdRedisClient.zrangeByScore(key,min,max,offset,count);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }


    @Override
    public Set<String> zrevrangeByScore(String key, double min, double max,int offset, int count) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<String> re = jdRedisClient.zrevrangeByScore(key,min,max,offset,count);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Set<CacheTuple> zrangeByScoreWithScores(String key,  double min,  double max) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> tupleSet = jdRedisClient.zrangeByScoreWithScores(key, min, max);
        getJdRedisClient().returnResource(jdRedisClient);
        return convertTuple(tupleSet);
    }

    @Override
    public Set<CacheTuple> zrevrangeByScoreWithScores(String key, double min, double max) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> tupleSet = jdRedisClient.zrevrangeByScoreWithScores(key, min, max);
        getJdRedisClient().returnResource(jdRedisClient);
        return convertTuple(tupleSet);
    }

    @Override
    public Set<CacheTuple> zrangeByScoreWithScores(String key, double min, double max,int offset, int count) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> tupleSet = jdRedisClient.zrangeByScoreWithScores(key, min, max, offset, count);
        getJdRedisClient().returnResource(jdRedisClient);
        return convertTuple(tupleSet);
    }


    @Override
    public Set<CacheTuple> zrevrangeByScoreWithScores(String key, double min, double max,int offset, int count) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<Tuple> tupleSet = jdRedisClient.zrevrangeByScoreWithScores(key,min,max,offset,count);
        getJdRedisClient().returnResource(jdRedisClient);
        return convertTuple(tupleSet);
    }


    @Override
    public Long zremrangeByScore(String key,  double min,  double max) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.zremrangeByScore(key,min,max);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public boolean hexists(String key, String field) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        boolean re = jdRedisClient.hexists(key, field);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long del(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.del(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    public String type(String key){
        Jedis jdRedisClient = getJdRedisClient().getResource();
        String re = jdRedisClient.type(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    public String type(byte[] key){
        Jedis jdRedisClient = getJdRedisClient().getResource();
        String re = jdRedisClient.type(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.hdel(key, field);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long hlen(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.hlen(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Set<String> hkeys(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Set<String> re = jdRedisClient.hkeys(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public List<String> hvals(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        List<String> re = jdRedisClient.hvals(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public boolean expire(String key, int expiredTime, TimeUnit unit) {
        Long time = unit.toSeconds(expiredTime);
        Jedis jdRedisClient = getJdRedisClient().getResource();
        boolean re = jdRedisClient.expire(key, time.intValue()) > 0;
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    public boolean expireAt(String key, Date date ) {
        if(date == null){
            return false;
        }
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long unixTime = date.getTime() / 1000L;
        boolean re = jdRedisClient.expireAt(key, unixTime.intValue()) > 0;
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    public long ttl(String key){
        Jedis jdRedisClient = getJdRedisClient().getResource();
        return jdRedisClient.ttl(key);
    }

    @Override
    public Long lpush(String key, String... value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.lpush(key, value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public String rpop(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        String re = jdRedisClient.rpop(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long lpushx(String key, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.lpushx(key,value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long rpushx(String key, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.rpushx(key,value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public Long rpush(String key, String... value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.rpush(key, value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public String lpop(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        String re = jdRedisClient.lpop(key);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public boolean setnx(String key, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        boolean re = jdRedisClient.setnx(key, value) > 0;
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public boolean setnx(String key, TimeUnit unit, int expiredTime, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        String s = jdRedisClient.set(key, value,"nx","ex",unit.convert(expiredTime,unit)) ;
        getJdRedisClient().returnResource(jdRedisClient);
        return "OK".equals(s);
    }

    @Override
    public Object getObject(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        try {
            byte[] data = jdRedisClient.get(key.getBytes());
            if (data == null)
                return null;
            else
                return toObject(data);
        } catch (Exception ex) {
            throw new RuntimeException("get error." + ex.getMessage());
        }finally {
            getJdRedisClient().returnResource(jdRedisClient);
        }

    }

    @Override
    public void setObject(String key, Object value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        try {
            jdRedisClient.set(key.getBytes(), toByteArray(value));
        } catch (Exception ex) {
            throw new RuntimeException("set error." + ex.getMessage());
        }finally {
            getJdRedisClient().returnResource(jdRedisClient);
        }

    }


    @Override
    public boolean setex(String key, TimeUnit unit, int expiredTime, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        try {
            Long seconds = unit.toSeconds(expiredTime);
            jdRedisClient.setex(key, seconds.intValue(), value);
            return true;
        }catch (Exception ex){
            throw new RuntimeException("setex error." + ex.getMessage());
        }finally {
            getJdRedisClient().returnResource(jdRedisClient);
        }
    }

    @Override
    public Long llen(String key) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        try {
            return jdRedisClient.llen(key);
        }catch (Exception ex){
            throw new RuntimeException("llen error." + ex.getMessage());
        }finally {
            getJdRedisClient().returnResource(jdRedisClient);
        }
    }

    @Override
    public List<String> lrange(String key,  long begin,  long end) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        List<String> re = jdRedisClient.lrange(key,begin,end);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    @Override
    public void ltrim(String key,  long begin,  long end) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        jdRedisClient.ltrim(key,begin,end);
        getJdRedisClient().returnResource(jdRedisClient);
    }

    @Override
    public String lindex(String key,long index) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        try{
            return jdRedisClient.lindex(key,index);
        }catch (Exception ex){
            throw new RuntimeException("lindex error." + ex.getMessage());
        }finally {
            getJdRedisClient().returnResource(jdRedisClient);
        }
    }

    @Override
    public void lset(String key,  long index, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        jdRedisClient.lset(key,index,value);
    }

    @Override
    public Long lrem(String key,  long count, String value) {
        Jedis jdRedisClient = getJdRedisClient().getResource();
        Long re = jdRedisClient.lrem(key,count,value);
        getJdRedisClient().returnResource(jdRedisClient);
        return re;
    }

    /**
     * 对象转数组
     *
     * @param obj
     * @return
     */
    private byte[] toByteArray(Object obj) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (bos != null)
                bos.close();
            if (oos != null)
                oos.close();
        }
        return bytes;
    }

    /**
     * 数组转对象
     *
     * @param bytes
     * @return
     */
    private Object toObject(byte[] bytes) throws IOException {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (ois != null)
                ois.close();
            if (bis != null)
                bis.close();
        }
        return obj;
    }
}
