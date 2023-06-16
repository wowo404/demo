package org.liu.reflect;

import com.alibaba.fastjson.JSON;
import org.liu.model.Animal;
import org.liu.model.Monkey;
import org.liu.model.MonkeySons;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

public class TestReflect {

    public static void main(String[] args) throws ClassNotFoundException {
        TestReflect t = new TestReflect();

        t.testField();
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


    public <T extends Animal> T test(T t) {
        Type clazz = t.getClass().getGenericSuperclass();
        Class<?> superclass = t.getClass().getSuperclass();
        Type[] interfaces = t.getClass().getGenericInterfaces();
        Class<?>[] declaredClasses = t.getClass().getDeclaredClasses();
        Class<?>[] classes = t.getClass().getClasses();
        Class<? extends Animal> aClass = t.getClass();
        Class<?> declaringClass = t.getClass().getDeclaringClass();
        Class<?> enclosingClass = t.getClass().getEnclosingClass();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        Field[] fields = t.getClass().getFields();
        Method[] declaredMethods = t.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName() + " -- " + Arrays.toString(declaredMethod.getParameterTypes()));
            if (declaredMethod.getParameterTypes().length > 0 ){
                System.out.println(declaredMethod.getParameterTypes()[0].equals(String.class));
            }
        }
        System.out.println("----------------------------");
        Method[] methods = t.getClass().getMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " -- " + Arrays.toString(method.getParameterTypes()));
        }
        Annotation[] annotations = t.getClass().getAnnotations();
        Annotation[] declaredAnnotations = t.getClass().getDeclaredAnnotations();
        AnnotatedType[] annotatedInterfaces = t.getClass().getAnnotatedInterfaces();
        AnnotatedType annotatedSuperclass = t.getClass().getAnnotatedSuperclass();

        String s = JSON.toJSONString(t);
        System.out.println(s);
        return t;
    }

    public void testField() {
        Field[] fields = Monkey.class.getFields();
        Field[] declaredFields = Monkey.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Type genericType = declaredField.getGenericType();
            Class<?> type = declaredField.getType();
            boolean assignableFrom = Collection.class.isAssignableFrom(type);
            System.out.println("Collection's sub class:" + assignableFrom + "-----" + type);
            Class<?> declaringClass = declaredField.getDeclaringClass();
            System.out.println();
        }

        System.out.println();
    }

    public void testClass() {
        Class<? extends Collection> aClass = List.class.asSubclass(Collection.class);
        System.out.println(aClass);
    }

}
