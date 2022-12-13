package org.liu.rpn;

/**
 * 逆波兰表示法，重定向自后缀表达式
 *
 * @Author lzs
 * @Date 2022/12/12 10:16
 **/
public class ReversePolishNotation {

    public static void main(String[] args) {
        String s = "3*(4+5)-6/(1+2%2)";
        InToPost inToPost = new InToPost(s);
        String trans = inToPost.doTrans();
        System.out.println(trans);//345+*6122%+/-
        ParsePost parsePost = new ParsePost(trans);
        int output = parsePost.doParse();
        System.out.println(output);
    }

}
