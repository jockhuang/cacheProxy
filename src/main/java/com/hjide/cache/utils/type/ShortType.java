package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

public class ShortType extends Type<Short> {

    public Short getValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Short.valueOf(value);
    }

    @Override
    public String getString(Object value) {
        return value == null ? null : Short.toString((Short) value);
    }
}