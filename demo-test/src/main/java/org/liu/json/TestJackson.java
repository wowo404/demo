package org.liu.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author liuzhangsheng
 * @create 2018/10/24
 */
public class TestJackson {

    private static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    }

    public static void main(String[] args) throws IOException {
        String json = "{\"code\":\"RD000000\",\"msg\":\"\\u4ea4\\u6613\\u6210\\u529f\",\"version\":\"\",\"sign_type\":\"MD5\",\"sign\":\"cdd163dab94dbc1f9f517da44abe6b5c\",\"uuid\":\"5727ba03ffd54bf3a9a6de0fafe3f5dc\",\"timestamp\":1540354186,\"custom\":\"\",\"out_serial_no\":\"ed4684f061be446d900c657e2bc626a9\",\"order_id\":\"35201810240000000052\",\"url\":\"https:\\/\\/112.27.219.238:8443\\/paygate\\/open?orderid=35201810240000000052&srul=http%3A%2F%2F172.16.30.70%3A3001%2Fcunguan%2Fresult.html%3Fstatus%3D0%26info%3D%5Cu64cd%5Cu4f5c%5Cu6210%5Cu529f&furl=http%3A%2F%2F172.16.30.70%3A3001%2Fcunguan%2Fresult.html%3Fstatus%3D1%26info%3D%5Cu64cd%5Cu4f5c%5Cu5931%5Cu8d25\",\"service\":\"create_account_p\",\"sequence_id\":\"5d8f71ed2b7a31a30d9ff07d5564ae67\"}";
        JacksonReq jacksonReq = mapper.readValue(json, JacksonReq.class);
        System.out.println(jacksonReq);
    }

}
