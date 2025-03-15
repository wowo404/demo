package org.liu.rpn.calculatecolumn;

/**
 * mongodb的+,-,*,/,%,abs,round
 *
 * @Author lzs
 * @Date 2022/12/12 17:37
 **/
public enum MongoProjectionGrammarEnum {
    add("{$add:[{},{}]}", "+"),
    subtract("{$subtract:[{},{}]}", "-"),
    multiply("{$multiply:[{},{}]}", "*"),
    divide("{$divide:[{},{}]}", "/"),
    mod("{$mod:[{},{}]}", "%"),
    abs("{$abs:{}}", "abs"),
    round("{$round:[{},{}]}", "round");

    private String grammar, symbol;

    MongoProjectionGrammarEnum(String grammar, String symbol) {
        this.grammar = grammar;
        this.symbol = symbol;
    }

    public String getGrammar() {
        return grammar;
    }

    public String getSymbol() {
        return symbol;
    }
}
