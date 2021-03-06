package com.hjide.cache.proxy.impl;

import com.hjide.cache.proxy.ObjectCacheProxy;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public  class DefaultObjectCachePorxy
        extends BaseCacheProxyImpl implements ObjectCacheProxy {

    @Deprecated
    @Override
    public void setObject(String key, Object value, TimeUnit unit, int expiredTime) {
        getClientProxy().setObject(key,unit,expiredTime,value);

    }

    @Override
    public Object getObject(String key) {
        return getClientProxy().getObject(key);
    }

    @Override
    public void setObject(String key, Object value) {
        getClientProxy().setObject(key,value);
    }
}
