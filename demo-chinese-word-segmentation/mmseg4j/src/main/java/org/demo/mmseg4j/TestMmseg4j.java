package org.demo.mmseg4j;

import com.chenlb.mmseg4j.*;

import java.io.IOException;
import java.io.StringReader;

/**
 * @Author lzs
 * @Date 2023/2/22 17:35
 **/
public class TestMmseg4j {

    public static void main(String[] args) {
        TestMmseg4j testMmseg4j = new TestMmseg4j();
        String text = "要是有这样的姐姐操心就不会这么烦了";
        System.out.println(testMmseg4j.segText(text, SIMPLE_SEG));
        System.out.println(testMmseg4j.segText(text, COMPLEX_SEG));
        System.out.println(testMmseg4j.segText(text, MAX_WORD_SEG));
    }

    private static final Dictionary DIC = Dictionary.getInstance();
    private static final SimpleSeg SIMPLE_SEG = new SimpleSeg(DIC);
    private static final ComplexSeg COMPLEX_SEG = new ComplexSeg(DIC);
    private static final MaxWordSeg MAX_WORD_SEG = new MaxWordSeg(DIC);

    private String segText(String text, Seg seg) {
        StringBuilder result = new StringBuilder();
        MMSeg mmSeg = new MMSeg(new StringReader(text), seg);
        try {
            Word word = null;
            while ((word = mmSeg.next()) != null) {
                result.append(word.getString()).append(" ");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return result.toString();
    }

}
