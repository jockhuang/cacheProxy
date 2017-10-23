package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class DateType extends Type<Date>
{
    public Date getValue(String value)
    {
        if (StringUtils.isBlank(value))
        {
            return null;
        }
        Date date = new Date(Long.parseLong(value));
        return date;
    }

    public String getString(Object o)
    {
        Date date = (Date)o;
        return String.valueOf(date.getTime());
    }
}
