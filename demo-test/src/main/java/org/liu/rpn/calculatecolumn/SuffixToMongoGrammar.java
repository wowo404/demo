package org.liu.rpn.calculatecolumn;

import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式语法转mongodb语法
 *
 * @Author lzs
 * @Date 2022/12/12 16:26
 **/
public class SuffixToMongoGrammar {

    private Stack<String> stack;
    private List<String> input;
    private static final String arithmeticOperatorsReg = "[+\\-*/%]";
    private static final String mongoCommandReg = "\\{\\$[a-z]+:.+}";
    private static final String functionCommandReg = "\\{#abs\\d+}|\\{#round\\d+}";

    public SuffixToMongoGrammar(List<String> input) {
        stack = new Stack<>();
        this.input = input;
    }

    public String parse() {
        for (int i = 0; i < input.size(); i++) {
            String opThis = input.get(i);
            if (!opThis.matches(arithmeticOperatorsReg)) {
                stack.push(opThis);
            } else {
                String finalGrammar;
                String opTop1 = replaceBrace(stack.pop());
                String opTop2 = replaceBrace(stack.pop());
                switch (opThis) {
                    case "+":
                        finalGrammar = StrUtil.format(MongoProjectionGrammarEnum.add.getGrammar(), opTop2, opTop1);
                        break;
                    case "-":
                        finalGrammar = StrUtil.format(MongoProjectionGrammarEnum.subtract.getGrammar(), opTop2, opTop1);
                        break;
                    case "*":
                        finalGrammar = StrUtil.format(MongoProjectionGrammarEnum.multiply.getGrammar(), opTop2, opTop1);
                        break;
                    case "/":
                        finalGrammar = StrUtil.format(MongoProjectionGrammarEnum.divide.getGrammar(), opTop2, opTop1);
                        break;
                    case "%":
                        finalGrammar = StrUtil.format(MongoProjectionGrammarEnum.mod.getGrammar(), opTop2, opTop1);
                        break;
                    default:
                        throw new RuntimeException("除【+、-、*、/、%、绝对值、四舍五入】外，暂不支持其他操作符");
                }
                stack.push(finalGrammar);
            }
        }
        return stack.pop();
    }

    private String replaceBrace(String text) {
        if (text.matches(mongoCommandReg)) {
            return text;
        }
        if (text.matches(functionCommandReg)) {
            return text;
        }
        if (text.endsWith("}") && text.startsWith("{")) {
            text = text.replace("}", "'").replace("{", "'");
        }
        return text;
    }

}
