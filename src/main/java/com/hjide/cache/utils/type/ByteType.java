package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

public class ByteType extends Type<Byte>
{

    public Byte getValue(String value)
    {
        if (StringUtils.isBlank(value))
        {
            return null;
        }
        return Byte.valueOf(value);
    }

    @Override
    public String getString(Object value)
    {
        return value == null ? null : Byte.toString((Byte)value);
    }
}