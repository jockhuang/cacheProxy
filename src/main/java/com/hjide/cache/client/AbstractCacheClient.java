package com.hjide.cache.client;

/**
 * cache com.hjide.cache.client
 * Created by jock on 2017/10/23.
 */
public abstract class AbstractCacheClient implements CacheClientProxy
{
    /**
     * 对象转数组
     *
     * @param obj Object
     * @return byte[]
     */
    protected byte[] toByteArray(Object obj)
        throws java.io.IOException
    {
        byte[] bytes = null;
        java.io.ByteArrayOutputStream bos = null;
        java.io.ObjectOutputStream oos = null;
        try
        {
            bos = new java.io.ByteArrayOutputStream();
            oos = new java.io.ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();

        }
        catch (java.io.IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (bos != null)
                bos.close();
            if (oos != null)
                oos.close();
        }
        return bytes;
    }

    /**
     * 数组转对象
     *
     * @param bytes byte[]
     * @return Object
     */
    protected Object toObject(byte[] bytes)
        throws java.io.IOException
    {
        Object obj = null;
        java.io.ByteArrayInputStream bis = null;
        java.io.ObjectInputStream ois = null;
        try
        {
            bis = new java.io.ByteArrayInputStream(bytes);
            ois = new java.io.ObjectInputStream(bis);
            obj = ois.readObject();

        }
        catch (java.io.IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (ois != null)
                ois.close();
            if (bis != null)
                bis.close();
        }
        return obj;
    }
}
