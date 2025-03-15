package org.liu.rpn;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author lzs
 * @Date 2022/12/12 10:13
 **/
public class InToPost {

    private Stack<Character> stack;
    private String input;
    private String output = "";

    public InToPost(String input) {
        this.stack = new Stack<>();
        this.input = input;
    }

    public String doTrans() {
        for (int i = 0; i < input.toCharArray().length; i++) {
            char ch = input.charAt(i);
            System.out.println("For " + ch + " " + stack);
            switch (ch) {
                case '+':
                case '-':
                    getOper(ch, 1);
                    break;
                case '*':
                case '/':
                case '%':
                    getOper(ch, 2);
                    break;
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    //遇到右括号，把括号中的操作符，添加到后缀表达式字符串中。
                    getParen(ch);
                    break;
                default:
                    output = output + ch;
                    break;
            }
        }
        while (!stack.isEmpty()) {
            System.out.println("While " + Arrays.asList(stack));
            output = output + stack.pop();
        }
        System.out.println("End " + Arrays.asList(stack));

        return output;
    }

    /**
     * 从input获得操作符
     *
     * @param opThis
     * @param currentPriority 操作符的优先级
     */
    private void getOper(char opThis, int currentPriority) {
        while (!stack.isEmpty()) {
            char opTop = stack.pop();
            //括号有较高优先级重新压入栈中
            if (opTop == '(') {
                stack.push(opTop);
                break;
            } else {
                int stackTopPriority;
                //+ ，-优先级都是1
                if (opTop == '+' || opTop == '-') {
                    stackTopPriority = 1;
                } else {
                    stackTopPriority = 2;
                }
                //如果当前优先级大于栈顶部的优先级，重新压入栈中，否则出栈加入到后缀表达式字符串中
                if (stackTopPriority < currentPriority) {
                    stack.push(opTop);
                    break;
                } else {
                    output = output + opTop;
                }
            }
        }
        stack.push(opThis);
    }

    public void getParen(char ch) {
        while (!stack.isEmpty()) {
            char chx = stack.pop();
            //如果是'('直接返回，其他操作符直接拼接到后缀表达式中。
            if (chx == '(') {
                break;
            } else {
                output = output + chx;
            }
        }
    }
}


