package com.hjide.cache.serialize;

import com.hjide.cache.common.Serialize;

import java.io.Serializable;

/**
 * Created by jock on 15/9/2.
 */
public abstract class CategoryBaseDTO implements Serializable {
    @Serialize
    protected Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }



}
