package org.demo.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedPackageRule extends BaseRule {

	// 获得条件--邀友数量，例如：[0,9]，表示邀请好友数量在0-9之间可以使用此规则
	private Integer[] gainConditionOfInviteNumber;
	private Integer cashRatio;//兑付比例，如：500：1，此处存500
	private Integer cashRatioValidTerm;//兑付比例有效期
	
}
