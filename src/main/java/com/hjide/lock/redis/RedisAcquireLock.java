package com.hjide.lock.redis;

import com.hjide.cache.CacheProxy;
import com.hjide.lock.AcquireLock;
import com.hjide.lock.exception.ConcurrentLockException;
import com.hjide.lock.handle.LockKeyClient;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: xuxianjun
 * Date: 15-1-29
 * Time: 下午8:40
 * To change this template use File | Settings | File Templates.
 */
public class RedisAcquireLock implements AcquireLock<String>
{

    /**
     * 默认redis key超时30秒
     */
    private final static int DEFAULT_EXPIRE_SECONDS = 30;

    private final static long DEFAULT_CHECK_EXPIRE_NUM = 5;

    /**
     * 资源被锁定中，被其它请求试着获取锁次数达到该值后，
     * 系统校验锁是否设置失效时间，防止锁永久占用
     */
    protected long checkExpireNum;

    /**
     * redis key锁失效时间
     */
    protected int expireSeconds;

    protected CacheProxy cacheProxy;

    public RedisAcquireLock()
    {
        expireSeconds = DEFAULT_EXPIRE_SECONDS;
        checkExpireNum = DEFAULT_CHECK_EXPIRE_NUM;
    }

    /**
     * 获取执行锁
     *
     * @param lockKey
     * @return
     * @throws {@link ConcurrentLockException}
     */
    public LockResult lock(LockKeyClient lockKey)
        throws ConcurrentLockException
    {
        if (StringUtils.isBlank(lockKey.getLockKey()))
        {
            throw new IllegalArgumentException("lockKey为空!");
        }

        if (cacheProxy == null)
        {
            throw new IllegalArgumentException("redis配置为空!");
        }

        LockResult<String> result = new LockResult<String>();
        try
        {

            long time = cacheProxy.incr(lockKey.getLockKey());
            //获取到锁
            if (time == 1)
            {
                result.setSuccess(true);
                return result;
            }
            checkExpire(lockKey, time);
        }
        finally
        {
            if (result.isSuccess())
            {
                expire(lockKey);
            }
        }
        return result;
    }

    private void checkExpire(LockKeyClient lockKey, long time)
    {
        long checkExpireTime = this.checkExpireNum;
        if (lockKey.getCheckExpireNum() > 0)
        {
            checkExpireTime = lockKey.getCheckExpireNum();
        }

        if (time > checkExpireTime
            && cacheProxy.ttl(lockKey.getLockKey()) < 0)
        {
            expire(lockKey);
        }

    }

    /**
     * 设置失效时间
     *
     * @param lockKey
     */
    private void expire(LockKeyClient lockKey)
    {
        int expire = this.expireSeconds;
        if (lockKey.getExpireSecond() > 0)
        {
            expire = lockKey.getExpireSecond();
        }
        cacheProxy.expire(lockKey.getLockKey(), expire, TimeUnit.SECONDS);
    }

    /**
     * @param lockKey
     * @param timeout
     * @param unit
     * @return
     * @throws ConcurrentLockException
     */
    public LockResult lock(LockKeyClient lockKey, long timeout, TimeUnit unit)
        throws ConcurrentLockException
    {
        if (StringUtils.isBlank(lockKey.getLockKey()))
        {
            throw new IllegalArgumentException("lockKey为空!");
        }
        if (cacheProxy == null)
        {
            throw new IllegalArgumentException("redis配置为空!");
        }
        //第一次锁失败
        LockResult result = lock(lockKey);
        if (result.isSuccess())
        {
            return result;
        }
        //等待锁被是否被释放
        long time = unit.toMillis(timeout);
        long lastTime = System.currentTimeMillis();
        try
        {
            //只有获取锁成功或等待时间过长
            while (!result.isSuccess() && time > 0)
            {
                if (waitLock(time, lockKey.getLockKey()))
                {
                    result = lock(lockKey);
                }
                //计算等待时间
                long now = System.currentTimeMillis();
                time -= now - lastTime;
                lastTime = now;
            }
        }
        catch (InterruptedException e)
        {
            throw new ConcurrentLockException("任务获取redis锁中断异常:", e);
        }
        catch (Exception e)
        {
            throw new ConcurrentLockException("任务获取redis锁异常:", e);
        }
        return result;
    }

    /**
     * @param waitTime 要等待的最长时间（以毫秒为单位）。
     * @param lockKey
     * @return true表示锁已释放
     */
    boolean waitLock(long waitTime, String lockKey)
        throws InterruptedException
    {
        long lastTime = System.currentTimeMillis();
        try
        {
            while (true)
            {

                if (waitTime <= 0)
                {
                    return false;
                }
                //单次等待
                Thread.sleep(50);
                //判断redis锁是否还占用
                if (!cacheProxy.exists(lockKey))
                {
                    return true;
                }
                //等待时间计算
                long now = System.currentTimeMillis();
                waitTime -= now - lastTime;
                lastTime = now;
                //线程被中断
                if (Thread.interrupted())
                    break;
            }
        }
        catch (RuntimeException e)
        {
            throw e;
        }
        throw new InterruptedException();
    }

    public boolean unLock(String lockKey)
    {
        return cacheProxy.del(lockKey) > 0;
    }

    public CacheProxy getCacheProxy()
    {
        return cacheProxy;
    }

    public void setCacheProxy(CacheProxy cacheProxy)
    {
        this.cacheProxy = cacheProxy;
    }

    public int getExpireSeconds()
    {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds)
    {
        this.expireSeconds = expireSeconds;
    }

    public long getCheckExpireNum()
    {
        return checkExpireNum;
    }

    public void setCheckExpireNum(long checkExpireNum)
    {
        if (checkExpireNum > 0)
        {
            this.checkExpireNum = checkExpireNum;
        }
        else
        {
            throw new IllegalArgumentException("checkExpireNum参数设置异常,必须大于0！");
        }
    }
}
