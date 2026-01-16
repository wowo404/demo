package org.liu.test.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Main().readAll();
    }

    public void readAll() {
        List<LinkedHashMap<Integer, String>> list = EasyExcel.read("D:\\work\\projects\\chenwenbi\\共享光伏电源\\菲律宾行政划分.xlsx").sheet(0).doReadSync();
        String lastRegion = null;
        String lastProvince = null;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) continue;
            LinkedHashMap<Integer, String> map = list.get(i);
            String region = map.get(1);
            if (StringUtils.isBlank(region)) {
                region = lastRegion;
            } else {
                lastRegion = region;
            }
            String province = map.get(2);
            if (StringUtils.isBlank(province)) {
                province = lastProvince;
            } else {
                lastProvince = province;
            }
            String cityStr = map.get(3);
            if (StringUtils.isBlank(cityStr)) {
                continue;
            }
            List<String> cities = split(cityStr);
            for (String city : cities) {
                System.out.println(region + " - " + province + " - " + city);
            }
        }
    }

    private List<String> split(String text) {
        List<String> result = new ArrayList<>();
        String[] split = text.split("\\n");
        for (String s : split) {
            String[] split1 = s.split("\\r");
            result.addAll(Arrays.asList(split1));
        }
        return result;
    }

}
