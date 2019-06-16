package com.liu.app.staticproxy;

public class TestCount {

    public static void main(String[] args) {
        CountImpl impl = new CountImpl();
        CountProxy proxy = new CountProxy(impl);
        proxy.queryCount();
        proxy.updateCount();

    }

}
