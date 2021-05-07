package org.demo.json;

import lombok.Data;
import org.demo.json.enums.OriginFormTypeEnum;

@Data
public class BarcodeQueryReq {
    private Long pageNum;

    private Long pageSize;
    /**
     * 商品编号/名称/型号/助记码
     */
    private String fuzzyString;
    /**
     * 规格信息
     */
    private String specInfo;
    /**
     * 源单编号数组
     */
    private String[] originFormIds;
    /**
     * 单据类型（0-采购订单，1-采购入库单，2-销售退货单，3-其他入库单，4-生产入库单）
     */
    private OriginFormTypeEnum originFormType;
}
