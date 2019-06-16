package com.liu.app.staticproxy;

public class CountProxy implements Count {
    
    private CountImpl countImpl;
    
    public CountProxy(CountImpl countImpl){
        this.countImpl = countImpl;
    }

    @Override
    public void queryCount() {
        System.out.println("do something before queryCount");
        this.countImpl.queryCount();
        System.out.println("do something after queryCount");

    }

    @Override
    public void updateCount() {
        System.out.println("do something before updateCount");
        this.countImpl.updateCount();
        System.out.println("do something after updateCount");

    }

}
