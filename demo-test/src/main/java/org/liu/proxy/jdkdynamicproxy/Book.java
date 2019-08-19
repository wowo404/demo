package org.liu.proxy.jdkdynamicproxy;

public class Book implements Paper {

    @Override
    public void write() {
        System.out.println("I write something!");

    }

}
