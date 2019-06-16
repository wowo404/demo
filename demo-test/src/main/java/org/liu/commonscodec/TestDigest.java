package org.liu.commonscodec;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by hello on 2017/11/8.
 */
public class TestDigest {
    public static void main(String[] args) {
        String sha512Hex = DigestUtils.sha512Hex("123456");
        System.out.println(sha512Hex);

        String md5Str = "test";
        String md5Hex = DigestUtils.md5Hex(md5Str);
        System.out.println(md5Hex);
    }
}
