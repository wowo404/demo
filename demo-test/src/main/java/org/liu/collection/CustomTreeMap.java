package org.liu.collection;

import java.util.*;

public class CustomTreeMap {

    //key：包号，value：逗号分隔的特征值数据
    private TreeMap<Integer, String> data = new TreeMap<>();
    private Integer index = 0;
    private String recordDateTime;

    public void put(String dateTime, Integer key, String value){
        //如果新的日期时间串跟旧的不一致，则清空map
        if (!dateTime.equals(recordDateTime)) {
            data.clear();
            recordDateTime = dateTime;
            index = 0;
        }
        data.put(key, value);
    }

    public List<String> checkAndRemove(){
        List<String> values = new ArrayList<>();
        if (data.size() == 0) {
            return values;
        }
        //检查索引
        Integer firstKey = data.firstKey();
        if (null == firstKey) {
            return values;
        }
        //包号最小的数据已到达
        if (firstKey.equals(index)) {
            Integer start = index;
            String next = data.get(start);
            while (next != null) {
                values.add(next);//将即将要移除的数据放入list
                data.remove(start);//将连续的数据移除
                start++;
                next = data.get(start);
            }
            index = start;//更新索引
        }
        return values;
    }

    public static void main(String[] args) {
        CustomTreeMap map = new CustomTreeMap();
        map.put("20200312115600", 0, "0");
        List<String> list1 = map.checkAndRemove();
        System.out.println(list1);
        map.put("20200312115600", 5, "5");
        List<String> list2 = map.checkAndRemove();
        System.out.println(list2);
        map.put("20200312115600", 2, "2");
        List<String> list4 = map.checkAndRemove();
        System.out.println(list4);
        map.put("20200312115600", 3, "3");
        List<String> list5 = map.checkAndRemove();
        System.out.println(list5);
        map.put("20200312115600", 1, "1");
        List<String> list3 = map.checkAndRemove();
        System.out.println(list3);
        map.put("20200312115600", 8, "8");
        List<String> list6 = map.checkAndRemove();
        System.out.println(list6);
        map.put("20200312115600", 4, "4");
        List<String> list7 = map.checkAndRemove();
        System.out.println(list7);

        map.put("20200312115601", 0, "0");
        List<String> list8 = map.checkAndRemove();
        System.out.println(list8);

    }

}
