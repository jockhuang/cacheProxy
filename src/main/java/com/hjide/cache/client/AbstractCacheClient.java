package com.hjide.cache.client;

import lombok.Cleanup;
import lombok.NonNull;

public abstract class AbstractCacheClient implements CacheClientProxy
{
    /**
     * 对象转数组
     *
     * @param obj Object
     * @return byte[]
     */
    protected byte[] toByteArray(@NonNull Object obj)
        throws java.io.IOException
    {
        byte[] bytes = null;
        @Cleanup java.io.ByteArrayOutputStream bos = null;
        @Cleanup java.io.ObjectOutputStream oos = null;
        bos = new java.io.ByteArrayOutputStream();
        oos = new java.io.ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        bytes = bos.toByteArray();

        return bytes;
    }

    /**
     * 数组转对象
     *
     * @param bytes byte[]
     * @return Object
     */
    Object toObject(@NonNull byte[] bytes)
        throws java.io.IOException
    {
        Object obj = null;
        @Cleanup java.io.ByteArrayInputStream bis = null;
        @Cleanup java.io.ObjectInputStream ois = null;
        bis = new java.io.ByteArrayInputStream(bytes);
        ois = new java.io.ObjectInputStream(bis);
        try
        {
            obj = ois.readObject();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return obj;
    }
}
