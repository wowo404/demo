package com.liu.app.shiro_demo;

public class IronDoor extends Door implements Alarm {

    @Override
    void open() {
        // TODO Auto-generated method stub
        
    }

    @Override
    void close() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void alarm() {
        // TODO Auto-generated method stub
        super.noise();
    }

}
