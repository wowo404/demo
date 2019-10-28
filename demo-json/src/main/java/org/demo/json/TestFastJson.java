package org.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.IOUtils;
import org.liu.obj.Junior;
import org.liu.obj.Superior;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestFastJson {

    public static void main(String[] args) throws IOException {

//		generate1();
//		generate2();
//		generate3();
//		generate4();
//		generate5();
//        generate6();
//        generate7();
//        CommonRequestData requestData = new CommonRequestData();
//        requestData.setRequestNo("a");
//        requestData.setDigest("b");
//        System.out.println(JSON.toJSONString(requestData));

        parseWarningArgs();
    }

    private static void parseWarningArgs(){
        NoDataUploadWarningArgs args = new NoDataUploadWarningArgs();
        args.setRepeatInterval(12);
        System.out.println(JSON.toJSONString(args));
    }

    private static void parseTaskArgs(){
        TrendDataProcessTaskArgs args = new TrendDataProcessTaskArgs();
        args.setExecuteInterval(5);
        args.setExecuteIntervalUnit(1);
        args.setCollectionName("trend_five_minutes");
        args.setIsNeedSplitCollectionById(true);
        System.out.println(JSON.toJSONString(args));
    }

    private static void parseWaveform() {
        TLVRealtimeDomainWaveform waveform = new TLVRealtimeDomainWaveform();
        waveform.setDateTime("20190912121212");
        Integer[] temp = {1, 12, 13, 454};
        waveform.setSourceValueArray(temp);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(21);
        waveform.setList(list);

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        waveform.setTunnelCodeList(set);

        String string = JSON.toJSONString(waveform);
        JSONObject jsonObject = JSON.parseObject(string);
        String sourceValueArray = jsonObject.getString("sourceValueArray");
        System.out.println(sourceValueArray);
        String list1 = jsonObject.getString("list");
        System.out.println(list1);

        JSONArray tunnelCodeList = jsonObject.getJSONArray("tunnelCodeList");
        System.out.println(tunnelCodeList);
        Integer[] a = new Integer[tunnelCodeList.size()];
        Integer[] integers = tunnelCodeList.toArray(a);
        System.out.println(integers);
    }

    private static void parseArea() throws IOException {
        InputStream is = TestFastJson.class.getClassLoader().getResourceAsStream("src/main/webapp/json/area.json");
        String resource = IOUtils.toString(is, "utf-8");
        JSONObject jsonObject = JSON.parseObject(resource);
        JSONObject province = jsonObject.getJSONObject("150000");
        String provinceName = province.getString("name");
        JSONObject child = province.getJSONObject("child");
        JSONObject city = child.getJSONObject("150800");
        String cityName = city.getString("name");
        JSONObject county = city.getJSONObject("child");
        String countyName = county.getString("150802");
        System.out.println();
    }

    private static void testIncludeNull() {
        String json = "{\"mobile\": \"13344445555\", \"borrowerNo\": \"328580638796025856\", \"liveDetail\": {\"address\": \"fdsfsfdsf\", \"liveArea\": \"11,1101,110101\", \"liveStatus\": 0, \"livingCondition\": 0}, \"loanDetail\": {\"term\": 5, \"bankNo\": \"3333 3333 3333 3333\", \"loanAmt\": 3000, \"loanRate\": 10.8, \"loanUsed\": \"122\", \"termType\": 2, \"productNo\": \"qwe123\", \"repayTerm\": 5, \"openingBank\": \"建行\", \"repayMethod\": 2, \"repayAccount\": \"户名\"}, \"workDetail\": {\"taxNo\": \"fssfds\", \"shopName\": \"fsdfs\", \"workType\": \"0\", \"afterTaxAmt\": \"12233\", \"incomeSource\": 0, \"licenseNumber\": \"ffsdsf\"}, \"personDetail\": {\"sex\": 1, \"province\": \"13\", \"realName\": \"xingming\", \"birthDate\": \"2000-05-22T13:12:08.282Z\", \"famousRace\": \"hanzu\", \"identityNo\": \"110101199003072316\", \"isMarriage\": 1, \"isHousehold\": 1, \"highestDegree\": 2, \"identityCard1\": \"http://jkzj-open.oss-cn-hangzhou.aliyuncs.com/26290811843766.png?Expires=1560358147&OSSAccessKeyId=LTAIRrjNj5SHTcmD&Signature=E6eSeU76dts3Gc7Awq0PjK5R2zY%3D\", \"identityCard2\": \"http://jkzj-open.oss-cn-hangzhou.aliyuncs.com/26294953730944.png?Expires=1560358151&OSSAccessKeyId=LTAIRrjNj5SHTcmD&Signature=LUEiTqM5XTcMOL8YOyDO9JZAf8A%3D\", \"educationLevel\": 1, \"householdBook1\": \"http://jkzj-open.oss-cn-hangzhou.aliyuncs.com/399107263276.png?Expires=1560361710&OSSAccessKeyId=LTAIRrjNj5SHTcmD&Signature=ODj2oafZyjf%2FcojCmoUpmtCFuAI%3D\", \"householdBook2\": \"http://jkzj-open.oss-cn-hangzhou.aliyuncs.com/403069035508.png?Expires=1560361714&OSSAccessKeyId=LTAIRrjNj5SHTcmD&Signature=79%2FbQvdf6RgSRTBLMJSzaKvUMxU%3D\", \"personalHealth\": 0, \"permanentAddress\": \"13,1301,130109\"}, \"spouseDetail\": {\"sex\": 1, \"name\": \"xingming\", \"identity\": \"110101199003072316\", \"birthDate\": \"2014-05-22T14:12:44.492Z\", \"famousRace\": \"minzu\", \"isHousehold\": 1, \"weddingPhoto\": \"http://jkzj-open.oss-cn-hangzhou.aliyuncs.com/463025754577.png?Expires=1560361774&OSSAccessKeyId=LTAIRrjNj5SHTcmD&Signature=zfJKZHBg9KJ6AdLZRNSEGoXiu6o%3D\", \"identityCard1\": \"http://jkzj-open.oss-cn-hangzhou.aliyuncs.com/453124021346.png?Expires=1560361764&OSSAccessKeyId=LTAIRrjNj5SHTcmD&Signature=mz32DDW%2FQoJ8xfP2S0jTZO1aU3E%3D\", \"identityCard2\": \"http://jkzj-open.oss-cn-hangzhou.aliyuncs.com/457024824499.png?Expires=1560361768&OSSAccessKeyId=LTAIRrjNj5SHTcmD&Signature=8UaN4vPo2WRu%2B20s6qP49a1%2B%2FPY%3D\", \"personalHealth\": 0}}";
        OrderApplyReq req = JSON.parseObject(json, OrderApplyReq.class);
        req.setMobile(null);//WriteNullStringAsEmpty配置会将字段类型为String值为null的字段转json为空字符串
        String s = JSON.toJSONString(req, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
        System.out.println(s);
    }

    private static void testFilterFromJson() {
        String commissionRatioInfo = "[{\"level\":1,\"ratio\":0.1},{\"level\":2,\"ratio\":0.01},{\"level\":3,\"ratio\":0.01},{\"level\":4,\"ratio\":0.01},{\"level\":5,\"ratio\":0.01}]";
        List<PartnerCommissionRatio> partnerCommissionRatioList = JSON.parseArray(commissionRatioInfo, PartnerCommissionRatio.class);
        for (int i = 1; i < 6; i++) {
            final int level = i;
            Optional<PartnerCommissionRatio> optional = partnerCommissionRatioList.stream().filter(ratio -> ratio.getLevel().equals(level)).findFirst();
            if (!optional.isPresent()) {
                System.out.println("not present");
                continue;
            }
            PartnerCommissionRatio ratio = optional.get();
            ratio.setPartnerId(i);
            System.out.println(ratio);
        }
    }

    private static void testJsonFromSon() {
        Junior junior = new Junior();
        junior.setId(1);
        junior.setName("test");
        junior.setBirthday(new Date());
        junior.setGender("man");

        test(junior);
    }

    private static void test(Superior superior) {
        String jsonString = JSON.toJSONString(superior);
        System.out.println(jsonString);
    }

    private static void testPriceModel() {
        List<PriceModel> list = new ArrayList<>();
        PriceModel model = new PriceModel();
        model.setId(1);
        model.setPersonalPrice(new BigDecimal("322.33"));
        model.setSubsidyPrice(new BigDecimal("444.33"));
        model.setTotalPrice(new BigDecimal("322.33").add(new BigDecimal("444.33")));
        model.setApplyCondition("残疾人");

        PriceModel model2 = new PriceModel();
        model2.setId(2);
        model2.setPersonalPrice(new BigDecimal("3222.33"));
        model2.setSubsidyPrice(new BigDecimal("4424.33"));
        model2.setTotalPrice(new BigDecimal("3222.33").add(new BigDecimal("4424.33")));
        model2.setApplyCondition("正常人");

        list.add(model);
        list.add(model2);

        System.out.println(JSON.toJSONString(list));
    }

    private static void dashengDataTest() {
        String json2 = "{\"message\":\"请求成功\",\"res\":{\"result\":\"击中规则\",\"Rule_final_decision\":\"复议\",\"rules\":[{\"rule_weight\":\"20\",\"rule_name\":\"朋友等关系银行不良\"},{\"rule_weight\":\"25\",\"rule_name\":\"银行不良\"}],\"Rule_final_weight\":\"45\"},\"orderNo\":\"170602170701919******\",\"code\":200}";
        DaShengBaseResponse daShengBaseResponse = JSON.parseObject(json2, DaShengBaseResponse.class);
        System.out.println(daShengBaseResponse);
    }

    private static void transferJsonToMap() throws IOException {
        FileInputStream fis = new FileInputStream(new File("/Users/liuzhangsheng/Documents/text.json"));
        Map<String, JSONObject> map = JSONObject.parseObject(fis, Map.class);
        Set<Map.Entry<String, JSONObject>> entries = map.entrySet();
        List<Province> provinceList = new ArrayList<>();
        for (Map.Entry<String, JSONObject> entry : entries) {
            JSONObject value = entry.getValue();
            ProvinceAndCityAndAreaMap provinceAndCityAndAreaMap = JSON.toJavaObject(value, ProvinceAndCityAndAreaMap.class);

            Province province = new Province();
            province.setName(provinceAndCityAndAreaMap.getName());

            Map<String, CityInner> child = provinceAndCityAndAreaMap.getChild();

            List<Province.City> cityList = new ArrayList<>();
            for (Map.Entry<String, CityInner> stringCityInnerEntry : child.entrySet()) {
                Province.City city = province.new City();
                city.setName(stringCityInnerEntry.getValue().getName());
                cityList.add(city);
            }

            province.setCityList(cityList);
            provinceList.add(province);
        }

        System.out.println(JSON.toJSONString(provinceList));
    }

    private static List<RedPackageRule> redPackageRuleList() {
        RedPackageRule red1 = new RedPackageRule();
        red1.setGainConditionOfInviteNumber(new Integer[]{0, 9});
        red1.setCashRatio(200);
        red1.setCashRatioValidTerm(3650);
        RedPackageRule red2 = new RedPackageRule();
        red2.setGainConditionOfInviteNumber(new Integer[]{10, 29});
        red2.setCashRatio(150);
        red2.setCashRatioValidTerm(180);
        RedPackageRule red3 = new RedPackageRule();
        red3.setGainConditionOfInviteNumber(new Integer[]{30, 69});
        red3.setCashRatio(100);
        red3.setCashRatioValidTerm(180);
        RedPackageRule red4 = new RedPackageRule();
        red4.setGainConditionOfInviteNumber(new Integer[]{70, 99});
        red4.setCashRatio(50);
        red4.setCashRatioValidTerm(180);
        RedPackageRule red5 = new RedPackageRule();
        red5.setGainConditionOfInviteNumber(new Integer[]{100, 100000});
        red5.setCashRatio(10);
        red5.setCashRatioValidTerm(180);
        List<RedPackageRule> redRuleList = new ArrayList<>();
        redRuleList.add(red1);
        redRuleList.add(red2);
        redRuleList.add(red3);
        redRuleList.add(red4);
        redRuleList.add(red5);
        return redRuleList;
    }

    private static void generate1() {


        RegisterRewardRule rule1 = new RegisterRewardRule();
        rule1.setRedPackageRuleList(redPackageRuleList());
        rule1.setTotalAmount(new BigDecimal("368"));
        rule1.setAmount(new BigDecimal("18"));
        rule1.setValidTerm(15);
        rule1.setOrderNumber(1);

        RegisterRewardRule rule2 = new RegisterRewardRule();
        rule2.setRedPackageRuleList(redPackageRuleList());
        rule2.setTotalAmount(new BigDecimal("368"));
        rule2.setAmount(new BigDecimal("60"));
        rule2.setValidTerm(30);
        rule2.setOrderNumber(2);

        RegisterRewardRule rule3 = new RegisterRewardRule();
        rule3.setRedPackageRuleList(redPackageRuleList());
        rule3.setTotalAmount(new BigDecimal("368"));
        rule3.setAmount(new BigDecimal("110"));
        rule3.setValidTerm(45);
        rule3.setOrderNumber(3);

        RegisterRewardRule rule4 = new RegisterRewardRule();
        rule4.setRedPackageRuleList(redPackageRuleList());
        rule4.setTotalAmount(new BigDecimal("368"));
        rule4.setAmount(new BigDecimal("180"));
        rule4.setValidTerm(60);
        rule4.setOrderNumber(4);

        List<RegisterRewardRule> list = new ArrayList<>();
        list.add(rule1);
        list.add(rule2);
        list.add(rule3);
        list.add(rule4);

        System.out.println(JSON.toJSONString(list));
    }

    private static void generate2() {
        RealNameRewardRule rule = new RealNameRewardRule();
        rule.setAmount(new BigDecimal("50"));
        rule.setValidTerm(180);
        rule.setRegisterInterval(90);
        rule.setRedPackageRuleList(redPackageRuleList());

        System.out.println(JSON.toJSONString(rule));
    }

    private static void generate3() {
        InvestRewardRule investRewardRule = new InvestRewardRule();
        investRewardRule.setType(1);
        investRewardRule.setValidTerm(365);
        investRewardRule.setRatio(new BigDecimal("0.1"));

        InvestRewardRule investRewardRule2 = new InvestRewardRule();
        investRewardRule2.setType(2);
        investRewardRule2.setValidTerm(365);
        investRewardRule2.setRatio(new BigDecimal("0.02"));

        List<InvestRewardRule> list1 = new ArrayList<>();
        list1.add(investRewardRule);
        list1.add(investRewardRule2);

        System.out.println(JSON.toJSONString(list1));

    }

    public static void generate4() {
        //20--15,30--30,100--60
        //-------邀友5---30
        VoucherRule rule1 = new VoucherRule();
        rule1.setOrderNumber(1);
        rule1.setValidTerm(15);
        rule1.setCouponAmount(new BigDecimal("30"));
        rule1.setGainConditionOfInviteNumber(5);
        rule1.setGainConditionOfNewRegister(1);
        rule1.setUseConditionOfInvestAmount(new BigDecimal("5000"));
        rule1.setUseConditionOfProductTerm(3);
        //------邀友10---20,30
        VoucherRule rule21 = new VoucherRule();
        rule21.setOrderNumber(2);
        rule21.setValidTerm(15);
        rule21.setCouponAmount(new BigDecimal("20"));
        rule21.setGainConditionOfInviteNumber(10);
        rule21.setGainConditionOfNewRegister(1);
        rule21.setUseConditionOfInvestAmount(new BigDecimal("5000"));
        rule21.setUseConditionOfProductTerm(3);
        VoucherRule rule22 = new VoucherRule();
        rule22.setOrderNumber(3);
        rule22.setValidTerm(30);
        rule22.setCouponAmount(new BigDecimal("30"));
        rule22.setGainConditionOfInviteNumber(10);
        rule22.setGainConditionOfNewRegister(1);
        rule22.setUseConditionOfInvestAmount(new BigDecimal("10000"));
        rule22.setUseConditionOfProductTerm(6);

        //------邀友30---100,20,30
        VoucherRule rule31 = new VoucherRule();
        rule31.setOrderNumber(4);
        rule31.setValidTerm(15);
        rule31.setCouponAmount(new BigDecimal("20"));
        rule31.setGainConditionOfInviteNumber(30);
        rule31.setGainConditionOfNewRegister(0);
        rule31.setUseConditionOfInvestAmount(new BigDecimal("5000"));
        rule31.setUseConditionOfProductTerm(3);
        VoucherRule rule32 = new VoucherRule();
        rule32.setOrderNumber(5);
        rule32.setValidTerm(30);
        rule32.setCouponAmount(new BigDecimal("30"));
        rule32.setGainConditionOfInviteNumber(30);
        rule32.setGainConditionOfNewRegister(0);
        rule32.setUseConditionOfInvestAmount(new BigDecimal("10000"));
        rule32.setUseConditionOfProductTerm(6);
        VoucherRule rule33 = new VoucherRule();
        rule33.setOrderNumber(6);
        rule33.setValidTerm(60);
        rule33.setCouponAmount(new BigDecimal("100"));
        rule33.setGainConditionOfInviteNumber(30);
        rule33.setGainConditionOfNewRegister(0);
        rule33.setUseConditionOfInvestAmount(new BigDecimal("20000"));
        rule33.setUseConditionOfProductTerm(12);

        //------邀友60---100,100,20,30
        VoucherRule rule41 = new VoucherRule();
        rule41.setOrderNumber(7);
        rule41.setValidTerm(15);
        rule41.setCouponAmount(new BigDecimal("20"));
        rule41.setGainConditionOfInviteNumber(60);
        rule41.setGainConditionOfNewRegister(1);
        rule41.setUseConditionOfInvestAmount(new BigDecimal("5000"));
        rule41.setUseConditionOfProductTerm(3);
        VoucherRule rule42 = new VoucherRule();
        rule42.setOrderNumber(8);
        rule42.setValidTerm(30);
        rule42.setCouponAmount(new BigDecimal("30"));
        rule42.setGainConditionOfInviteNumber(60);
        rule42.setGainConditionOfNewRegister(1);
        rule42.setUseConditionOfInvestAmount(new BigDecimal("10000"));
        rule42.setUseConditionOfProductTerm(6);
        VoucherRule rule43 = new VoucherRule();
        rule43.setOrderNumber(9);
        rule43.setValidTerm(60);
        rule43.setCouponAmount(new BigDecimal("100"));
        rule43.setGainConditionOfInviteNumber(60);
        rule43.setGainConditionOfNewRegister(1);
        rule43.setUseConditionOfInvestAmount(new BigDecimal("20000"));
        rule43.setUseConditionOfProductTerm(12);
        VoucherRule rule44 = new VoucherRule();
        rule44.setOrderNumber(10);
        rule44.setValidTerm(60);
        rule44.setCouponAmount(new BigDecimal("100"));
        rule44.setGainConditionOfInviteNumber(60);
        rule44.setGainConditionOfNewRegister(1);
        rule44.setUseConditionOfInvestAmount(new BigDecimal("20000"));
        rule44.setUseConditionOfProductTerm(12);

        //------邀友100---100,100,100,100,20,30
        VoucherRule rule51 = new VoucherRule();
        rule51.setOrderNumber(11);
        rule51.setValidTerm(15);
        rule51.setCouponAmount(new BigDecimal("20"));
        rule51.setGainConditionOfInviteNumber(100);
        rule51.setGainConditionOfNewRegister(1);
        rule51.setUseConditionOfInvestAmount(new BigDecimal("5000"));
        rule51.setUseConditionOfProductTerm(3);
        VoucherRule rule52 = new VoucherRule();
        rule52.setOrderNumber(12);
        rule52.setValidTerm(30);
        rule52.setCouponAmount(new BigDecimal("30"));
        rule52.setGainConditionOfInviteNumber(100);
        rule52.setGainConditionOfNewRegister(1);
        rule52.setUseConditionOfInvestAmount(new BigDecimal("10000"));
        rule52.setUseConditionOfProductTerm(6);
        VoucherRule rule53 = new VoucherRule();
        rule53.setOrderNumber(13);
        rule53.setValidTerm(60);
        rule53.setCouponAmount(new BigDecimal("100"));
        rule53.setGainConditionOfInviteNumber(100);
        rule53.setGainConditionOfNewRegister(1);
        rule53.setUseConditionOfInvestAmount(new BigDecimal("20000"));
        rule53.setUseConditionOfProductTerm(12);
        VoucherRule rule54 = new VoucherRule();
        rule54.setOrderNumber(14);
        rule54.setValidTerm(60);
        rule54.setCouponAmount(new BigDecimal("100"));
        rule54.setGainConditionOfInviteNumber(100);
        rule54.setGainConditionOfNewRegister(1);
        rule54.setUseConditionOfInvestAmount(new BigDecimal("20000"));
        rule54.setUseConditionOfProductTerm(12);
        VoucherRule rule55 = new VoucherRule();
        rule55.setOrderNumber(15);
        rule55.setValidTerm(60);
        rule55.setCouponAmount(new BigDecimal("100"));
        rule55.setGainConditionOfInviteNumber(100);
        rule55.setGainConditionOfNewRegister(1);
        rule55.setUseConditionOfInvestAmount(new BigDecimal("20000"));
        rule55.setUseConditionOfProductTerm(12);
        VoucherRule rule56 = new VoucherRule();
        rule56.setOrderNumber(16);
        rule56.setValidTerm(60);
        rule56.setCouponAmount(new BigDecimal("100"));
        rule56.setGainConditionOfInviteNumber(100);
        rule56.setGainConditionOfNewRegister(1);
        rule56.setUseConditionOfInvestAmount(new BigDecimal("20000"));
        rule56.setUseConditionOfProductTerm(12);

        List<VoucherRule> list = new ArrayList<>();
        list.add(rule1);
        list.add(rule21);
        list.add(rule22);
        list.add(rule31);
        list.add(rule32);
        list.add(rule33);
        list.add(rule41);
        list.add(rule42);
        list.add(rule43);
        list.add(rule44);
        list.add(rule51);
        list.add(rule52);
        list.add(rule53);
        list.add(rule54);
        list.add(rule55);
        list.add(rule56);

        System.out.println(JSON.toJSONString(list));
    }

    public static void generate5() {
        List<RankingCashAwardRule> list = new ArrayList<>();

        RankingCashAwardRule rule1 = new RankingCashAwardRule();
        rule1.setRanking(1);
        rule1.setAwardAmount(new BigDecimal("30"));

        RankingCashAwardRule rule2 = new RankingCashAwardRule();
        rule2.setRanking(2);
        rule2.setAwardAmount(new BigDecimal("20"));

        RankingCashAwardRule rule3 = new RankingCashAwardRule();
        rule3.setRanking(3);
        rule3.setAwardAmount(new BigDecimal("10"));

        list.add(rule1);
        list.add(rule2);
        list.add(rule3);

        list.sort((ruleA, ruleB) -> ruleA.getRanking().compareTo(ruleB.getRanking()));

        System.out.println(JSON.toJSONString(list));
    }

    public static void generate6() {
        List<TimeLimitCollectedAwardRule> list = new ArrayList<>();

        TimeLimitCollectedAwardRule rule1 = new TimeLimitCollectedAwardRule();
        rule1.setTimeLimit(30);
        rule1.setCarveUpNumber(5);
        rule1.setTotalAwardAmount(new BigDecimal("100"));

        TimeLimitCollectedAwardRule rule2 = new TimeLimitCollectedAwardRule();
        rule2.setTimeLimit(60);
        rule2.setCarveUpNumber(5);
        rule2.setTotalAwardAmount(new BigDecimal("50"));

        TimeLimitCollectedAwardRule rule3 = new TimeLimitCollectedAwardRule();
        rule3.setTimeLimit(180);
        rule3.setCarveUpNumber(5);
        rule3.setTotalAwardAmount(new BigDecimal("25"));

        list.add(rule1);
        list.add(rule2);
        list.add(rule3);

        System.out.println(JSON.toJSONString(list));
    }

    public static void generate7() {
        AccountRegisterRequest registerRequest = new AccountRegisterRequest();
        registerRequest.setRequestNo(UUID.randomUUID().toString().replace("-", ""));
        registerRequest.setDigest("test");
        registerRequest.setRedirectUrl("https://m.uat.niumowang-inc.com/web/#/user/summary");
        registerRequest.setNotifyUrl("http://127.0.0.1:8080/openapi/receive");

        OpenApiRequest request = new OpenApiRequest();
        request.setAppId("MFJK02117053347APP1");
        request.setReqData(JSON.toJSONString(registerRequest));
        request.setServiceName("com.tongniujucai.openapi.asset.account.register");
        request.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        request.setSign("aaaa");
        System.out.println(JSON.toJSONString(request));
    }

}
