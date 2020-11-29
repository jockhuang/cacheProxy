package com.hjide.cache;

import com.hjide.cache.client.CacheClientProxy;

public class DefaultCacheImpl extends AbstractCacheImpl
{

    private CacheClientProxy cacheClientProxy;

    @Override
    protected CacheClientProxy getClientProxy()
    {
        return cacheClientProxy;
    }

    public void setCacheClientProxy(CacheClientProxy cacheClientProxy)
    {
        this.cacheClientProxy = cacheClientProxy;
    }

}
