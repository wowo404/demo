package org.liu.hutool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.liu.model.AjaxResult;
import org.liu.model.Animal;

import java.util.ArrayList;
import java.util.List;

import static org.liu.hutool.StrExtendUtil.ordinalIndexOf;

public class TestHutool {

    public static void main(String[] args) {
        json();
    }

    public static void test() {
        System.out.println(Math.abs(-7.77));
        System.out.println(NumberUtil.isNumber("+123.564"));

        String text = "绝对值($d)*(绝对值($e)+$f)-绝对值({$a+绝对值($b+($c*$g))})";
        System.out.println(ordinalIndexOf(text, "", 3, 19));

        System.out.println(StrUtil.format("123{},{}", "a", ""));
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

}
