package org.liu.math.fft;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

/*
 * @description:快速傅里叶变换
 * */
public class FFTByCommonsMath {

    public static Complex[] fft(double[] inputData) {
        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        return fft.transform(inputData, TransformType.FORWARD);
    }

}