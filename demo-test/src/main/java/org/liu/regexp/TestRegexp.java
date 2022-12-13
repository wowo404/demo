package org.liu.regexp;

import cn.hutool.core.util.StrUtil;
import org.liu.hutool.TestHutool;

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

    public static void matchAbs() {
//        String text = "绝对值({$a+绝对值($b+($c*$g))})-绝对值($d)*(绝对值($e)+$f)";
//        String text = "绝对值($d)*(绝对值($e)+$f)-绝对值({$a+绝对值($b+($c*$g))})";
//        String text = "绝对值({$d})*(绝对值({$e})+{$f})-绝对值({$a}+绝对值({$b}+({$c}*{$g})))+绝对值({$h}+绝对值({$i}+({$j}*{$k})))";
        String text = "四舍五入($d,1)*(四舍五入($e,2)+$f)-四舍五入({$a}+四舍五入($b+($c*$g),4),3)+四舍五入({$h}+四舍五入($i+($j*$k),6),5)";
        String reg = "四舍五入\\(.*?\\)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            int leftBracketCount = StrUtil.count(group, "(");
            int rightBracketCount = StrUtil.count(group, ")");
            int firstIndex = text.indexOf(group);
            if (leftBracketCount != rightBracketCount) {
                int lastIndex = TestHutool.ordinalIndexOf(text, ")", leftBracketCount, firstIndex);
                System.out.println(firstIndex + " - " + lastIndex);
                String complexText = text.substring(firstIndex, lastIndex + 1);
                System.out.println(complexText);

            } else {
                int lastIndex = firstIndex + group.length();
                System.out.println(text.substring(firstIndex, lastIndex));
            }
        }
    }

    public static void test() {
        String functionCommandReg = "\\{#abs\\d+}|\\{#round\\d+}";
        System.out.println("{#abs0}".matches(functionCommandReg));
        System.out.println("{#round20}".matches(functionCommandReg));

        String str = "crm";
        String reg = "(?!^-)[0-9\\-]{6,16}";
        System.out.println(str.matches(reg));
        String reg1 = "pl|erp|crm|sim";
        System.out.println(str.matches(reg1));

        String reg2 = "[+\\-*/%]";
        System.out.println("反对".matches(reg2));
        System.out.println("{$add:['salesAmountOfTaxFree2','salesAmountOfCommonTaxRate1']}".matches("\\{\\$[a-z]+:.+}"));
        System.out.println("{$salesAmountOfCommonTaxRate1}".matches("\\{\\$[a-z]+:.+}"));
    }

    public static void getMatchGroup() {
        String text = "1000+绝对值({房地产本年完成投资额（万元）})/四舍五入({本年商品房销售面积（平方米）},2)";
        Pattern pattern = Pattern.compile("\\{.*?}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * 用正则表达式匹配出每个<p></p>里面的第三个。（句号）然后替换成169号
     */
    public static void matchPosition() {
        String text = "<p>1你好吗,你好吗,你好吗。你好吗你好吗,你好吗,你好吗。你好吗你好吗,你好吗,你好吗。你好吗你好吗,你好吗,你好吗。你好吗你好吗,你好吗,你好吗。</p>" +
                "<p>2我很好。我很好，我很好，我很好。你好吗你好吗,你好吗,你好吗。你好吗你好吗,你好吗,你好吗。你好吗你好吗,你好吗,你好吗。</p>";
        String reg = "(<p>(?:.*?。){2}.*?)。(.*?</p>)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            for (int i = 0; i < matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
        String replaceAll = text.replaceAll(reg, "$1(169号)$2");
        System.out.println(replaceAll);
    }

    /**
     * 必须包含大写字母，小写字母，数字，6到20位：(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{6,20}
     */
    public static void password() {
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

    public static void image() {
        String src = "data:image/JPEG;base64";
        if (src.matches("(data:image/)[a-zA-Z]+(;base64)")) {
            System.out.println(src);
        }
    }

    public static void domain() {
        System.out.println("sfd".indexOf(""));
        System.out.println("监测点123".indexOf("点1"));
        String url = "http://anotherbug.blog.chinajavaworld.org.cn/entry/4545/0/";
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(?:com\\.cn|net\\.cn|org\\.cn|com|net|org|cn|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);

        Matcher matcher = p.matcher(url);
        matcher.find();
        System.out.println(matcher.group());
    }

    public static void ip() {
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

    public static void mobile() {
        String reg = "^(13|14|15|16|17|18|19)[0-9]{9}$";
        Pattern pattern = Pattern.compile(reg);
        System.out.println(pattern.matcher("15058124996").matches());
    }

    public static void notContain() {
        String reg = "^((?!@|\\$|#|[a-z]|[0-9]).)*$";
        Pattern pattern = Pattern.compile(reg);
        System.out.println(pattern.matcher("我是对方@").matches());
        System.out.println(pattern.matcher("我是对方").matches());
        System.out.println(pattern.matcher("我$").matches());
        System.out.println(pattern.matcher("我#").matches());
        System.out.println(pattern.matcher("我1a").matches());
        System.out.println(pattern.matcher("我a").matches());

        String a = "((?!地).)*";
        String str = "址a12地3bc";
        System.out.println(str.matches(a));

        String re = "^((?!\\{|\\}|\\[|\\]|并且|或者|包含|不包含|前缀|非前缀|后缀|非后缀|为空|不为空|等于|不等于|大于|大于等于|小于|小于等于).)*$";
        System.out.println("df[er".matches(re));
        System.out.println("df]wer".matches(re));
        System.out.println("df{wer".matches(re));
        System.out.println("df}wer".matches(re));
        System.out.println("df并且wer".matches(re));
        System.out.println("df或者wer".matches(re));
        System.out.println("df包含wer".matches(re));
        System.out.println("df不包含wer".matches(re));
    }

    public static void startWith() {
        String reg = "^备.*";
        String str = "备份abc124！@#";
        System.out.println(str.matches(reg));
    }

    public static void notStartWith() {
        String reg = "^((?!ssr://)(?!vmess://)(?!http://)).*";
        String str = "aaa://反对";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());

        String a = "^(?!a).*";
        String s = "地bcd1234.";
        System.out.println(s.matches(a));

        String reg1 = "^(?![0-9])[a-zA-Z0-9]*";
        System.out.println("1231abc".matches(reg1));
    }

    public static void endWith() {
        String a = ".*地$";
        String s = "啊啊发地";
        System.out.println(s.matches(a));
    }

    public static void notEndWith() {
        String a = "^.*(?<!a)$";
        System.out.println("地bcd1234.".matches(a));
        System.out.println("地bcd1234.a".matches(a));
    }

    /**
     * 名字：英文名字，中文名字，可以包含·或者•，但不能开头和结尾
     * 示例：justing·liu，justing，艾哈迈德·买买提，李三
     */
    public static void name() {
        //TODO:还不完善，中间两个点也能匹配，如：a··b
        String reg1 = "^[a-zA-Z]+([·•]*[a-zA-Z]+)*$|^[\\u3400-\\u9FFF]+([·•]*[\\u3400-\\u9FFF]+)*$";
        String content = "艾哈迈德·买买提·买买提";
        System.out.println(content.matches(reg1));
    }

    public static void name2() {
        String reg = "^(?![-.])[0-9a-zA-Z-.]*(?<![.])$";
        System.out.println("ab123".matches(reg));
        System.out.println("-ab123".matches(reg));
        System.out.println("ab123.".matches(reg));
        System.out.println("ab123-".matches(reg));
        System.out.println("ab12.3-".matches(reg));
    }

}
