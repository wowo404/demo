package org.liu.enums;

public enum Shape implements SelectionEnum {
    square("1"), triangle("2");

    String code;

    Shape(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
