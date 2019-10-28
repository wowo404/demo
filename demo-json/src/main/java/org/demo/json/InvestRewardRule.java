package org.demo.json;

import java.math.BigDecimal;

/**
 * Created by liuzhsh on 2017/11/29.
 */
public class InvestRewardRule extends BaseRule {

    private Integer type;//类型（1-一级奖励金，2-二级奖励金）
    private Integer validTerm;//有效期：365天
    private BigDecimal ratio;//百分比，存储格式如10%，则此处保存0.1

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValidTerm() {
        return validTerm;
    }

    public void setValidTerm(Integer validTerm) {
        this.validTerm = validTerm;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}
