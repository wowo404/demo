package org.liu.multithread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

    public static void main(String[] args) {
        Map map =new ConcurrentHashMap();

        Object o = map.computeIfAbsent("a", key -> {

            map.put("a", "v2");

            return "v1";

        });
        System.out.println(o);
    }

}
