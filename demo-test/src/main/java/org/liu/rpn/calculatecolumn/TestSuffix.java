package org.liu.rpn.calculatecolumn;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/12/12 15:46
 **/
public class TestSuffix {

    public static void main(String[] args) {
        testCalculateSuffix();
    }

    public static void testCalculateSuffix() {
        String text = "3*(4.5+5.2)-6.1/(1.6+2%2)";//29.1-3.81
        InfixToSuffix infixToSuffix = new InfixToSuffix(text);
        List<String> list = infixToSuffix.parse();
        System.out.println(String.join("", list));
        SuffixCalculator suffixCalculator = new SuffixCalculator(list);
        System.out.println(suffixCalculator.calculate());
    }

    public static void testSuffixToMongoGrammar() {
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
