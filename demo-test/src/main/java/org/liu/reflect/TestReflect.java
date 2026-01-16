package org.liu.reflect;

import com.alibaba.fastjson.JSON;
import org.liu.model.Monkey;
import org.liu.model.MonkeySons;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public class TestReflect {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        TestReflect t = new TestReflect();

//        t.testField();
        Monkey monkey = new Monkey();
        monkey.setColor("red");
        monkey.setId(1);
        monkey.setName("gucci");
        t.test(monkey);
    }

    public void convert() {
        List<MonkeySons> sons = new ArrayList<>();
        MonkeySons sons1 = new MonkeySons();
        sons1.setSonName("zhang");
        sons.add(sons1);

        MonkeySons sons2 = new MonkeySons();
        sons2.setSonName("liu");
        sons.add(sons2);

        Monkey monkey = new Monkey();
        monkey.setColor("red");
        monkey.setId(1);
        monkey.setName("gucci");
        monkey.setSons(sons);

        Map<String, Object> stringObjectMap = BeanConverterUtil.transBean2Map(monkey);
        System.out.println(stringObjectMap);
    }


    public <T> void test(T t) {
        System.out.println("------------类的相关信息----------");
        Type clazz = t.getClass().getGenericSuperclass();
        Class<?> superclass = t.getClass().getSuperclass();
        Class<?>[] interfaces = t.getClass().getInterfaces();
        Type[] genericInterfaces = t.getClass().getGenericInterfaces();
        Class<?>[] declaredClasses = t.getClass().getDeclaredClasses();
        Class<?>[] classes = t.getClass().getClasses();
        Class<?> aClass = t.getClass();
        Class<?> declaringClass = t.getClass().getDeclaringClass();
        Class<?> enclosingClass = t.getClass().getEnclosingClass();
        System.out.println("------------构造方法的相关信息----------");
        Constructor<?>[] constructors = t.getClass().getConstructors();
        Constructor<?>[] declaredConstructors = t.getClass().getDeclaredConstructors();
        System.out.println("------------字段的相关信息----------");
        Field[] declaredFields = t.getClass().getDeclaredFields();
        Field[] fields = t.getClass().getFields();
        System.out.println("------------方法的相关信息----------");
        Method[] declaredMethods = t.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName() + " -- " + Arrays.toString(declaredMethod.getParameterTypes()));
            if (declaredMethod.getParameterTypes().length > 0) {
                System.out.println(declaredMethod.getParameterTypes()[0].equals(String.class));
            }
        }
        System.out.println("----------------------------");
        Method[] methods = t.getClass().getMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " -- " + Arrays.toString(method.getParameterTypes()));
        }
        System.out.println("------------类的注解的相关信息----------");
        Annotation[] annotations = t.getClass().getAnnotations();
        Annotation[] declaredAnnotations = t.getClass().getDeclaredAnnotations();
        AnnotatedType[] annotatedInterfaces = t.getClass().getAnnotatedInterfaces();
        AnnotatedType annotatedSuperclass = t.getClass().getAnnotatedSuperclass();

        String s = JSON.toJSONString(t);
        System.out.println(s);
    }

    public void testField() throws IllegalAccessException {
        Monkey monkey = new Monkey();
        Field[] fields = Monkey.class.getFields();
        System.out.println("getFields:" + Arrays.toString(fields));
        Field[] declaredFields = Monkey.class.getDeclaredFields();
        System.out.println("getDeclaredFields:" + Arrays.toString(declaredFields));
        for (Field declaredField : declaredFields) {
            System.out.println("------------------------");
            System.out.println("名称：" + declaredField.getName());
            System.out.println("修饰符：" + Modifier.toString(declaredField.getModifiers()));
            System.out.println("是否static：" + Modifier.isStatic(declaredField.getModifiers()));
            System.out.println("类型：" + declaredField.getType());
            System.out.println("是否可访问：" + declaredField.isAccessible());
            System.out.println("是否合成字段：" + declaredField.isSynthetic());
            System.out.println("通用类型：" + declaredField.getGenericType());
            Class<?> type = declaredField.getType();
            boolean assignableFrom = Collection.class.isAssignableFrom(type);
            System.out.println("Collection's sub class:" + assignableFrom + "-----" + type);
            System.out.println("定义在哪个类型中？：" + declaredField.getDeclaringClass());
            declaredField.setAccessible(true);
            Object fieldValue = declaredField.get(monkey);
            System.out.println("字段值：" + fieldValue);
        }

        System.out.println();
    }

    public void testClass() {
        Class<? extends Collection> aClass = List.class.asSubclass(Collection.class);
        System.out.println(aClass);
    }

}
