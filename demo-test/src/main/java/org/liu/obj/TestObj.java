package org.liu.obj;

/**
 * main
 *
 * @author liuzhangsheng
 * @create 2018/9/1
 */
public class TestObj {

    public static void main(String[] args) {
        char a = 'a';
        TestObj test = new TestObj();
        char[] i = (char[])test.transferStringToBasicDataType(String.valueOf(a), char.class);
    }

    public Object test(int i) {
        switch (i) {
            case 0:
                return "1";
            case 1:
                return "0.1";
            case 2:
                return "19181712334461651273";
            case 3:
                return "";
            case 4:
                return "";
            default:
                return null;
        }
    }

    private Object transferStringToBasicDataType(String value, Class<?> clazz) {
        if (clazz.equals(String.class)) {
            return value;
        }
        if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            return Integer.valueOf(value);
        }
        if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
            return Byte.valueOf(value);
        }
        if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            return Long.valueOf(value);
        }
        if (clazz.equals(Double.class) || clazz.equals(double.class)) {
            return Double.valueOf(value);
        }
        if (clazz.equals(Float.class) || clazz.equals(float.class)) {
            return Float.valueOf(value);
        }
        if (clazz.equals(Character.class) || clazz.equals(char.class)) {
            return value.toCharArray();
        }
        if (clazz.equals(Short.class) || clazz.equals(short.class)) {
            return Short.valueOf(value);
        }
        if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            return Boolean.valueOf(value);
        }
        return null;
    }

}
