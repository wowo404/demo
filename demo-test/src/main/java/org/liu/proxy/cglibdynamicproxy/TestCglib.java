package org.liu.proxy.cglibdynamicproxy;

public class TestCglib {
    
    public static void main(String[] args) {
        MyCglibProxy cglib = new MyCglibProxy();
        BookFacadeImpl impl = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
        impl.addBook();
    }

}
