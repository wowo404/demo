package org.demo.ikanalyzer;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

/**
 * @Author lzs
 * @Date 2023/2/21 10:10
 **/
public class TestIkAnalyzer {

    public static void main(String[] args) throws IOException {
        StringReader sr = new StringReader("打铁8111");
        IKSegmenter ik = new IKSegmenter(sr, false);
        Lexeme lexeme = null;
        while ((lexeme = ik.next()) != null) {
            System.out.println(lexeme.getLexemeText());
        }
    }

}
