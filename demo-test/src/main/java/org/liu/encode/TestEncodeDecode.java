package org.liu.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestEncodeDecode {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("1234abcd微软", "UTF-8");
        System.out.println(encode);
    }

}
