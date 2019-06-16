/*
 * Project: POI
 * 
 * File Created at 2014年3月7日 下午8:30:02
 * 
 * Copyright 2012 seaway.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Seaway Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with seaway.com.
 */
package cn.seaway.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import cn.seaway.word2007.VolumeProductionWord;


public class MyStringUtil {

    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer();
        sb.append("<w:t>第1期</w:t>");
        sb.append("<w:t>2014-01-01</w:t>");
        sb.append("<w:t>¥2001.00</w:t>");
        
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
        map.put("2014-02-02", "¥2100.00");
        map.put("2014-03-02", "¥2200.00");
        map.put("2014-04-02", "¥2300.00");

        String num = "第1期";
        String date = "2014-01-01";
        String money = "¥2001.00";

        int idxOne = sb.indexOf(num);
        int idxTwo = sb.indexOf(date);
        int idxThree = sb.indexOf(money);
        
        StringBuffer result = new StringBuffer();
        int i = 1;
        for(Entry<String,String> entry : map.entrySet()){
            sb.replace(idxOne, idxOne+num.length(), "第"+i+"期");
            sb.replace(idxTwo, idxTwo+date.length(), entry.getKey());
            sb.replace(idxThree, idxThree+money.length(), entry.getValue());
            
            result.append(sb);
            i++;
        }
        System.out.println(result.toString());
        
        String table = VolumeProductionWord.generateXmlTable(map);
        System.out.println(table);
    }

}
