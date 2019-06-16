package com.liu.app.jdkdynamicproxy;

public class TestProxy {

    public static void main(String[] args) {
        MyInvocationHandler handler = new MyInvocationHandler();
        Paper paper = (Paper) handler.bind(new Book());
        paper.write();

    }

}
