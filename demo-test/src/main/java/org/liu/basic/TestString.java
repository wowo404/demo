package org.liu.basic;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.liu.rpn.calculatecolumn.InfixToSuffix;
import org.liu.rpn.calculatecolumn.SuffixCalculator;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author lzs
 * @Date 2022/12/8 11:21
 **/
public class TestString {

    private String formula = "绝对值(4.44+5.55-四舍五入(6.66,2)+绝对值(-7.77))/(四舍五入(8.88/绝对值(9.99+10.11)*四舍五入(11.223,2),2)+12.33)";
    private static final String mathematicsExpressionReg = "[+\\-*/%0-9().]+";

    public static void main(String[] args) {
        TestString testString = new TestString();
        testString.recursiveReplace();
        System.out.println("🤦🏼‍♂️".length());
        System.out.println("Å".equals("Å"));
        System.out.println("a b c".replaceAll(" ", ""));

        String imageDirectory = "E:\\work\\minxun\\经济普查\\定南县\\dingnan";
        String location = imageDirectory.substring(imageDirectory.lastIndexOf(File.separator) + 1);
        System.out.println(location);
        System.out.println(imageDirectory.substring(0, imageDirectory.length() - 3));

        isAlphabetic();
    }

    public static void isAlphabetic() {
        System.out.println("-----------------------");
        String text = "a哦，.;-=123来BV";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean tag = Character.isUpperCase(c) || Character.isLowerCase(c) || Character.isDigit(c);
            System.out.println("index：" + i + "，字符：" + c + "，是否字母或数字：" + tag);
        }
    }

    public void recursiveReplace() {
        while (StrUtil.containsAny(formula, "绝对值", "四舍五入")) {
            TextModel textModel = new TextModel("绝对值");
            calculateAndReplace(textModel);
            textModel = new TextModel("四舍五入");
            calculateAndReplace(textModel);
        }
    }

    private void calculateAndReplace(TextModel textModel) {
        //查找可以直接计算的函数
        getCanCalculateFunction(textModel, 0);
        if (null != textModel.getFunctionText()) {
            InfixToSuffix infixToSuffix = new InfixToSuffix(textModel.getInnerFunctionText());
            SuffixCalculator suffixCalculator = new SuffixCalculator(infixToSuffix.parse());
            BigDecimal innerFunctionResult = suffixCalculator.calculate();//函数内部表达式计算结果
            BigDecimal functionResult = getFunctionResult(textModel, innerFunctionResult);

            //替换原始字符串
            formula = formula.replace(textModel.getFunctionText(), functionResult.toPlainString());
        }
    }

    private void getCanCalculateFunction(TextModel textModel, int startIndex) {
        int firstIndex = formula.indexOf(textModel.getFunctionName(), startIndex);//函数在text中的起始索引
        if (firstIndex == -1) {
            return;
        }
        int firstRightBracketIndex = formula.indexOf(")", firstIndex);//从函数起始索引开始的第一个左括号的索引
        String functionText = formula.substring(firstIndex, firstRightBracketIndex + 1);//截取函数字符串
        int leftBracketCount = StrUtil.count(functionText, "(");
        int rightBracketCount = StrUtil.count(functionText, ")");
        if (leftBracketCount != rightBracketCount) {
            getCanCalculateFunction(textModel, startIndex + textModel.getFunctionName().length());
        } else {
            textModel.setFunctionText(functionText);
            textModel.setInnerFunctionText(getInnerFunctionText(functionText, textModel.getFunctionName()));
        }
    }

    private BigDecimal getFunctionResult(TextModel textModel, BigDecimal innerFunctionResult) {
        if (textModel.getFunctionName().equals("绝对值")) {
            return innerFunctionResult.abs();
        } else {
            int lastIndexOfRightBracket = textModel.getFunctionText().lastIndexOf(")");
            int lastIndexOfComma = textModel.getFunctionText().lastIndexOf(",");
            String placeString = textModel.getFunctionText().substring(lastIndexOfComma + 1, lastIndexOfRightBracket);
            return innerFunctionResult.setScale(Integer.parseInt(placeString), RoundingMode.HALF_UP);
        }
    }

    @Getter
    @Setter
    private static class TextModel {
        private String functionName;//函数中文名
        private String functionText;//函数完整字符串
        private String innerFunctionText;//函数内部的字符串

        TextModel(String functionName) {
            this.functionName = functionName;
        }
    }

    private String getInnerFunctionText(String functionText, String functionName) {
        String innerTextInFunction = functionText.replaceFirst(functionName + "\\(", "");
        if (functionName.equals("绝对值")) {
            innerTextInFunction = innerTextInFunction.substring(0, innerTextInFunction.length() - 1);
        } else {
            innerTextInFunction = innerTextInFunction.substring(0, innerTextInFunction.lastIndexOf(","));
        }
        return innerTextInFunction;
    }

    public static void test() {
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
