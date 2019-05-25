package org.liu.http;

import java.net.MalformedURLException;
import java.net.URL;

public class TestHttp {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://www.baidu.com");
        System.out.println(url.getProtocol());

        URL url1 = new URL("http://www.baidu.com");
        System.out.println(url1.getProtocol());
    }

}
