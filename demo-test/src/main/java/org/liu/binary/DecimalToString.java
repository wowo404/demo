package org.liu.binary;

import java.math.BigDecimal;

public class DecimalToString {

    /**
     * 直接用toString方法输出最大只能到9999999.999999998
     * toString(9999999.999999999)会输出9999999.999999998
     * toString(9999999.999999998 + 0.000000001)会输出科学计数法
     */
    public static void test() {
        double d = 9999999.999999999;
        System.out.println(d);
        System.out.println(d + 0.000000001);//非精确的输出

        //由于先使用了Double.toString，输出结果精确程度一般，比直接打印好
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(d));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(0.000000001));
        System.out.println(bigDecimal1.add(bigDecimal2));//精确的输出，结果为：9999999.9999999990

        //这是最精确的计算方式
        BigDecimal bigDecimal3 = new BigDecimal("9999999.999999999");
        BigDecimal bigDecimal4 = new BigDecimal("0.000000001");
        System.out.println(bigDecimal3.add(bigDecimal4));//精确的输出
    }

    public static void main(String[] args) {
        test();
    }

}
