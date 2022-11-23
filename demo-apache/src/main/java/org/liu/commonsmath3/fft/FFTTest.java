package org.liu.commonsmath3.fft;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.math3.complex.Complex;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class FFTTest {

    public static void main(String[] args) throws IOException {
        test3();
    }

    public static void test3() throws IOException {
        String[] split = getFileData();
        double[] doubles = new double[split.length];
        for (int i = 0; i < split.length; i++) {
            doubles[i] = Double.parseDouble(split[i]);
        }

        Complex[] fft = FFTByCommonsMath.fft(doubles);
        for (Complex complex : fft) {
            System.out.println(complex);
        }
    }

    private static String[] getFileData() throws IOException {
        String content = FileUtils.readFileToString(new File("D:\\home\\manufacture\\file\\2019\\11\\01\\1234567800000002-7-20191101145544.txt"), Charset.defaultCharset());
        content = content.replaceFirst(",", "");
        String[] split = content.split(",");
        return split;
    }

    //无效
    public static void test2() throws IOException {
        String[] split = getFileData();
        double[] doubles = new double[split.length];
        for (int i = 0; i < split.length; i++) {
            doubles[i] = Double.parseDouble(split[i]);
        }

        Map<String, Object> stringObjectMap = FFTUtil.freqSpectrumq(doubles, 12800);
        Object x = stringObjectMap.get("x");
        Object y = stringObjectMap.get("y");
        System.out.println(ToStringBuilder.reflectionToString(x));
        System.out.println(ToStringBuilder.reflectionToString(y));
    }

    public static void test1() throws IOException {
        String[] split = getFileData();
        float[] arr = new float[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Float.parseFloat(split[i]);
        }
        FFT fft = new FFT();
        fft.calculate(arr);
        System.out.println(ToStringBuilder.reflectionToString(arr));
    }

}
