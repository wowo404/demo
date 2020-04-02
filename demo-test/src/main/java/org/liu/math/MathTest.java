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

}
