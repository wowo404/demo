package org.liu.objectreflection;

import java.lang.reflect.Field;
import java.util.List;

public class TestObjectReflection {

    public static void main(String[] args) {
        TestObjectReflection test = new TestObjectReflection();
        test.testFieldIsSynthetic();
    }

    public boolean isBaseDataType(Class clazz) {
        return clazz.equals(Integer.class) ||
                clazz.equals(int.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(byte.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(long.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(double.class) ||
                clazz.equals(Float.class) ||
                clazz.equals(float.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(char.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(short.class) ||
                clazz.equals(Boolean.class) ||
                clazz.equals(boolean.class)||
                clazz.equals(String.class);
    }

    /**
     * 通过如下测试可以看出，类自己定义的字段，全部是false，jdk生成的字段或者方法才是true
     */
    public void testFieldIsSynthetic() {
        Field[] fields = TestFieldIsSynthetic.class.getDeclaredFields();
        for (Field field : fields) {
//            System.out.println(field + " -- " + field.isSynthetic());
//            System.out.println(field.getDeclaringClass() + " -- " + field.getType() + " -- " + field.getGenericType() + " -- " + field.getClass());
//            System.out.println(isBaseDataType(field.getType()));
            System.out.println(field.getName());
        }
    }

    class TestFieldIsSynthetic {
        private int id;//原始类型
        private Integer type;//包装类型
        private String name;
        private List<String> list;
        private FieldSon fieldSon;//引用类型
    }

    class FieldSon {
        private int id;
    }

}
