package org.liu.hutool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.NumberChineseFormatter;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.TableMap;
import cn.hutool.core.util.*;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.json.JSONUtil;
import org.liu.enums.AnimalType;
import org.liu.enums.SelectionEnum;
import org.liu.model.Action;
import org.liu.model.AjaxResult;
import org.liu.model.Animal;
import org.liu.model.Monkey;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.liu.hutool.StrExtendUtil.ordinalIndexOf;

public class TestHutool {

    public static void main(String[] args) throws MalformedURLException {
        testId();
    }

    public static void testTableMap() {
        TableMap<String, String> tableMap = new TableMap<>();
        tableMap.put("a", "1");
        tableMap.put("a", "2");
        tableMap.put("b", "3");
        tableMap.put("c", "4");
        for (Map.Entry<String, String> entry : tableMap) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static void testSetProperty() {
        Animal animal = new Animal();
        animal.setId(1);
        animal.setAge(11);
//        BeanUtil.setProperty(animal, "go", "111");
        BeanUtil.setFieldValue(animal, "name", "ddd");
        System.out.println(animal);
    }

    public static void testId() {
        String s = IdUtil.fastSimpleUUID();
        System.out.println(s);//0f7615a0a8694889ac42d460b05c2c23
        String randomString = RandomUtil.randomString(16);
        System.out.println(randomString);
    }

    public static void testClassUtil() {
        Set<Class<?>> classes = ClassUtil.scanPackageBySuper("org.liu.model", Action.class);
        System.out.println(classes);
        classes = ClassUtil.scanPackageBySuper("org.liu.enums", SelectionEnum.class);
        System.out.println(classes);
    }

    public static void testConvert() {
        String a = null;
        System.out.println(Convert.convert(String.class, a));
    }

    public static void pinyin() {
        char firstLetter = PinyinUtil.getFirstLetter('刘');
        System.out.println(String.valueOf(firstLetter).toUpperCase());
    }

    public static void beanCopy() {
        Animal animal = new Animal();
        animal.setId(1);
        animal.setAge(11);
        animal.setAnimalType(AnimalType.LAND);
        Monkey monkey = new Monkey();
        monkey.setColor("red");
        monkey.setBirthday(new Date());
        BeanUtil.copyProperties(animal, monkey, CopyOptions.create().ignoreNullValue());
        System.out.println(monkey);
    }

    public static void testNumberChineseFormatter() {
        String format = NumberChineseFormatter.format(12, false);
        System.out.println(format);
        format = NumberChineseFormatter.formatThousand(123, false);
        System.out.println(format);
    }

    public static void testImg() throws MalformedURLException {
        BufferedImage image = ImgUtil.read(new URL("http://124.71.114.75:9008/nkrlzyfiletest/commonFile/1770411696997814300/1770411708450406400.jpg"));
        ImgUtil.write(image, new File("E:\\downloads\\a.jpg"));
    }

    public static void split() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        List<List<String>> split = CollUtil.split(list, list.size() / 2);
        for (List<String> stringList : split) {
            System.out.println(stringList);
        }
    }

    public static void test() {
        System.out.println(DateUtil.endOfMonth(new Date()));
        System.out.println(DateUtil.format(new Date(), DatePattern.UTC_WITH_XXX_OFFSET_FORMAT));
        System.out.println(DateUtil.format(new Date(), DatePattern.UTC_WITH_ZONE_OFFSET_FORMAT));
        System.out.println(DateUtil.format(new Date(), DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(DateUtil.parse("20211", "yyyyMM"));
        System.out.println(Math.abs(-7.77));
        System.out.println(NumberUtil.isNumber("+123.564"));

        String text = "绝对值($d)*(绝对值($e)+$f)-绝对值({$a+绝对值($b+($c*$g))})";
        System.out.println(ordinalIndexOf(text, "", 3, 19));

        System.out.println(StrUtil.format("123{},{},{},{}", "a", "", "", true));
        System.out.println(DateUtil.parse("202212", "yyyyMM"));
        System.out.println(StrUtil.fillAfter("abc", '1', 5));

        System.out.println("------对aes key加密流程------");
        String aesKey = RandomUtil.randomString(32);
        aesKey = "h550psakwp29498p373xfg1tp2ub1h33";
        System.out.println("原始aes密钥：" + aesKey);
        String encryptByBase64 = java.util.Base64.getEncoder().encodeToString(aesKey.getBytes(StandardCharsets.UTF_8));
        System.out.println("base64编码后：" + encryptByBase64);
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==";
        String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=";
        String encryptedByRsa = SecureUtil.rsa(privateKey, publicKey).encryptBase64(encryptByBase64, KeyType.PublicKey);
        System.out.println("rsa加密后后：" + encryptedByRsa);
        System.out.println("-------对请求体进行aes加密------");
        String body = "{\"code\":200,\"msg\":\"\",\"data\":[{\"token\":\"\",\"createTime\":\"\"},{\"token\":\"\",\"createTime\":\"\"}]}";
        String encryptedByAes = SecureUtil.aes(aesKey.getBytes(StandardCharsets.UTF_8)).encryptBase64(body);
        System.out.println("加密后的body：" + encryptedByAes);//ZtOPujrHE7gxU9UvdFbS9A==
        System.out.println("--------对aes key解密流程---------");
        System.out.println("--------对请求体解密流程---------");
        byte[] bytes = java.util.Base64.getDecoder().decode(encryptedByAes);
        System.out.println("base64解码成字节码");
        byte[] decrypt = SecureUtil.aes(aesKey.getBytes(StandardCharsets.UTF_8)).decrypt(bytes);
        System.out.println("aes解码后：" + new String(decrypt));

    }

    public static void json() {
        List<Animal> list = new ArrayList<>();
        list.add(new Animal(1));
        list.add(new Animal(2));
        AjaxResult<List<Animal>> ajaxResult = new AjaxResult<>();
        ajaxResult.setCode(200);
        ajaxResult.setData(list);

        String jsonStr = JSONUtil.toJsonStr(ajaxResult);
        System.out.println(jsonStr);

        AjaxResult<List<Animal>> bean = JSONUtil.toBean(jsonStr, new TypeReference<AjaxResult<List<Animal>>>() {
        }, false);
        System.out.println(bean);
    }

    public static void base64() {
        //不能以“data:image/png;base64,”开头
//        String base64 = "";
        //对于大文件字符串，使用String类型的变量存储，超出长度，可以使用StringBuilder代替
        StringBuilder sb = new StringBuilder();
        //此处将大文本移动到独立文件中
        ClassPathResource resource = new ClassPathResource("base64.txt");
        String base64 = resource.readStr(Charset.defaultCharset());
        ImgUtil.write(ImgUtil.toImage(Base64.decode(base64)), new File("D:\\download\\test1.jpeg"));
    }

}
