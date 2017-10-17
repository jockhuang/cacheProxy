package com.hjide.cache.utils.type;

import org.apache.commons.lang.StringUtils;

public class CharacterType extends Type<Character>
{

    public Character getValue(String value)
    {
        if (StringUtils.isBlank(value))
        {
            return null;
        }
        return value.toCharArray()[0];
    }

    @Override
    public String getString(Object value)
    {
        return value == null ? null : Character.toString((Character)value);
    }
}