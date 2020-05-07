package org.liu.basic;

import org.junit.Assert;
import org.junit.Test;

public class BasicTest {

    @Test
    public void test(){
        int a = 1;
        System.out.println(Integer.toBinaryString(a));//00000000 00000000 00000000 00000001
        Assert.assertTrue((a << 30) > 0);
        Assert.assertTrue((a << 31) < 0);
        Assert.assertEquals((a << 31), Integer.MIN_VALUE);
    }

}
