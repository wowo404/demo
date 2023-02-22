package org.demo.smartcn;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.StringReader;

/**
 * @Author lzs
 * @Date 2023/2/21 10:54
 **/
public class TestSmartcn {

    public static void main(String[] args) {
        System.out.println(segText("打铁8111"));
    }

    private static final SmartChineseAnalyzer SMART_CHINESE_ANALYZER = new SmartChineseAnalyzer();

    private static String segText(String text) {
        StringBuilder result = new StringBuilder();
        try {
            TokenStream tokenStream = SMART_CHINESE_ANALYZER.tokenStream("text", new StringReader(text));
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
                result.append(charTermAttribute.toString()).append(" ");
            }
            tokenStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
