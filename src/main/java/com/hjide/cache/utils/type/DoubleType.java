package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

public class DoubleType extends Type<Double> {

    public Double getValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Double.valueOf(value);
    }


    @Override
    public String getString(Object value) {
        return value == null ? null : Double.toString((Double) value);
    }
}