package org.liu.sort;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.TreeMap;

/**
 * treemap sort
 *
 * @author liuzhangsheng
 * @create 2018/4/26
 */
public class TreeMapSort {

    public static void main(String[] args) {

        String json = "{\"cardlist\":[{\"bankcode\":\"ICBC\",\"bindid\":\"181786441\",\"cardlast\":\"2445\",\"cardname\":\"E时代卡\",\"cardtop\":\"622203\",\"merchantno\":\"10000449592\",\"phone\":\"\",\"verifystatus\":true}],\"errorcode\":\"\",\"errormsg\":\"\",\"identityid\":\"18888889999\",\"identitytype\":\"ID_CARD\",\"merchantno\":\"10000449592\",\"sign\":\"Ykk4K1Pb/NYo2//1zdBa7wpPzGzBQCbK3zKIbYkPaZ4aesSRunyklKyYe0dPg//+MC14/4TCaGP2nIRaKgVy48MU3TgoyDnU4yHvKQc6SKESyRAqcTAEuJcYsvq0jBvWD2cg62oEywJvPXksL0MB93Atsj6Qkjx3rtZqdrX9fIU=\"}";
        TreeMap<String, String> treeMap = JSON.parseObject(json, new TypeReference<TreeMap<String, String>>() {
        });
        System.out.println(treeMap);
    }

}
