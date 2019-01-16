package org.liu.regexp;

/**
 * @author liuzhangsheng
 * @create 2018/10/25
 */
public class TestRegexp {

    public static void main(String[] args) {
        name();
    }

    /**
     * 名字：英文名字，中文名字，可以包含·或者•，但不能开头和结尾
     * 示例：justing·liu，justing，艾哈迈德·买买提，李三
     */
    public static void name(){
        //TODO:还不完善，中间两个点也能匹配，如：a··b
        String reg1 = "^[a-zA-Z]+([·•]*[a-zA-Z]+)*$|^[\\u3400-\\u9FFF]+([·•]*[\\u3400-\\u9FFF]+)*$";
        String content = "艾哈迈德·买买提·买买提";
        System.out.println(content.matches(reg1));
    }

}
