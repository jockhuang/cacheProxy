package com.hjide.cache.utils.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * Created by xuxianjun on 2016/1/20.
 */
public class ListType extends Type<List>
{

    @Override
    public List getValue(String value)
    {
        return JSON.parseObject(value, List.class);
    }

    @Override
    public String getString(Object value)
    {
        return JSON.toJSONString(value, SerializerFeature.WriteClassName);
    }
}
