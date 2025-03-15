package org.liu.model;

/**
 * @author lzs
 * @Date 2024/10/30 11:17
 **/
public class Bicycle implements Action {
    @Override
    public void run() {
        System.out.println("go go go");
    }
}
