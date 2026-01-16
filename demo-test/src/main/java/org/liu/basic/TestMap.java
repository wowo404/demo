package org.liu.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author lzs
 * @Date 2022/12/22 13:59
 **/
public class TestMap {

    public static void main(String[] args) {
        sourceTest();
    }

    public static void sourceTest() {
        System.out.println(Thread.currentThread().getName());
        Map<String, String> map = new HashMap<>();
//        map.put("1", "1");
//        map.put("2", "2");
//        map.put("3", "3");
        map.put("a", "aa");
        map.put("go", "ok");
        map.put("流", "方法从");
        map.put("目录", "谔谔");
        System.out.println(map);
    }

    public static void treeMap() {
        Map<CollectionPrefix, String> map = new TreeMap<>();
        map.put(CollectionPrefix.departmentDataTax, "我答复答复");
        map.put(CollectionPrefix.countDataAccommodationFood, "我答复答复");
        map.put(CollectionPrefix.baseDataCompany1011, "我答复答复");
        map.put(CollectionPrefix.baseDataCompany1011, null);
        System.out.println(map);
    }

}
