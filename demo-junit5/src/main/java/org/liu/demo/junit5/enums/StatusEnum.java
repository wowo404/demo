package org.liu.demo.junit5.enums;

/**
 * @author lzs
 * @Date 2024/8/16 17:38
 **/
public enum StatusEnum {
    NORMAL(1, "正常"), TEMPORARY_DISABLE(2, "临时禁用"), PERMANENT_DISABLE(3, "永久禁用");

    private Integer code;
    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
