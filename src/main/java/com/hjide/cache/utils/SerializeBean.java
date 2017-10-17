package com.hjide.cache.utils;

import com.hjide.cache.common.Serialize;
import com.hjide.cache.utils.type.Type;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象转换关系
 * <p/>
 * User: xuxianjun
 * Date: 14-5-9
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
public class SerializeBean
{
    protected static Map<Class, List<SerializeField>> serializeFieldMap =
        new ConcurrentHashMap<Class, List<SerializeField>>();

    private static Log log = LogFactory.getLog("SerializeBean");

    public static List<SerializeField> getSerializeField(Class clazz)
        throws IntrospectionException
    {
        List<SerializeField> fieldList = serializeFieldMap.get(clazz);
        if (fieldList == null)
        {
            fieldList = new ArrayList<SerializeField>();
            List<Field> fields = getAllField(clazz);
            for (Field field : fields)
            {
                if (isNeed(field))
                {
                    SerializeField serializeField = new SerializeField();

                    // 获取序列换字段名：默认取自定义，否则取属性名称
                    Serialize serialize = field.getAnnotation(Serialize.class);
                    String fieldName = serialize.name();
                    if (StringUtils.isBlank(fieldName))
                    {
                        fieldName = field.getName();
                    }
                    serializeField.field = field;
                    serializeField.propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
                    serializeField.serializeName = fieldName;
                    serializeField.type = Type.isPrimitive(field.getType());
                    fieldList.add(serializeField);
                }
            }
            serializeFieldMap.put(clazz, fieldList);
        }
        return fieldList;
    }

    private static List<Field> getAllField(Class clazz)
    {
        List<Field> fieldList = new ArrayList<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields)
        {
            fieldList.add(field);
        }
        Class superClazz = clazz.getSuperclass();
        if (superClazz == null)
        {
            return fieldList;
        }
        fieldList.addAll(getAllField(superClazz));
        return fieldList;
    }

    /**
     * 序列化对象
     *
     * @param bean
     * @return
     */
    public static Map<String, String> serializeObj(Object bean)
    {
        try
        {
            Map<String, String> fieldMap = new HashMap<String, String>();
            List<SerializeField> fields = getSerializeField(bean.getClass());
            for (SerializeField serializeField : fields)
            {
                serializeField.putToMap(bean, fieldMap);
            }
            return fieldMap;
        }
        catch (Exception e)
        {
            log.error("序列化对象异常：" + e);
        }
        return null;
    }

    /**
     * 反序列化对象
     *
     * @param bean
     * @param obj
     * @return
     */
    public static Object deserialization(Object bean, Map<String, String> obj)
    {
        try
        {
            if (obj == null || obj.isEmpty())
            {
                return null;
            }

            List<SerializeField> fields = getSerializeField(bean.getClass());
            for (SerializeField serializeField : fields)
            {

                serializeField.writeToBean(bean, obj);
            }
        }
        catch (Exception e)
        {
            log.error("反序列化对象异常" + e);
        }
        return bean;
    }

    /**
     * 判断自动是否需要序列化或者反序列化
     *
     * @param field
     * @return
     */
    private static boolean isNeed(Field field)
    {
        Type primitive = Type.isPrimitive(field.getType());
        if (primitive == null)
        {
            return false;
        }
        if (field.getName() == null)
        {
            return false;
        }
        if (!field.isAnnotationPresent(Serialize.class))
        {
            return false;
        }
        if (Modifier.isStatic(field.getModifiers()))
        {
            return false;
        }
        return true;
    }

    public static class SerializeField
    {
        public Field field;

        public PropertyDescriptor propertyDescriptor;

        public String serializeName;

        public Type type;

        void putToMap(Object bean, Map<String, String> fieldMap)
        {
            Method readMethod = propertyDescriptor.getReadMethod();
            if (readMethod == null)
            {
                return;
            }
            try
            {
                Object value = readMethod.invoke(bean);
                if (value == null)
                {
                    return;
                }
                String stringValue = type.getString(value);
                if (stringValue != null)
                {
                    fieldMap.put(serializeName, stringValue);
                }
            }
            catch (Exception e)
            {
                log.error("读取bean属性值异常,类名=" + bean.getClass().getName() +
                    ",属性名:" + propertyDescriptor.getName(), e);
            }

        }

        void writeToBean(Object bean, Map<String, String> fieldMap)
        {

            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod == null)
            {
                return;
            }
            try
            {
                Object value = type.
                    getValue(fieldMap.get(serializeName));
                if (value == null)
                {
                    return;
                }
                writeMethod.invoke(bean, value);
            }
            catch (Exception e)
            {
                log.error(propertyDescriptor.getName() + "=" + fieldMap.get(propertyDescriptor.getName()), e);
            }
        }
    }

}
