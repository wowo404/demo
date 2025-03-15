package algorithm.basic;

import java.math.BigInteger;

/**
 * 密钥交换算法即DH算法：Diffie-Hellman算法
 * 此算法需要共享p和g，这两个常量在RFC文档中定义：https://www.ietf.org/rfc/rfc3526.txt
 *
 * @author lzs
 * @Date 2024/9/20 14:41
 **/
public class DiffieHellman {

    public static void main(String[] args) {
        int p = 97;
        int g = 5;
        int a = 123;
        int A = powAndMod(g, a, p).intValue();
        System.out.println("甲的私钥为：" + a + "，公钥为：" + A);
        int b = 456;
        int B = powAndMod(g, b, p).intValue();
        System.out.println("乙的私钥为：" + b + "，公钥为：" + B);
        int s1 = powAndMod(A, b, p).intValue();
        System.out.println("乙计算得到了密钥：" + s1);
        int s2 = powAndMod(B, a, p).intValue();
        System.out.println("甲计算得到了密钥：" + s2);
        System.out.println("甲乙共同计算出了相同的密钥：" + (s1 == s2));
    }

    public static BigInteger powAndMod(int base, int exponent, int mod) {
        BigInteger pow = BigInteger.valueOf(base).pow(exponent);
        return pow.mod(BigInteger.valueOf(mod));
    }

}
