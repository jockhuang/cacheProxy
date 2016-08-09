package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

public class StringType extends Type<String> {

    public String getValue(String value) {
        return value;
    }

    @Override
    public String getString(Object value) {
        return StringUtils.isBlank((String) value) ? "" : value.toString();
    }

}