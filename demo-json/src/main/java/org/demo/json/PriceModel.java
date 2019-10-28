package org.demo.json;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 价格领域模型
 *
 * @author liuzhangsheng
 * @create 2019/3/28
 */
@Data
public class PriceModel {
    private Integer id;//一条数据一个ID
    private BigDecimal personalPrice;//个人出资
    private BigDecimal subsidyPrice;//政府补贴
    private BigDecimal totalPrice;//总价格=个人出资+政府补贴
    //社保类型（0-低保户，1-残疾人，2-养老保）---管理员手动输入，类型不定死
    private String applyCondition;
}
