package org.liu.rpn;

import java.util.Stack;

/**
 * @Author lzs
 * @Date 2022/12/12 10:15
 **/
public class ParsePost {

    private Stack<Integer> stack;

    private String input;

    public ParsePost(String input) {
        this.input = input;
    }

    public int doParse() {
        stack = new Stack<>();
        char ch;
        int num1, num2, interAns;
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            System.out.println(" " + ch + " " + stack);
            if (ch >= '0' && ch <= '9') {
                stack.push(ch - '0');
            } else {
                num2 = stack.pop();
                num1 = stack.pop();
                switch (ch) {
                    case '+':
                        interAns = num1 + num2;
                        break;
                    case '-':
                        interAns = num1 - num2;
                        break;
                    case '*':
                        interAns = num1 * num2;
                        break;
                    case '/':
                        interAns = num1 / num2;
                        break;
                    case '%':
                        interAns = num1 % num2;
                        break;
                    default:
                        interAns = 0;
                        break;
                }
                stack.push(interAns);
            }
        }
        interAns = stack.pop();
        return interAns;
    }
}

