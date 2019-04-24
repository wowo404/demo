package org.liu.innerclass;

/**
 * main
 *
 * @author liuzhangsheng
 * @create 2019/4/11
 */
public class TestInnerClassMain {

    public void test(){
        //static内部类
        TestInnerClass.Main main = new TestInnerClass.Main();
        //非static内部类
        TestInnerClass test = new TestInnerClass();
        TestInnerClass.Sub sub = test.new Sub();
    }

    //这种创建方式，在static方法内创建和非static方法内创建没有区别
    public static void main(String[] args) {
        TestInnerClass.Main main = new TestInnerClass.Main();

        TestInnerClass test = new TestInnerClass();
        TestInnerClass.Sub sub = test.new Sub();
    }

}
