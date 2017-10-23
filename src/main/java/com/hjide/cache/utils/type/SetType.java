package com.hjide.cache.utils.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashSet;
import java.util.Set;

public class SetType extends Type<Set>
{

    @Override
    public Set getValue(String value)
    {
        return JSON.parseObject(value, HashSet.class);
    }

    @Override
    public String getString(Object value)
    {
        return JSON.toJSONString(value, SerializerFeature.WriteClassName);
    }
}
