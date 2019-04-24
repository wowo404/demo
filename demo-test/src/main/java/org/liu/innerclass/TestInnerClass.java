package org.liu.innerclass;

/**
 * 内部类
 *
 * @author liuzhangsheng
 * @create 2019/4/11
 */
public class TestInnerClass {

    static class Main {
        public int i = 10;

        public synchronized void operationSup() {
            try {
                i--;
                System.out.println("Main print i = " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Sub extends Main { //没有使用static修饰的内部类不能直接创建对象
        public synchronized void operationSub() {
            try {
                while (i > 0) {
                    i--;
                    System.out.println("Sub print i = " + i);
                    Thread.sleep(100);
                    this.operationSup();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test(){
        Sub sub = new Sub();//非static方法可以引用非static修饰的内部类
        sub.operationSub();
    }

    public static void main(String[] args) {

        TestInnerClass test = new TestInnerClass();
        //Sub sub = new Sub(); //static方法不能引用非静态变量this，没有使用static修饰的内部类不能直接创建对象
        test.new Sub();//正确的使用方法

        Main main = new Main();//可以直接创建static内部类的对象
        main.operationSup();

    }

}
