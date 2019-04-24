package org.liu.jdkkeyword;

/**
 * 测试jdk关键字
 *
 * @author liuzhangsheng
 * @create 2019/4/10
 */
public class TestJDKKeyword {

    public static void main(String[] args) {
        TestJDKKeyword test = new TestJDKKeyword();
        test.test();
    }

    private final String key = "key";
    private final int keyInt = 1;
    private static String keyStatic = "origin static string";

    public void test(){
        testFinal(key);
        testFinalInt(keyInt);
        testStaticString();
    }

    public void testFinal(String a){
        a = "a";
        System.out.println(a);
        System.out.println(key);
    }

    public void testFinalInt(int a){
        a = 2;
        System.out.println(a);
        System.out.println(keyInt);
    }

    public void testStaticString(){
        keyStatic = "change static string";
        System.out.println(keyStatic);
    }

}
