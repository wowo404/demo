package org.liu.google.guava;

import com.google.common.collect.HashBiMap;

public class TestBiMap {

    public static void main(String[] args) {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("a", "1");
        biMap.put("b", "2");
        biMap.put("c", "3");
//        biMap.put("d", "3");//key可以重复，但会覆盖。value重复的话会报错
        biMap.put("d", "4");
        biMap.forcePut("d", "5");
        biMap.forcePut("e", "4");//forcePut：key和value都可以重复
        String value = biMap.get("a");
        System.out.println(value);//1
        String key = biMap.inverse().get("1");
        System.out.println(key);//a

        String removed = biMap.remove("c");
        System.out.println(removed);

        String removed1 = biMap.inverse().remove("4");
        System.out.println(removed1);

        String removed2 = biMap.remove("ddd");
        System.out.println(removed2);//null
    }

}
