package org.liu.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathTest {

    public static void main(String[] args) {
        double atan2 = Math.atan2(0.09766, 10240);
        BigDecimal result = BigDecimal.valueOf(atan2).setScale(8, RoundingMode.HALF_UP).stripTrailingZeros();
        System.out.println(result.toPlainString());
        System.out.println(result.multiply(new BigDecimal("180")).divide(BigDecimal.valueOf(Math.PI), 8, RoundingMode.HALF_UP));

        double sqrt = Math.sqrt(0.09766 * 0.09766 + 10240 * 10240);
        System.out.println(sqrt);
    }

    public static void atan2(){
        double atan2 = Math.atan2(7.0, -7.0);
        System.out.println(atan2);
        double angle = atan2 * 180 / Math.PI;
        System.out.println(angle);
    }

    public static void split(){
        int a = 111;
        //分成5份
        int b = a / 5;
        System.out.println(b);
        int nextStartIndex = 0;
        for (int i = 1; i < 6; i++) {
            int c = i == 5 ? a : b * i;
            int start = nextStartIndex;
            System.out.println("第" + i + "次循环，start==" + start);
            for(int j = start; j < c; j++){

            }

            System.out.println("第" + i + "次循环，ent==" + (c - 1));
            nextStartIndex = c;
        }
    }

}
