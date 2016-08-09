package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

public class IntegerType extends Type<Integer> {

    public Integer getValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }


    @Override
    public String getString(Object value) {
        return value == null ? null : Integer.toString((Integer) value);
    }
}