package com.liu.app.shiro_demo;

public abstract class Door {
    
    abstract void open();
    
    abstract void close();
    
    void noise(){
        System.out.println("zzzzzzzzz");
    }

}
