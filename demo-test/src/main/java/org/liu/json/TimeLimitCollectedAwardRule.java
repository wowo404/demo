package org.liu.json;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 满标限时奖励规则
 * @author liuzhangsheng
 * @create 2018/7/9
 */
@Getter
@Setter
public class TimeLimitCollectedAwardRule extends BaseRule {

    private Integer timeLimit;//满标时间限制
    private Integer CarveUpNumber;//瓜分人数：投资总额前5名
    private BigDecimal totalAwardAmount;//奖励金额

}
