package org.liu.basic;

import java.util.*;

/**
 * @Author lzs
 * @Date 2022/12/22 13:59
 **/
public class TestMap {

    public static void main(String[] args) {
        treeMap();
        StringBuilder sb = new StringBuilder();
        sb.append("fffff,");
        sb.replace(sb.length() - 1, sb.length(), "");
        System.out.println(sb.toString());
    }

    public static void treeMap(){
        Map<CollectionPrefix, String> map = new TreeMap<>();
        map.put(CollectionPrefix.departmentDataTax, "我答复答复");
        map.put(CollectionPrefix.countDataAccommodationFood, "我答复答复");
        map.put(CollectionPrefix.baseDataCompany1011, "我答复答复");
        System.out.println(map);
    }

}
