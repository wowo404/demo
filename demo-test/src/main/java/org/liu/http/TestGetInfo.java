package org.liu.http;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

/**
 * @Author lzs
 * @Date 2023/6/23 17:20
 **/
public class TestGetInfo {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String url = "http://www.zhongkaohelp.com/gaokao/jiangxi/" + i + ".html";
            HttpResponse response = HttpRequest.get(url).execute();
            if (response.isOk()) {
                System.out.println(url);
            }
        }
    }

}
