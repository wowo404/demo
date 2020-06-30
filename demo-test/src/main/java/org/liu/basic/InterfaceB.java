package org.liu.basic;

public interface InterfaceB<T> extends InterfaceA<T>{
    @Override//覆盖父接口中的方法，可以将返回值修改为父接口方法返回值类的继承者
    InterfaceB<T> test(T t);
}
