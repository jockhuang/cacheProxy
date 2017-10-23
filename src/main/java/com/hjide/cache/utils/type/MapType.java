package com.hjide.cache.utils.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class MapType extends Type<Map>
{

    @Override
    public Map getValue(String value)
    {
        return JSON.parseObject(value, HashMap.class);
    }

    @Override
    public String getString(Object value)
    {
        return JSON.toJSONString(value, SerializerFeature.WriteClassName);
    }
}
