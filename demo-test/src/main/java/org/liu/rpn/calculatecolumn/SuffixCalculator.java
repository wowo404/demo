package org.liu.rpn.calculatecolumn;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Stack;

/**
 * @Author lzs
 * @Date 2022/12/14 9:13
 **/
public class SuffixCalculator {

    private Stack<BigDecimal> stack;
    private List<String> input;
    private static final String arithmeticOperatorsReg = "[+\\-*/%]";

    public SuffixCalculator(List<String> input) {
        this.input = input;
        stack = new Stack<>();
    }

    public BigDecimal calculate() {
        for (int i = 0; i < input.size(); i++) {
            String opThis = input.get(i);
            if (!opThis.matches(arithmeticOperatorsReg)) {
                if (!NumberUtil.isNumber(opThis)) {
                    throw new RuntimeException("计算列中的计算项不能有非数字");
                }
                stack.push(new BigDecimal(opThis));
            } else {
                BigDecimal result;
                BigDecimal opTop1 = stack.pop();
                BigDecimal opTop2 = stack.pop();
                switch (opThis) {
                    case "+":
                        result = opTop2.add(opTop1);
                        break;
                    case "-":
                        result = opTop2.subtract(opTop1);
                        break;
                    case "*":
                        result = opTop2.multiply(opTop1);
                        break;
                    case "/":
                        if (opTop1.compareTo(BigDecimal.ZERO) == 0) {
                            throw new RuntimeException("被除数不能为0");
                        }
                        result = opTop2.divide(opTop1, 2, RoundingMode.HALF_UP);
                        break;
                    case "%":
                        result = opTop1.remainder(opTop1);
                        break;
                    default:
                        throw new RuntimeException("除【+、-、*、/、%、绝对值、四舍五入】外，暂不支持其他操作符");
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

}
