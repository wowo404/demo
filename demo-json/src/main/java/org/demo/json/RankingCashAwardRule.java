package org.demo.json;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 排名现金奖励规则
 *
 * @author liuzhangsheng
 * @create 2018/7/9
 */
@Getter
@Setter
public class RankingCashAwardRule extends BaseRule {

    private Integer ranking;//排名
    private BigDecimal awardAmount;//奖励金额

}
