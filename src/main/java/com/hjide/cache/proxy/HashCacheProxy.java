package com.hjide.cache.proxy;

import java.util.List;
import java.util.Map;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public interface HashCacheProxy {

    //has相关方法
    public boolean hset(String key, String field, String value);

    public void hmset(String key, Map<String, String> hash);

    public String hget(String key, String field);

    public List<String> hmget(String key, String... fields);

    public Map<String, String> hgetAll(String key);

    public boolean hexists(String key, String field);

    public Long hdel(String key, String... field);

}
