package org.liu.model;

public enum OriginFormTypeEnum implements BaseEnum {
    PURCHASING_ORDER(0, "采购订单"), PURCHASING_STORAGE(1, "采购入库单"),
    SALE_RETURN_NOTE(2, "销售退货单"), OTHER_STORAGE(3, "其他入库单"),
    PRODUCE_STORAGE(4, "生产入库单");

    public Integer code;
    public String value;

    OriginFormTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
