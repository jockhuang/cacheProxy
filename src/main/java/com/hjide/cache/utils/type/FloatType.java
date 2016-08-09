package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

public class FloatType extends Type<Float> {

    public Float getValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Float.valueOf(value);
    }


    @Override
    public String getString(Object value) {
        return value == null ? null : Float.toString((Float) value);
    }
}