package org.liu.google;

import com.google.api.client.util.ArrayMap;

/**
 * @Author lzs
 * @Date 2022/4/19 11:44
 **/
public class TestArrayMap {

    public static void main(String[] args) {
        ArrayMap<Integer, String> arrayMap = ArrayMap.create();
        arrayMap.put(1, "10");
        arrayMap.put(1, "11");
        arrayMap.put(2, "20");
        arrayMap.add(2, "21");
        arrayMap.put(3, "30");
        arrayMap.put(100, "100");
        arrayMap.put(99, "99");
        arrayMap.put(18, "18");
        for (int i = 0; i < arrayMap.size(); i++) {
            System.out.println(arrayMap.getKey(i) + " -- " + arrayMap.getValue(i));
        }
    }

}
