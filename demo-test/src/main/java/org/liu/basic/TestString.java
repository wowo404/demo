package org.liu.basic;

import lombok.Data;

/**
 * @Author lzs
 * @Date 2022/12/8 11:21
 **/
public class TestString {

    public static void main(String[] args) {
        String replace = "1000+绝对值({房地产本年完成投资额（万元）})/四舍五入({本年商品房销售面积（平方米）},2)";
        int absIndex = replace.indexOf("绝对值");
        int absEndIndex = replace.indexOf(")", absIndex);
        System.out.println(absIndex);
        System.out.println(absEndIndex);
        System.out.println(replace.substring(absIndex, absEndIndex + 1));

        int roundIndex = replace.indexOf("四舍五入");
        int roundEndIndex = replace.indexOf(")", roundIndex);
        System.out.println(roundIndex);
        System.out.println(roundEndIndex);
        System.out.println(replace.substring(roundIndex, roundEndIndex + 1));
    }

    public static void test(){
        SearchConditionRelationalUnit unit = new SearchConditionRelationalUnit();
        String text = "{房地产本年完成投资额（万元）} 大于 1000 并且 {行业代码} 等于 6001 或者 {成立时间-年} 大于等于 2000 或者 {移动电话} 后缀 4996 并且 {运营状态} 等于 1 并且 {执行会计制度类别} 不等于 2";
        recursiveSubstring(text, unit, 0);
        System.out.println(unit);
    }

    private static void recursiveSubstring(String text, SearchConditionRelationalUnit unit, int index) {
        int andIndex = text.indexOf("并且");
        int orIndex = text.indexOf("或者");
        if (andIndex == -1 && orIndex == -1) {
            SearchConditionRelationalUnit last = new SearchConditionRelationalUnit();
            last.setFullText(text);
            unit.setNext(last);
            return;
        }
        String logicOperatorOriginal, relationalUnit, surplusText;
        if (andIndex < orIndex || orIndex == -1) {
            relationalUnit = text.substring(0, andIndex);
            surplusText = text.substring(andIndex + 2);
            logicOperatorOriginal = "并且";
        } else {
            relationalUnit = text.substring(0, orIndex);
            surplusText = text.substring(orIndex + 2);
            logicOperatorOriginal = "或者";
        }
        if (index == 0) {
            unit.setFullText(relationalUnit);
            unit.setLogicOperatorOriginal(logicOperatorOriginal);

            recursiveSubstring(surplusText, unit, ++index);
        } else {
            SearchConditionRelationalUnit next = new SearchConditionRelationalUnit();
            next.setFullText(relationalUnit);
            next.setLogicOperatorOriginal(logicOperatorOriginal);
            unit.setNext(next);

            recursiveSubstring(surplusText, next, ++index);
        }
    }

    @Data
    private static class SearchConditionRelationalUnit {
        private String fullText;//完整的原始字符串：{房地产本年完成投资额（万元）} 大于 1000
        private String leftKeyOriginal;//关系操作符的左侧原始字符串
        private String leftKey;//解析成数据库字段名后的左侧值
        private String relationalOperatorOriginal;//关系操作符的原始字符串
        private String mongoRelationalOperator;//解析成mongodb语法的操作符
        private String rightValueOriginal;//关系操作符的右侧原始字符串
        private String rightValue;//解析成数据库字段名后的右侧值

        private String logicOperatorOriginal;//逻辑操作符的原始字符串
        private String mongoLogicOperator;//解析成mongodb语法的操作符

        private SearchConditionRelationalUnit next;
    }
}
