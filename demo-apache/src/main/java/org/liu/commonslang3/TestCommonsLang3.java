package org.liu.commonslang3;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liuzhsh on 2017/11/10.
 */
public class TestCommonsLang3 {

    public static void main(String[] args) {

        random();

    }

    public static Integer minusWithDay(Date firstDate, Date anotherDate) {
        long first = DateUtils.truncate(firstDate, Calendar.DAY_OF_MONTH).getTime();
        long another = DateUtils.truncate(anotherDate, Calendar.DAY_OF_MONTH).getTime();
        return (int) ((first - another) / (24 * 60 * 60 * 1000));
    }

    private static void random() {
        System.out.println(RandomStringUtils.randomAlphabetic(5));
    }

    private static void countMatches() {
        String ip = "192.168.100.1";
        System.out.println(StringUtils.countMatches(ip, "."));
    }

    private static void removeFirst() {
        String removeFirst = StringUtils.removeFirst("data:image/png;base64,iVBORw0K", "data:image/.*;base64,");
        System.out.println(removeFirst);
    }

    private static void endsWithAny() {
        boolean ends = StringUtils.endsWithAny("my.img", ".img2", "pdf");
        System.out.println(ends);
    }

    //替换--很高级的方法
    private static void abbreviate() {
        String abbreviate = StringUtils.abbreviate("1", "0", 3, 3);
        System.out.println(abbreviate);
    }

    private static void subarray() {
        byte[] temp = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05};
        byte[] subarray = ArrayUtils.subarray(temp, 2, temp.length);
        System.out.println(subarray);
    }

    private static void substring(List<String> idList, String id) {
        int division = 19;
        if (id.length() % 17 == 0) {
            division = 17;
        } else if (id.length() % 18 == 0) {
            division = 18;
        } else if (id.length() % 19 == 0) {
            division = 19;
        } else if (id.length() % 20 == 0) {
            division = 20;
        }
        if (id.length() > division) {
            String substring = id.substring(0, division);
            idList.add(substring);
            String surplus = id.substring(division);
            substring(idList, surplus);
        } else {
            idList.add(id);
        }
    }

    public static void convert() {
        Integer a = Integer.valueOf("001");
        System.out.println(a);
    }

    public static void split() {
        String batchNo = "145996171280781312\n" +
                "145996634323554304\n" +
                "146289505144541184\n" +
                "146290159103643648\n" +
                "150991986139205632\n" +
                "150992118465302528\n" +
                "150992506396479488\n" +
                "150992615037341696\n" +
                "150993240173187072\n" +
                "151334563967275008\n" +
                "151366519174074368\n" +
                "151375019312812032\n" +
                "151378799735476224\n" +
                "151379543662399488\n" +
                "151379870402875392\n" +
                "151380432854847488\n" +
                "151381393488875520\n" +
                "151381972692897792\n" +
                "151387863802580992\n" +
                "151388180141182976\n" +
                "151388304699428864\n" +
                "151388419187150848\n" +
                "151388629422444544\n" +
                "151389823347855360\n" +
                "151393063489638400\n" +
                "151393119563288576\n" +
                "151393433158815744";
        String[] arr = batchNo.split("\n");
        System.out.println(arr.length);
    }

    public static void join() {
        String[] params = {"appid=1", "secret=2", "js_code=3", "grant_type=4"};
        Arrays.sort(params);
        String signStr = StringUtils.join(params, "&");

        System.out.println(signStr);
    }

}
