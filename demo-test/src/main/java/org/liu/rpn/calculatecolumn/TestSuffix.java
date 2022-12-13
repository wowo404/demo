package org.liu.rpn.calculatecolumn;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/12/12 15:46
 **/
public class TestSuffix {

    public static void main(String[] args) {
        String text = "({$a}+{$b})*1000-({$h}*100)+{#abs0}/({#round0}+{$g})";
        InfixToSuffix infixToSuffix = new InfixToSuffix(text);
        List<String> list = infixToSuffix.parse();
        System.out.println(String.join("", list));
        //345+*612+/-
        //{salesAmountOfCommonTaxRate1}{salesAmountOfTaxFree1}{investmentAmount}+*{salesAmountOfTaxFree1}{salesAmountOfCommonTaxRate1}{salesAmountOfTaxFree2}+/-
        SuffixToMongoGrammar suffixToMongoGrammar = new SuffixToMongoGrammar(list);
        String mongoGrammar = suffixToMongoGrammar.parse();
        System.out.println(mongoGrammar);
    }

}
