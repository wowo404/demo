package org.demo.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * https://www.cnblogs.com/guanbin-529/p/11488869.html
 *
 * @author liuzhangsheng
 * @create 2018/10/24
 */
public class TestJackson {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
    }

    public static void main(String[] args) throws IOException {
        readJsonFile();
    }

    public static void readJsonFile() throws IOException {
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, ColumnConfig.class);
        List<ColumnConfig> list = mapper.readValue(new File("D:\\data\\casen_data\\sys_configs.sql"), collectionType);
        System.out.println(list);
    }

    public static void readJsonFileToJsonNode() throws IOException {
        JsonNode jsonNode = mapper.readTree(new File("D:\\data\\casen_data\\sys_configs.sql"));
        List<ColumnConfig> list = new ArrayList<>();
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            JsonNode next = elements.next();
            System.out.println(next);
            ColumnConfig columnConfig = new ColumnConfig();
            columnConfig.setName(next.get("name").asText());
            if (next.get("table").isTextual()) {
                columnConfig.setTable(next.get("table").asText());
            } else if (next.get("table").isObject()) {
                JsonNode table = next.get("table");
                Map<String,String[]> map = mapper.treeToValue(table, Map.class);
                columnConfig.setTable(map.toString());
            }
            if (next.has("nfmt")) {
                columnConfig.setNfmt(next.get("nfmt").asText());
            }
            columnConfig.setType(next.get("type").asText());
            columnConfig.setFlag(next.get("flag").asText());
//            columnConfig.setMajor(next.get("major"));
            if (next.has("exp")) {
                columnConfig.setExp(next.get("exp").toString());
            }

            list.add(columnConfig);
        }
        System.out.println(list);
    }


    public static void testBeautifulToJson() throws JsonProcessingException {
        InfoPublish publish = new InfoPublish();
        publish.setPublishId(1L);
        publish.setTitle("okokok");

        CategoryOrPublishResp resp1 = new CategoryOrPublishResp();
        resp1.setCategoryId(1L);
        resp1.setCategoryName("a");
//        resp1.setInfoPublish(publish);
        CategoryOrPublishResp resp2 = new CategoryOrPublishResp();
        resp2.setCategoryId(2L);
        resp2.setCategoryName("b");
//        resp1.setInfoPublish(publish);

        List<CategoryOrPublishResp> list = new ArrayList<>();
        list.add(resp1);
        list.add(resp2);

        System.out.println(mapper.writeValueAsString(list));
    }

    public static void testBigDecimal() throws JsonProcessingException {
        PriceModel model = new PriceModel();
        model.setPersonalPrice(new BigDecimal("1.0"));

        String s = mapper.writeValueAsString(model);
        System.out.println(s);
    }

    /**
     * originFormType字段时枚举类，如果mapper不配置ACCEPT_EMPTY_STRING_AS_NULL_OBJECT，则将空字符串转成枚举会报错
     *
     * @throws IOException
     */
    public static void jsonAndEnum() throws IOException {
        String json = "{\n" +
                "    \"pageNum\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"fuzzyString\": \"\",\n" +
                "    \"specInfo\": \"\",\n" +
                "    \"originFormIds\": [],\n" +
                "    \"originFormType\": \"\"\n" +
                "}";
        BarcodeQueryReq value = mapper.readValue(json, BarcodeQueryReq.class);
        System.out.println(value);
    }

    public static void jsonToObject() throws IOException {
        //address是个对象，在这个json中是个空字符串
        //courses是个数组，在这个json中是个单值
        //baseReq是个对象，在这个json字符串中是个空array
        String json = "{'name':'liu','age':20,'address':'','teachers':[],'courses':'chinese','baseReq':[]}";
        Student student = mapper.readValue(json, Student.class);
        System.out.println(student);

        byte[] bytes = mapper.writeValueAsBytes(student);
        System.out.println(new String(bytes));
    }

    public static void jsonNode() throws IOException {
        String json = "{'name':'liu','age':20,'address':'','teachers':[],'courses':['chinese']}";
        JsonNode jsonNode = mapper.readTree(json);
        System.out.println(jsonNode.get("address"));
        System.out.println(jsonNode.get("teachers"));
        System.out.println(jsonNode.get("courses"));
        System.out.println(jsonNode.get("abc"));
    }

    public static void test() throws IOException {
        String json = "{\"code\":\"RD000000\",\"msg\":\"\\u4ea4\\u6613\\u6210\\u529f\",\"version\":\"\",\"sign_type\":\"MD5\",\"sign\":\"cdd163dab94dbc1f9f517da44abe6b5c\",\"uuid\":\"5727ba03ffd54bf3a9a6de0fafe3f5dc\",\"timestamp\":1540354186,\"custom\":\"\",\"out_serial_no\":\"ed4684f061be446d900c657e2bc626a9\",\"order_id\":\"35201810240000000052\",\"url\":\"https:\\/\\/112.27.219.238:8443\\/paygate\\/open?orderid=35201810240000000052&srul=http%3A%2F%2F172.16.30.70%3A3001%2Fcunguan%2Fresult.html%3Fstatus%3D0%26info%3D%5Cu64cd%5Cu4f5c%5Cu6210%5Cu529f&furl=http%3A%2F%2F172.16.30.70%3A3001%2Fcunguan%2Fresult.html%3Fstatus%3D1%26info%3D%5Cu64cd%5Cu4f5c%5Cu5931%5Cu8d25\",\"service\":\"create_account_p\",\"sequence_id\":\"5d8f71ed2b7a31a30d9ff07d5564ae67\"}";
        JacksonReq jacksonReq = mapper.readValue(json, JacksonReq.class);
        System.out.println(jacksonReq);

        String json2 = "{\"message\":\"请求成功\",\"res\":{\"result\":\"击中规则\",\"Rule_final_decision\":\"复议\",\"rules\":[{\"rule_weight\":\"20\",\"rule_name\":\"朋友等关系银行不良\"},{\"rule_weight\":\"25\",\"rule_name\":\"银行不良\"}],\"Rule_final_weight\":\"45\"},\"orderNo\":\"170602170701919******\",\"code\":200}";
        DaShengBaseResponse daShengBaseResponse = mapper.readValue(json2, DaShengBaseResponse.class);
        System.out.println(daShengBaseResponse.getRes());
    }

}
