package org.liu.json;

import java.math.BigDecimal;

public class VoucherRule extends BaseRule {

	private Integer orderNumber;// 序号，在抵扣券规则中唯一标识（1，2，3，4，5）
	private Integer validTerm;// 有效期
	private BigDecimal couponAmount;// 抵扣券面值
	private Integer gainConditionOfInviteNumber;// 获得条件--邀友数量
    private Integer gainConditionOfNewRegister;//获得条件--新注册(0-新注册可以领取，1-不可以)
	private BigDecimal useConditionOfInvestAmount;// 使用条件--投资金额
	private Integer useConditionOfProductDeadline;// 使用条件--产品期限（天）
	private Integer useConditionOfProductTerm;//使用条件--产品期限（期数）

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getValidTerm() {
		return validTerm;
	}

	public void setValidTerm(Integer validTerm) {
		this.validTerm = validTerm;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

    public Integer getGainConditionOfInviteNumber() {
		return gainConditionOfInviteNumber;
	}

	public void setGainConditionOfInviteNumber(Integer gainConditionOfInviteNumber) {
		this.gainConditionOfInviteNumber = gainConditionOfInviteNumber;
	}

    public Integer getGainConditionOfNewRegister() {
        return gainConditionOfNewRegister;
    }

    public void setGainConditionOfNewRegister(Integer gainConditionOfNewRegister) {
        this.gainConditionOfNewRegister = gainConditionOfNewRegister;
    }

    public BigDecimal getUseConditionOfInvestAmount() {
		return useConditionOfInvestAmount;
	}

	public void setUseConditionOfInvestAmount(BigDecimal useConditionOfInvestAmount) {
		this.useConditionOfInvestAmount = useConditionOfInvestAmount;
	}

	public Integer getUseConditionOfProductDeadline() {
		return useConditionOfProductDeadline;
	}

	public void setUseConditionOfProductDeadline(Integer useConditionOfProductDeadline) {
		this.useConditionOfProductDeadline = useConditionOfProductDeadline;
	}

    public Integer getUseConditionOfProductTerm() {
        return useConditionOfProductTerm;
    }

    public void setUseConditionOfProductTerm(Integer useConditionOfProductTerm) {
        this.useConditionOfProductTerm = useConditionOfProductTerm;
    }
}
