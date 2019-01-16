package org.liu.interest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by: wenbi.chen Created Time: 2017-12-21 8:33 PM Description:
 **/
public class InterestCalculateUtils {

	private static final RoundingMode roundingMode = RoundingMode.DOWN;

	/**
	 * 根据浮动利率计算总利息
	 * 
	 * @param floatRate 浮动利率，英文逗号分割
	 * @param period 期数
	 * @param investAmt 投资金额
	 * @return
	 */
	public static BigDecimal sumInterest(String floatRate, Integer period, BigDecimal investAmt) {
		String[] rateArray = floatRate.split(",");
		// 计算初始利率下的本金、利息、每月还款额
		List<SingleTermInfo> singleTermInfoList = calculateAllTermEqual(investAmt, new BigDecimal(rateArray[0]),
				period);
		BigDecimal totalInterest = BigDecimal.ZERO;
		BigDecimal principal = investAmt;
		for (int i = 0; i < rateArray.length; i++) {
			System.out.println(principal);
			List<SingleTermInfo> singleTermInfos = calculateAllTermEqual(principal, new BigDecimal(rateArray[i]),
					period);
			System.out.println(singleTermInfos);
			// 每次发生还款，本金在改变，期数也在递减
			principal = principal.subtract(singleTermInfoList.get(i).getPrinciple());
			period--;
			totalInterest = totalInterest.add(singleTermInfos.get(0).getProfit());
		}
		return totalInterest;
	}

	/**
	 * 计算等额本息还款计划
	 *
	 * @param principal 投资金额
	 * @param interestOfYear 年利率
	 * @param term 总期数
	 * @return
	 */
	public static List<SingleTermInfo> calculateAllTermEqual(BigDecimal principal, BigDecimal interestOfYear,
			Integer term) {

		List<SingleTermInfo> result = new LinkedList<>();
		BigDecimal monthRate = getMonthRateByYearRate(interestOfYear);
		BigDecimal monthRepay = getMonthRepayment(principal, term, monthRate);// 每月还款额
		BigDecimal surplusPrincipal = principal;// 剩余本金
		for (int i = 1; i < term + 1; i++) {
			// 每月利息 = 剩余本金 x 贷款月利率
			BigDecimal profit = surplusPrincipal.multiply(monthRate).setScale(2, roundingMode);
			// 每月本金 = 每月还款额 - 每月利息
			BigDecimal monthPrincipal;
			if (i < term) {
				monthPrincipal = monthRepay.subtract(profit);
			} else {
				monthPrincipal = surplusPrincipal;
			}
			// 每月本金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1)
			result.add(new SingleTermInfo(i, profit, monthPrincipal, monthRepay));
			surplusPrincipal = surplusPrincipal.subtract(monthPrincipal);// 剩余本金 = 剩余本金 - 已还本金
		}

		return result;
	}

	public static void main(String[] args) {
		BigDecimal sumInterest = sumInterest("0.078,0.083,0.098,0.108,0.118,0.128", 6, new BigDecimal("10000"));
        System.out.println(sumInterest);
	}

	/**
	 * 等额本息还款法 获得每月还款额,进行四舍五入
	 * 
	 * @param totalLoan 贷款本金
	 * @param lifeOfLoan 借款期限(多少个月)
	 * @param monthRate 月利率(小于1的小数)
	 * @return (totalLoan*monthRate*(1+monthRate)^lifeOfLoan)/((1+monthRate)^lifeOfLoan-1)
	 */
	public static BigDecimal getMonthRepayment(BigDecimal totalLoan, Integer lifeOfLoan, BigDecimal monthRate) {
		BigDecimal monthRatePower = monthRate.add(new BigDecimal("1")).pow(lifeOfLoan);
		BigDecimal left = totalLoan.multiply(monthRate).multiply(monthRatePower);
		BigDecimal right = monthRatePower.subtract(new BigDecimal("1"));

		return left.divide(right, 2, roundingMode);
	}

	/**
	 * 通过年利率获得月利率,结果采用Decimal128
	 * 
	 * @param yearRate 年利率(小于1的小数)
	 * @return yearRate/12=月利率
	 */
	public static BigDecimal getMonthRateByYearRate(BigDecimal yearRate) {
		return yearRate.divide(new BigDecimal("12"), 128, roundingMode);
	}

	/**
	 * 单期信息
	 */
	public static class SingleTermInfo {

		private int termId;
		private BigDecimal principle;
		private BigDecimal profit;
		private BigDecimal total;

		public SingleTermInfo(int termId, BigDecimal profit, BigDecimal principle, BigDecimal total) {
			this.termId = termId;
			this.principle = principle;
			this.profit = profit;
			this.total = total;
		}

		public int getTermId() {
			return termId;
		}

		public BigDecimal getPrinciple() {
			return principle;
		}

		public BigDecimal getProfit() {
			return profit;
		}

		public BigDecimal getTotal() {
			return total;
		}

		@Override
		public String toString() {
			return "SingleTermInfo{" + "termId=" + termId + ", principle=" + principle + ", profit=" + profit
					+ ", total=" + total + '}';
		}
	}

}
