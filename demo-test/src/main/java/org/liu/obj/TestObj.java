package org.liu.obj;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * main
 *
 * @author liuzhangsheng
 * @create 2018/9/1
 */
public class TestObj {

    public static void main(String[] args) {
        Junior junior = new Junior();
        junior.setId(1);
        junior.setName("test");
        junior.setBirthday(new Date());
        junior.setGender("man");

        TestObj testObj = new TestObj();
        testObj.test(junior);
    }

    public void test(Superior superior){
        String jsonString = JSON.toJSONString(superior);
        System.out.println(jsonString);
    }

}
