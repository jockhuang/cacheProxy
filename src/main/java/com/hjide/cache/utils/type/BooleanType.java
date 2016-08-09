package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

public class BooleanType extends Type<Boolean> {

    public Boolean getValue(String value) {
        if (StringUtils.isBlank(value)) {
            return Boolean.FALSE;
        }
        return Boolean.valueOf(value);
    }

    @Override
    public String getString(Object value) {
        return value == null ? null : Boolean.toString((Boolean) value);
    }
}