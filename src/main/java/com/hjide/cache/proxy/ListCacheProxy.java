package com.hjide.cache.proxy;

import java.util.List;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public interface ListCacheProxy
{

    //list相关方法
    public Long lpush(String key, String... value);

    public Long rpush(String key, String... value);

    public String lpop(String key);

    public String rpop(String key);

    public long llen(String key);

    public String lindex(String key, long index);

    List<String> lrange(String key, long begin, long end);

    void ltrim(String key, long begin, long end);

    void lset(String key, long index, String value);

    Long lrem(String key, long count, String value);

    Long lpushx(String key, String value);

    Long rpushx(String key, String value);
}
