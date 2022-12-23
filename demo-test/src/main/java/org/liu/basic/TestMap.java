package org.liu.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author lzs
 * @Date 2022/12/22 13:59
 **/
public class TestMap {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        Set<String> list = new HashSet<>();
        for (String key : map.keySet()) {
            System.out.println(key);
            list.add(key);
        }
        list.forEach(map::remove);
        System.out.println(map);
    }

}
