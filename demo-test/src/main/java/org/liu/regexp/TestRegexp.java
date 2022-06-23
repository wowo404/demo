package org.liu.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuzhangsheng
 * @create 2018/10/25
 */
public class TestRegexp {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        String str = "crm";
        String reg = "(?!^-)[0-9\\-]{6,16}";
        System.out.println(str.matches(reg));
        String reg1 = "pl|erp|crm|sim";
        System.out.println(str.matches(reg1));
    }

    /**
     * 必须包含大写字母，小写字母，数字，6到20位：(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{6,20}
     */
    public static void password(){
        /**
         * 分开来注释一下：
         * ^ 匹配一行的开头位置
         * (?![0-9]*$) 预测该位置后面不全是数字，如果把*号换成+号，则不能以数字开头
         * (?![a-zA-Z]+$) 预测该位置后面不全是字母
         * [0-9A-Za-z] {8,16} 由8-16位数字或这字母组成
         * $ 匹配行结尾位置
         *
         * 注：(?!xxxx) 是正则表达式的负向零宽断言一种形式，标识预该位置后不是xxxx字符
         */
        String pwd = "@123QWER$";
        String reg1 = "(?![0-9]*$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}";
        String reg2 = "(?!^\\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]+$";
        //至少包含数字跟字母，可以有字符
        String reg = "(?!^[a-zA-Z]+$)(?!^\\d+$)[a-zA-Z0-9`~!@#$%^&*()_\\-=+]{8,18}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(pwd);
        boolean matches = matcher.matches();
        System.out.println(matches);
    }

    public static void image(){
        String src = "data:image/JPEG;base64";
        if (src.matches("(data:image/)[a-zA-Z]+(;base64)")) {
            System.out.println(src);
        }
    }

    public static void domain(){
        notContain();
        System.out.println("sfd".indexOf(""));
        System.out.println("监测点123".indexOf("点1"));
        String url = "http://anotherbug.blog.chinajavaworld.org.cn/entry/4545/0/";
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(?:com\\.cn|net\\.cn|org\\.cn|com|net|org|cn|biz|info|cc|tv)",Pattern.CASE_INSENSITIVE);

        Matcher matcher = p.matcher(url);
        matcher.find();
        System.out.println(matcher.group());
    }

    public static void ip(){
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern compile = Pattern.compile(ip);
        System.out.println(compile.matcher("192.168.100.1").matches());
        System.out.println(compile.matcher("").matches());
        System.out.println(compile.matcher("192.168.1.1").matches());
        System.out.println(compile.matcher("256.2.3.4").matches());
        System.out.println(compile.matcher("1.2.3.4").matches());
        System.out.println(compile.matcher("1.2.3.4.5").matches());
        System.out.println(compile.matcher("1.2.3.4.").matches());
        System.out.println(compile.matcher("0.0.0.0").matches());
        System.out.println(compile.matcher("1.0.0.0").matches());
        System.out.println(compile.matcher("1.0.0.").matches());
        System.out.println(compile.matcher("192.168.0.0").matches());
    }

    public static void mobile(){
        String reg = "^(13|14|15|16|17|18|19)[0-9]{9}$";
        Pattern pattern = Pattern.compile(reg);
        System.out.println(pattern.matcher("15058124996").matches());
    }

    public static void notContain(){
        String reg = "^((?!@|\\$|#|a-z|0-9).)*$";
        Pattern pattern = Pattern.compile(reg);
        System.out.println(pattern.matcher("我是对方@").matches());
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
