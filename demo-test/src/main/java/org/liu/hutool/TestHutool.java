package org.liu.hutool;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.liu.model.AjaxResult;
import org.liu.model.Animal;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.liu.hutool.StrExtendUtil.ordinalIndexOf;

public class TestHutool {

    public static void main(String[] args) {
        test();
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
        System.out.println(Math.abs(-7.77));
        System.out.println(NumberUtil.isNumber("+123.564"));

        String text = "绝对值($d)*(绝对值($e)+$f)-绝对值({$a+绝对值($b+($c*$g))})";
        System.out.println(ordinalIndexOf(text, "", 3, 19));

        System.out.println(StrUtil.format("123{},{},{},{}", "a", "", "", true));
        System.out.println(DateUtil.parse("202212", "yyyyMM"));
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
