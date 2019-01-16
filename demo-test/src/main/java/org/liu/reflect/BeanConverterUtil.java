package org.liu.reflect;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * bean转换map
 *
 * @author liuzhangsheng
 * @create 2018/9/12
 */
public class BeanConverterUtil {

    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> transBean2Map(Object obj, String... excludes) {
        Map<String, Object> map = new HashMap<>();
        try {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            putFields(obj, clazz, fields, map, excludes);
            Class<?> superclass = clazz.getSuperclass();
            if (superclass.getSimpleName().equals("Object")) {
                return map;
            }
            Field[] declaredFields = superclass.getDeclaredFields();
            putFields(obj, clazz, declaredFields, map, excludes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private static void putFields(Object obj, Class<?> clazz, Field[] fields, Map<String, Object> map, String... excludes) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Field field : fields) {
            String name = field.getName();
            for(String exclude : excludes){
                if (name.equalsIgnoreCase(exclude)) {
                    continue;
                }
            }
            Method method = clazz.getMethod("get" + name.substring(0,1).toUpperCase() + name.substring(1));
            Object value = method.invoke(obj);
            if (null == value) {
                return;
            }
            if (value.getClass().isArray()) {
                //无需改动value
            } else if(value instanceof Map){
                //暂时不实现
            } else if(value instanceof Collection){
                //目前有List<Object>的情况，需要解析Object
                List<Object> list = (List<Object>) value;
                Class<?> aClass = list.get(0).getClass();
                if (aClass.isPrimitive() || aClass == String.class) {
                    //无需改动value
                } else {
                    List<Object> newList = new ArrayList<>();
                    for (Object o : list) {
                        Map<String, Object> objToMap = transBean2Map(o, excludes);
                        newList.add(objToMap);
                    }
                    value = newList;
                }
            } else if(obj.getClass().isPrimitive() || obj.getClass() == String.class){
                //无需改动value
            }
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            if (null != jsonProperty) {
                name = jsonProperty.value();
            }
            map.put(name, value);
        }
    }

}
