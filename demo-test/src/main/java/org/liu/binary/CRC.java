package org.liu.binary;

/**
 * 这是一篇从二进制出发的理论相关的获取CRC相关的文章
 * https://www.cnblogs.com/DyLoder/p/10206555.html
 */
public class CRC {

    public static void main(String[] args) {
        CRC crc = new CRC();
        crc.testCRC();
    }

    public void testCRC() {
        //满足通项公式的值 例如:P(X)=x^4+x^3+1
        int a=0b1101;
        //数据
        int b=0b101001;
        System.out.println("fcs:"+Integer.toBinaryString(createFCS(b, a)));
        int data=encrypt(b,a);
        System.out.println("data:"+Integer.toBinaryString(data));

        //CRC验证数据是否不匹配 接收端对数据用同样的除数进行模二运算
        System.out.println(m2Div(data, a));
    }
    /**
     * 生成验证数据
     * @param data 要验证的数据
     * @param b 满足通项公式的值 例如 P(X)=x^4+x^3+1
     * */
    int encrypt(int data,int b) {
        int fcs=createFCS(data,b);
        int length=intLength(b, 2)-1;
        return (data<<length)+fcs;
    }
    /**
     * 得到FCS
     * */
    int createFCS(int a,int b){
        int length=intLength(b, 2)-1;
        return m2Div(a<<length, b);
    }
    /**
     * 模2除法运算
     * @return int 余数
     * 改为e返回商
     * */
    int m2Div(int a,int b) {
        int lengtha=intLength(a,2);
        int lengthb=intLength(b,2);
        //存储余数
        int d=0;
        //存储商
        int e=0;
        for(int i=lengtha;i>0;i--) {
            int c=getIndex(a,i);
            d=(d<<1)+c;
            e=e<<1;
            if(intLength(d, 2)==lengthb) {
                e=e+1;
                d=d^b;
            }
        }
        return d;
    }
    int intLength(int num,int radix) {
        for(int i=0;i<32;i++) {
            int c=(int) Math.pow(radix, i);
            if(num%c==num) {
                return i;
            }
        }
        return 0;
    }
    int getIndex(int a,int index) {
        return a>>(index-1)&1;
    }

}
