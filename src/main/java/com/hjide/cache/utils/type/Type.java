package com.hjide.cache.utils.type;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基本数据类型处理类
 * 作 者： chenchuan
 * 创建时间：2014-4-10
 */
public abstract class Type<T> {
    protected static Map<Class, Type> primitiveType = new HashMap<Class, Type>();

    protected static Map<Class, Type> collectionType = new HashMap<Class, Type>();

    public abstract T getValue(String value);

    public abstract String getString(Object value);


    static {

        primitiveType.put(Long.class, new LongType());
        primitiveType.put(Integer.class, new IntegerType());
        primitiveType.put(Boolean.class, new BooleanType());
        primitiveType.put(String.class, new StringType());
        primitiveType.put(Character.class, new CharacterType());
        primitiveType.put(Short.class, new ShortType());
        primitiveType.put(Float.class, new FloatType());
        primitiveType.put(Double.class, new DoubleType());
        primitiveType.put(Byte.class, new ByteType());
        primitiveType.put(long.class, new LongType());
        primitiveType.put(char.class, new CharacterType());
        primitiveType.put(int.class, new IntegerType());
        primitiveType.put(boolean.class, new BooleanType());
        primitiveType.put(short.class, new ShortType());
        primitiveType.put(float.class, new FloatType());
        primitiveType.put(double.class, new DoubleType());
        primitiveType.put(byte.class, new ByteType());
        primitiveType.put(Date.class, new DateType());
        primitiveType.put(Set.class, new SetType());
        primitiveType.put(Map.class, new MapType());
        primitiveType.put(List.class, new ListType());

        //集合类型
        collectionType.put(Set.class, new SetType());
        collectionType.put(Map.class, new MapType());
        collectionType.put(List.class, new ListType());

    }

    public static Type isPrimitive(Class clazz) {
        if (clazz != null ) {
            return primitiveType.get(clazz);
        }
//        Iterator<Map.Entry<Class, Type>> iterator = collectionType.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<Class, Type> entry = iterator.next();
//            if (entry.getKey().isAssignableFrom(clazz)) {
//                return entry.getValue();
//            }
//        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(isPrimitive(ArrayList.class));
        System.out.println(isPrimitive(List.class));
        System.out.println(isPrimitive(HashMap.class));
        System.out.println(isPrimitive(HashSet.class));
        System.out.println(isPrimitive(ConcurrentHashMap.class));

        System.out.println("=========set===========");
        Set<Long > set = new LinkedHashSet<Long>();
        set.add(1L);
        set.add(4L);
        set.add(5L);
        set.add(6L);
        set.add(7L);
        SetType setType = new SetType();
        String json = setType.getString(set);
        System.out.println(json);
        Set<Long> jsonSet = setType.getValue(json);
        System.out.println(jsonSet);

        System.out.println("=========list===========");

        List<Integer > list = new LinkedList<Integer>();
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        ListType listType = new ListType();
        json = setType.getString(list);
        System.out.println(json);
        List<Integer > jsonList = listType.getValue(json);
        System.out.println(jsonList);

        System.out.println("=========map===========");
        Map<String,String > map = new Hashtable<String, String>();
        map.put("1","1");
        map.put("4","2");
        map.put("5","5");
        map.put("6","6");
        map.put("7","7");
        MapType mapType = new MapType();
        json = mapType.getString(map);
        System.out.println(json);
        Map<String,String > jsonMap = mapType.getValue(json);
        System.out.println(jsonMap);
    }

}
