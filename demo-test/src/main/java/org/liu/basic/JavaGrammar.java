package org.liu.basic;

public class JavaGrammar {

    public static void main(String[] args) {
        ClassCastModel castModel = new ClassCastModel();
        castModel.setId(1L);
        referenceArgs(castModel);
        System.out.println(castModel);
        tableSizeFor();
    }

    /**
     * 操作符
     */
    static void operator(int a, int b, int c) {
        //此条件相当于(a == 0 && b == 1) || c == 2
        if (a == 0 && b == 1 || c == 2) {
            System.out.println("ok");
        } else {
            System.out.println("fuck");
        }
    }

    //返回大于或等于cap的2的倍数的那个值
    static void tableSizeFor() {
        int cap = 256000;
        int n = cap - 1;
        System.out.println(n >>> 1);//127999
        System.out.println(n >>> 2);//63999
        n |= n >>> 1;//等价于n = n | n >>> 1
        System.out.println(n);
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int b = (n < 0) ? 1 : n + 1;
        System.out.println(b);
    }

    public static void testIntAnd() {
        System.out.println(Integer.toBinaryString(1));//00000001
        System.out.println(Integer.toBinaryString(2));//00000010
        int a = 1 & 2;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));//00000000
    }

    public static void testSwitch() {
        int i = 0;
        switch (i) {
            case 0:
            case 1:
            case 2:
                System.out.println("匹配012");
                break;
            case 3:
                System.out.println("匹配3");
                break;
        }
    }

    public static void testRetry() {
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ",");
                if (j == 3)
                    continue retry;//这里等同于break
            }
        }
        abc:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ",");
                if (j == 3)
                    break abc;//如果下面没有代码继续执行，则这里可以替换为return
            }
        }
        System.out.println("ok,it's done!");
        //不用标识位，通常的写法是如下：
        boolean tag = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ",");
                if (j == 3) tag = true;
            }
            if (tag) break;
        }
    }

    public static void referenceArgs(ClassCastModel castModel) {
        castModel.setId(2L);
        castModel = new ClassCastModel();
        castModel.setId(111L);
        System.out.println(castModel);
    }

}
