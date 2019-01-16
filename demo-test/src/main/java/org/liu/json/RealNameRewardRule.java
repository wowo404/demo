package org.liu.json;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liuzhsh on 2017/11/29.
 */
@Getter
@Setter
public class RealNameRewardRule extends BaseRule {

    private BigDecimal amount;//金额
    private Integer cashRatio;
    private Integer validTerm;//有效期：7天
    private Integer registerInterval;//注册30天内实名认证有效，此次记录30
    private List<RedPackageRule> redPackageRuleList;//获取和兑付规则

}
