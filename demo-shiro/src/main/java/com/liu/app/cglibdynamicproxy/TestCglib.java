package com.liu.app.cglibdynamicproxy;

public class TestCglib {
    
    public static void main(String[] args) {
        MyCglibProxy cglib = new MyCglibProxy();
        BookFacadeImpl impl = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
        impl.addBook();
    }

}
