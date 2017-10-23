package com.hjide.lock.handle;

public interface LockKeyClient
{
    /**
     * 获取锁key
     *
     * @return
     */
    public String getLockKey();

    /**
     * 锁有效期(s)
     *
     * @return
     */
    public int getExpireSecond();

    /**
     * 校验锁失效时间阈值次数
     * <br>
     * 资源被锁定中，被其它请求试着获取锁次数达到该值后，
     * 系统校验锁是否设置失效时间，防止锁永久占用
     *
     * @return
     */
    public long getCheckExpireNum();
}
