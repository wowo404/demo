package org.liiu.gaode.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

/**
 * @Author lzs
 * @Date 2023/6/19 10:33
 **/
public class TestApi {

    public static void main(String[] args) {
        regeo();
    }

    public static void regeo() {
        String key = "69ecead1de4da52aa145366546fe3449";
        String location = "114.8047802734375,24.698463812934026";
        String url = "https://restapi.amap.com/v3/geocode/regeo?key={}&location={}";
        HttpResponse response = HttpRequest.get(StrUtil.format(url, key, location)).execute();
        if (response.isOk()) {
            System.out.println(response.body());
        } else {
            System.out.println(response.toString());
        }
    }

}
