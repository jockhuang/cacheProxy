package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xuxianjun
 * Date: 14-5-7
 * Time: 下午6:13
 * To change this template use File | Settings | File Templates.
 */
public class DateType extends Type<Date> {
    public Date getValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        Date date = new Date(Long.parseLong(value));
        return date;
    }

    public String getString(Object o) {
        Date date = (Date) o;
        return String.valueOf(date.getTime());
    }
}
