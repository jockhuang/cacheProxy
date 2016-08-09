package com.hjide.cache.proxy;

import java.util.Set;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public interface SetCacheProxy {

    Long sadd(String key, String... values);

    Set<String> smembers(String key);

    Long srem(String key, String... values);

    String spop(String key);

    Long scard(String key);

    Boolean sismember(String key, String value);

    String srandmember(String key);

}
