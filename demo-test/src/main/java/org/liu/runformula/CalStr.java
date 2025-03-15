/*
 * Project: seaway-p2p-biz-mgr
 *
 * File Created at 2014年1月13日 下午3:34:53
 *
 * Copyright 2012 seaway.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Seaway Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with seaway.com.
 */
package org.liu.runformula;

import java.util.ListIterator;
import java.util.Stack;

/**
 * 银行利息计算
 */
public class CalStr {
    private String src;

    /**
     * constructor
     *
     * @param src the string(expression) to calculate
     */
    public CalStr(String src) {
        this.src = src;
    }

    /**
     * calculate to get the result
     *
     * @return(double)result
     */
    public double getResult() {
        String postfix = getPostfix();
        Stack stk = new Stack();
        // System.out.println(postfix);
        String parts[] = postfix.split(" +");
        double result = 0;
        for (int i = 0; i < parts.length; i++) {
            char tmp = parts[i].charAt(0);
            if (!isOperator(tmp)) {
                stk.push(parts[i]);
            } else {
                double a = Double.parseDouble((String) stk.pop());
                double b = Double.parseDouble((String) stk.pop());
                // b is followed by a in the orignal expression
                result = calculate(b, a, tmp);
                stk.push(String.valueOf(result));
            }
        }
        return result;
    }

    /**
     * test if the character is an operator,such +,-,*,/
     *
     * @param op the character to test
     * @returntrue if op is an operator otherwise false
     */
    private boolean isOperator(char op) {
        return (op == '+' || op == '-' || op == '*' || op == '/');
    }

    /**
     * calculate an expression such (a op b)
     *
     * @param a  number 1
     * @param b  number 2
     * @param op the operator
     * @return(double)(a op b)
     */
    public double calculate(double a, double b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return -1;
    }

    /**
     * convert the suffix to postfix
     *
     * @return the postfix as a string
     */
    private String getPostfix() {
        Stack stk = new Stack();
        String postfix = new String();
        char op;
        int i = 0;
        while (i < src.length()) {
            if (Character.isDigit(src.charAt(i)) || src.charAt(i) == '.') {
                postfix += " ";
                do {
                    postfix += src.charAt(i++);
                } while ((i < src.length()) && (Character.isDigit(src.charAt(i))));
                postfix += " ";
            } else {
                switch (op = src.charAt(i++)) {
                    case '(':
                        stk.push("(");
                        break;

                    case ')':
                        while (stk.peek() != "(") {
                            String tmp = (String) stk.pop();
                            postfix += tmp;
                            if (tmp.length() == 1 && isOperator(tmp.charAt(0)))
                                postfix += " ";
                        }
                        stk.pop();
                        postfix += " ";
                        break;

                    case '+':
                    case '-':
                        while ((!stk.empty()) && (stk.peek() != "(")) {
                            postfix += stk.pop() + " ";
                        }
                        stk.push(String.valueOf(Character.valueOf(op)));
                        break;

                    case '*':
                    case '/':
                        while ((!stk.empty()) && ((stk.peek() == "*") || (stk.peek() == "/"))) {
                            postfix += stk.pop() + " ";
                        }
                        stk.push(String.valueOf(Character.valueOf(op)));
                        break;
                }
            }
        }
        ListIterator it = stk.listIterator(stk.size());
        while (it.hasPrevious())
            postfix += it.previous() + " ";
        return postfix.trim().replaceAll(" +\\\\.", ".");
    }

    /**
     * main function
     *
     * @param args
     */
    public static void main(String args[]) {
        System.out.println(new CalStr("((1.5+6.000)*9+9.36)*(8+9-8*8+8*7)").getResult());
    }
}
