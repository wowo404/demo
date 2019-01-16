package org.liu.commonscodec;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by hello on 2017/11/8.
 */
public class TestDigest {
    public static void main(String[] args) {
        String sha512Hex = DigestUtils.sha512Hex("123456");
        System.out.println(sha512Hex);
        
        //-128到127
        Integer a = 128;
        Integer b = 128;
        System.out.println(a == b);//false
        
        int c = 1323;
        int d = 1323;
        System.out.println(c == d);
    }
}
