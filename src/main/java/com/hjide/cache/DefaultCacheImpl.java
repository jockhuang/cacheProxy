package com.hjide.cache;


import com.hjide.cache.client.CacheClientProxy;

/**
 * Created by jock on 15/12/24.
 */
public class DefaultCacheImpl extends AbstractCacheImpl {

    private CacheClientProxy cacheClientProxy;

    @Override
    protected CacheClientProxy getClientProxy() {
        return cacheClientProxy;
    }

    public void setCacheClientProxy(CacheClientProxy cacheClientProxy){
        this.cacheClientProxy = cacheClientProxy;
    }




}
