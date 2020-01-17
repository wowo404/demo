package org.liu.basic;

public class JavaGrammar {

    public static void testRetry(){
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

}
