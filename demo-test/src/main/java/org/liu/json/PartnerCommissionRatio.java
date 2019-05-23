package org.liu.json;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 保险产品合伙人分佣相关信息
 * 以json格式放入ext_info字段
 *
 * @author liuzhangsheng
 * @create 2019/3/30
 */
@Data
public class PartnerCommissionRatio {
    private Integer level;//合伙人级别
    private Integer partnerId;
    private String partnerName;
    private BigDecimal ratio;//分佣比例
}
