package org.demo.ansj;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.List;

/**
 * @Author lzs
 * @Date 2023/2/23 9:29
 **/
public class TestAnsj {

    public static void main(String[] args) {
//        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
        String str = "打铁8111";
        List<Term> terms = ToAnalysis.parse(str).getTerms();
        for (Term term : terms) {
            System.out.println(term.getRealName());
        }
    }

}
