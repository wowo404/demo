package org.liu.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Author lzs
 * @Date 2023/3/3 10:27
 **/
public class TestHttpClient {
    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        // token可以从 数据中心 -> 我的接口 中获取
        String token = "328f1ed7-cf42-42e7-abcc-e21a9e1758d7";
        String url = "http://open.api.tianyancha.com/services/open/ic/baseinfo/normal?keyword=南康区中医院";
        System.out.println(executeGet(url, token));
    }

    /**
     * http get请求
     *
     * @param url   接口url
     * @param token token
     * @return 返回接口数据
     */
    protected static String executeGet(String url, String token) {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 1000);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        String result = null;
        try {

            HttpGet get = new HttpGet(url);
            // 设置header
            get.setHeader("Authorization", token);
            // 设置类型
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }

}
