package org.liu.google.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class AsyncTask implements Callable<Integer> {

    String str;

    public AsyncTask(String str) {

        this.str = str;

    }

    @Override
    public Integer call() throws Exception {

        System.out.println("call execute.." + str);

        TimeUnit.SECONDS.sleep(1);

        return 7;

    }

}
