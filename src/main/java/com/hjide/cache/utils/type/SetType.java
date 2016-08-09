package com.hjide.cache.utils.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xuxianjun on 2016/1/20.
 */
public class SetType extends Type<Set> {


    @Override
    public Set getValue(String value) {
        return JSON.parseObject(value,HashSet.class);
    }

    @Override
    public String getString(Object value) {
        return JSON.toJSONString(value, SerializerFeature.WriteClassName);
    }

    public static void  main(String[] args){
        Set<String > set = new HashSet<String>();
        set.add("1");
        set.add("4");
        set.add("5");
        set.add("6");
        set.add("7");
        SetType setType = new SetType();
        String json = setType.getString(set);
        System.out.println(json);
        System.out.println(setType.getValue(json));
    }
}
