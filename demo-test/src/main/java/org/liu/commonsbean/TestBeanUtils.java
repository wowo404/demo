package org.liu.commonsbean;

import org.apache.commons.beanutils.BeanUtils;
import org.liu.model.Animal;
import org.liu.model.Monkey;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * test
 *
 * @author liuzhangsheng
 * @create 2018/9/12
 */
public class TestBeanUtils {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Monkey monkey = new Monkey();
        monkey.setColor("red");
        monkey.setAge(11);

        Monkey monkey1 = new Monkey();
        monkey1.setColor("green");
        monkey1.setAge(12);

        List<Monkey> list = new ArrayList<>();
        list.add(monkey);
        list.add(monkey1);

        List<Monkey> o = (List<Monkey>) BeanUtils.cloneBean(list);
        System.out.println(o);

        Map<String, String> describe = BeanUtils.describe(monkey);//map中包含了一个class
        for (Map.Entry<String, String> entry : describe.entrySet()) {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        }
    }

    public static void run(){
        System.out.println(TestBeanUtils.class.getSimpleName());
        Monkey monkey = new Monkey();
        monkey.setColor("red");
        monkey.setAge(11);

        Map<String, Object> map = test(monkey);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
    }

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

    public static Map<String, Object> test(Animal animal){
        return transBean2Map(animal);
    }

    public static Map<String, Object> transBean2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

}
