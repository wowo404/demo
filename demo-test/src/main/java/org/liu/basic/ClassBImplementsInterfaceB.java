package org.liu.basic;

public class ClassBImplementsInterfaceB<T> implements InterfaceB<T> {
    //可以看到这里实现了test方法，返回值的类型是InterfaceB<T>
    @Override
    public InterfaceB<T> test(T t) {
        return null;
    }
}
