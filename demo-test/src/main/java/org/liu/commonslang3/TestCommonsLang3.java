package org.liu.commonslang3;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;

import java.util.List;

/**
 * Created by liuzhsh on 2017/11/10.
 */
public class TestCommonsLang3 {

    public static void main(String[] args) {

        String s1 = "abcdefg";
        System.out.println(s1.substring(0, s1.length() - 4) + "****");

        int a = RandomUtils.nextInt(100000, 999999);

        System.out.println(String.valueOf(a));

        System.out.println("4.0".compareTo("1.0.4"));

        String s = StringUtils.join("account", "ssss", "data", 2);
        System.out.println(s);

        String passwd = StringUtils.join("1", "0000").substring(0, 5).concat("15058124996");
        System.out.println(passwd);

        String abbreviate = StringUtils.abbreviate("1", "0", 3, 3);
        System.out.println(abbreviate);

        String substring = StringUtils.substring("my.img", "my.img".indexOf("."));
        System.out.println(substring);

        System.out.println("-----" + "/a/b/s.jpeg".split("\\.")[1]);

        split();
        convert();

        boolean ends = StringUtils.endsWithAny("my.img", ".img2", "pdf");
        System.out.println(ends);

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        System.out.println(generator.generate(5, 5));

        System.out.println(RandomStringUtils.random(5, true, true));

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

}
