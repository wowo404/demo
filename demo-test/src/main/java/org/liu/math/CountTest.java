package org.liu.math;

import java.util.ArrayList;
import java.util.List;

public class CountTest {

    public static void main(String[] args) {
        count();
    }

    public static void count(){
        String s = "346,352,411,355,\n" +
                "414,360,\n" +
                "475,387,\n" +
                "487,489,492,404,\n" +
                "424,\n" +
                "443,\n" +
                "449,\n" +
                "513,545,\n" +
                "545,\n" +
                "554,\n" +
                "560,\n" +
                "584,\n" +
                "121,\n" +
                "697,\n" +
                "303,\n" +
                "311,493,436,799,\n" +
                "300,\n" +
                "306,\n" +
                "816,\n" +
                "860,\n" +
                "69,859,\n" +
                "482,\n" +
                "470,\n" +
                "505,\n" +
                "1043,513,\n" +
                "1063,1065,523,\n" +
                "518,\n" +
                "538,\n" +
                "540,\n" +
                "540,\n" +
                "274,\n" +
                "1082,677,115,\n" +
                "682,395,\n" +
                "595,\n" +
                "606,\n" +
                "609,\n" +
                "608,\n" +
                "614,\n" +
                "614,\n" +
                "615,\n" +
                "614,\n" +
                "616,\n" +
                "620,\n" +
                "761,\n" +
                "853,\n" +
                "1186,849,\n" +
                "687,\n" +
                "686,\n" +
                "348,\n" +
                "779,\n" +
                "681,\n" +
                "303,\n" +
                "778,791,\n" +
                "205,\n" +
                "642,\n" +
                "373,707,\n" +
                "713,\n" +
                "430,\n" +
                "854,\n" +
                "720,\n" +
                "713,\n" +
                "993,874,864,\n" +
                "781,\n" +
                "569,806,\n" +
                "619,1007,\n" +
                "918,\n" +
                "488,\n" +
                "785,\n" +
                "840,\n" +
                "571,\n" +
                "721,\n" +
                "649,\n" +
                "552,\n" +
                "1016";
        s = s.replace("\n", "");
        String[] split = s.split(",");
        System.out.println("total:" + split.length);
        int n100 = 0;
        int n200 = 0;
        int n300 = 0;
        int n400 = 0;
        int n500 = 0;
        int n600 = 0;
        int n700 = 0;
        int n800 = 0;
        int n900 = 0;
        int n1000 = 0;
        int large1000 = 0;
        for (String s1 : split) {
            Integer value = Integer.valueOf(s1);
            if (value <= 100) {
                n100++;
            } else if (value <= 200){
                n200++;
            } else if (value <= 300){
                n300++;
            } else if (value <= 400){
                n400++;
            } else if (value <= 500){
                n500++;
            } else if (value <= 600){
                n600++;
            } else if (value <= 700){
                n700++;
            } else if (value <= 800){
                n800++;
            } else if (value <= 900){
                n900++;
            } else if (value <= 1000){
                n1000++;
            } else if (value > 1000){
                large1000++;
            }
        }
        System.out.println(n100);
        System.out.println(n200);
        System.out.println(n300);
        System.out.println(n400);
        System.out.println(n500);
        System.out.println(n600);
        System.out.println(n700);
        System.out.println(n800);
        System.out.println(n900);
        System.out.println(n1000);
        System.out.println(large1000);
    }

}
