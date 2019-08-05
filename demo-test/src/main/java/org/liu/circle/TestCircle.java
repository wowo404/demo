package org.liu.circle;

public class TestCircle {

    public static void main(String[] args) {
        doWhile(5);
    }

    public static void doWhile(int i){
        do {
            System.out.println("do while");
            i--;
        } while (i == 1);
        System.out.println("over");
    }

}
