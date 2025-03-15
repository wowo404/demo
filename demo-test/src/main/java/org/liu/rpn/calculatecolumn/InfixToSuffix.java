package org.liu.rpn.calculatecolumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式转后缀表达式
 *
 * @Author lzs
 * @Date 2022/12/12 10:44
 **/
public class InfixToSuffix {

    private Stack<Character> stack;
    private String input;
    private List<String> output;
    private static final String reg = "[+\\-*/%()]";

    public InfixToSuffix(String input) {
        this.stack = new Stack<>();
        this.input = input;
        this.output = new ArrayList<>();
    }

    public List<String> parse() {
        int len = 0;
        for (int i = 0; i < input.length(); i++) {
            char opThis = input.charAt(i);
            switch (opThis) {
                case '+':
                    len = processNormalText(len, i);
                    processOperator(opThis, 1);
                    break;
                case '-':
                    //兼容负数
                    if (i == 0) {
                        len++;
                    } else if (Character.valueOf(input.charAt(i - 1)).toString().matches(reg)){
                        len++;
                    } else {
                        len = processNormalText(len, i);
                        processOperator(opThis, 1);
                    }
                    break;
                case '*':
                case '/':
                case '%':
                    len = processNormalText(len, i);
                    processOperator(opThis, 2);
                    break;
                case '(':
                    len = processNormalText(len, i);
                    stack.push(opThis);
                    break;
                case ')':
                    len = processNormalText(len, i);
                    processRightBracket();
                    break;
                default:
                    len++;
                    if (i == input.length() - 1) {
                        processNormalText(len, input.length());
                    }
                    break;
            }
        }
        while (!stack.isEmpty()) {
            output.add(stack.pop().toString());
        }
        return output;
    }

    private int processNormalText(int len, int index) {
        if (len != 0) {
            String normalText = input.substring(index - len, index);
            output.add(normalText);
        }
        return 0;
    }

    private void processOperator(char opThis, int priority) {
        while (!stack.isEmpty()) {
            Character opTop = stack.pop();
            //括号有较高优先级重新压入栈中
            if (opTop == '(') {
                stack.push(opTop);
                break;
            } else {
                int stackTopPriority;
                //+，-优先级都是1
                if (opTop == '+' || opTop == '-') {
                    stackTopPriority = 1;
                } else {
                    stackTopPriority = 2;
                }
                //如果当前优先级大于栈顶部的优先级，重新压入栈中，否则出栈加入到后缀表达式字符串中
                if (stackTopPriority < priority) {
                    stack.push(opTop);
                    break;
                } else {
                    output.add(opTop.toString());
                }
            }
        }
        stack.push(opThis);
    }

    private void processRightBracket() {
        while (!stack.isEmpty()) {
            Character chx = stack.pop();
            //如果是'('直接返回，其他操作符直接拼接到后缀表达式中。
            if (chx == '(') {
                break;
            } else {
                output.add(chx.toString());
            }
        }
    }
}
