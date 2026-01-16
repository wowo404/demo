package org.liu.designpatterns.createschema.singleton;

/**
 * 静态内部类实现单例模式
 * 利用了静态内部类的特性：其理论依据是对象初始化过程中隐含的初始化锁
 */
public class ClassC {
    private static class ClassCHolder {
        private static ClassC instance = new ClassC();
    }

    private ClassC() {
    }

    public static ClassC newInstance() {
        return ClassCHolder.instance;
    }
}
