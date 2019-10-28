package org.demo.json;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liuzhsh on 2017/11/29.
 */
@Getter
@Setter
public class RegisterRewardRule extends BaseRule {

	private List<RedPackageRule> redPackageRuleList;//注册礼包中的每一张券都会包含一套获取和兑付规则
    private BigDecimal totalAmount;//红包总金额
    private BigDecimal amount;//金额
    private Integer validTerm;//有效期
    private Integer orderNumber;//序号，在注册新手礼包中的唯一标识（1，2，3，4）
    
}
