package org.liu.hutool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.NumberChineseFormatter;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.json.JSONUtil;
import org.liu.enums.AnimalType;
import org.liu.model.AjaxResult;
import org.liu.model.Animal;
import org.liu.model.Monkey;

import javax.crypto.SecretKey;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.liu.hutool.StrExtendUtil.ordinalIndexOf;

public class TestHutool {

    public static void main(String[] args) throws MalformedURLException {
        test();
    }

    public static void pinyin(){
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
        System.out.println(DateUtil.parse("20211", "yyyyMM"));
        System.out.println(Math.abs(-7.77));
        System.out.println(NumberUtil.isNumber("+123.564"));

        String text = "绝对值($d)*(绝对值($e)+$f)-绝对值({$a+绝对值($b+($c*$g))})";
        System.out.println(ordinalIndexOf(text, "", 3, 19));

        System.out.println(StrUtil.format("123{},{},{},{}", "a", "", "", true));
        System.out.println(DateUtil.parse("202212", "yyyyMM"));
        System.out.println(StrUtil.fillAfter("abc", '1', 5));

        SecretKey secretKey = SecureUtil.generateKey("aes");
        System.out.println(secretKey.getAlgorithm());
        System.out.println(java.util.Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        byte[] keyBytes = java.util.Base64.getDecoder().decode("uZSgFbNAZQ/aVLPg+vo9Rw==");
        String encryptBase64 = SecureUtil.aes(keyBytes).encryptBase64("刘123abc李奎炯嚄");
        String decryptStr = SecureUtil.aes(keyBytes).decryptStr(encryptBase64);
        System.out.println("aes加密后：" + encryptBase64);
        System.out.println("aes解密后：" + decryptStr);
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
