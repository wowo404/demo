package org.liu.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.liu.model.Animal;
import org.liu.model.Monkey;

import com.alibaba.fastjson.JSON;
import org.liu.model.MonkeySons;

public class TestReflect {

	public static void main(String[] args) throws ClassNotFoundException {
		TestReflect t = new TestReflect();

        Monkey monkey = new Monkey();
		monkey.setColor("red");
		monkey.setId(1);
		monkey.setName("gucci");
		t.test(monkey);

		t.convert();
	}

	public void convert(){
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

	
	public <T extends Animal> T test(T t){
		Type clazz = t.getClass().getGenericSuperclass();
        Class<?> superclass = t.getClass().getSuperclass();
        Type[] interfaces = t.getClass().getGenericInterfaces();
        Class<?>[] declaredClasses = t.getClass().getDeclaredClasses();
        Class<?>[] classes = t.getClass().getClasses();
        Class<? extends Animal> aClass = t.getClass();
        Class<?> declaringClass = t.getClass().getDeclaringClass();
        Class<?> enclosingClass = t.getClass().getEnclosingClass();
        Field[] fields = t.getClass().getFields();

        String s = JSON.toJSONString(t);
		return t;
	}

}
