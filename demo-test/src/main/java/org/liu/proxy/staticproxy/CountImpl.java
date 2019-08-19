package org.liu.proxy.staticproxy;

public class CountImpl implements Count {

    @Override
    public void queryCount() {
        System.out.println(this.getClass().getName() + ".queryCount()");

    }

    @Override
    public void updateCount() {
        System.out.println(this.getClass().getName() + ".updateCount()");

    }

}
