package com.hjide.cache.proxy;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public interface ObjectCacheProxy {


    @Deprecated
    public void set(String key, Object value, TimeUnit unit, int expiredTime);

    public Object getObject(String key);

    public void setObject(String key, Object value);
}
