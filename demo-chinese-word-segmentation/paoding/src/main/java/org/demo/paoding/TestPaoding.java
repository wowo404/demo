package org.demo.paoding;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.StringReader;

/**
 * 如何使用看net.paoding.analysis.analyzer.PaodingAnalyzer#main(java.lang.String[])
 *
 * @Author lzs
 * @Date 2023/2/21 14:56
 **/
public class TestPaoding {

    public static void main(String[] args) {
//        String text = "要是有这样的姐姐操心就不会这么烦了";
        String text = "家具销售";
        System.out.println(seg(text, PaodingAnalyzer.MOST_WORDS_MODE));
        System.out.println(seg(text, PaodingAnalyzer.MAX_WORD_LENGTH_MODE));
    }

    private static final PaodingAnalyzer ANALYZER = new PaodingAnalyzer();

    private static String seg(String text, int mode) {
        ANALYZER.setMode(mode);
        StringBuilder result = new StringBuilder();
        try (TokenStream stream = ANALYZER.tokenStream("", new StringReader(text))) {
            stream.reset();
            CharTermAttribute termAtt = stream.addAttribute(CharTermAttribute.class);
            while (stream.incrementToken()) {
                result.append(termAtt.toString()).append(" ");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result.toString();
    }
}
