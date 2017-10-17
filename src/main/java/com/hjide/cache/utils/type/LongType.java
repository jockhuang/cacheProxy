package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

public class LongType extends Type<Long>
{

    public Long getValue(String value)
    {
        if (StringUtils.isBlank(value))
        {
            return null;
        }
        return Long.valueOf(value);
    }

    public String getString(Object value)
    {
        return value == null ? null : Long.toString((Long)value);
    }
}