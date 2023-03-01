package org.demo.ansj;

import org.ansj.domain.Term;
import org.ansj.domain.TermNature;
import org.ansj.domain.TermNatures;
import org.ansj.recognition.impl.NatureRecognition;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.Arrays;
import java.util.List;

/**
 * @Author lzs
 * @Date 2023/2/23 9:29
 **/
public class TestAnsj {

    public static void main(String[] args) {
//        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
        String[] str = {"打铁8111", "家具销售", "道路运输", "家", "具"};
        for (String text : str) {
            List<Term> terms = IndexAnalysis.parse(text).getTerms();
            for (Term term : terms) {
                System.out.print(term);
                System.out.println(Arrays.toString(term.termNatures().termNatures));
            }
        }
        NatureRecognition natureRecognition = new NatureRecognition();
        TermNatures termNatures = natureRecognition.getTermNatures("具");
        System.out.println(Arrays.toString(termNatures.termNatures));
    }

}
