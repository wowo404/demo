/*
 * Project: seaway-p2p-biz-mgr
 * 
 * File Created at 2014年1月13日 下午2:41:01
 * 
 * Copyright 2012 seaway.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Seaway Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with seaway.com.
 */
package org.liu.interest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 还款计算算法工具类 
 * @author JustingLiu
 */
public class RepaymentAlgorithm {

    /**
     * 相加
     */
    public static Long add(Long one, Long two) {
        return new BigDecimal(one.toString()).add(new BigDecimal(two.toString())).longValue();
    }

    /**
     * 相减
     */
    public static Long subtract(Long one, Long two) {
        return new BigDecimal(one.toString()).subtract(new BigDecimal(two.toString())).longValue();
    }

    /**
     * 3个数相减
     */
    public static Long subtractTwo(Long one, Long two, Long three) {
        return new BigDecimal(one.toString()).subtract(new BigDecimal(two.toString()))
                .subtract(new BigDecimal(three.toString())).longValue();
    }

    /**
     * one - two + three
     */
    public static Long subtractAdd(Long one, Long two, Long three) {
        return new BigDecimal(one.toString()).subtract(new BigDecimal(two.toString()))
                .add(new BigDecimal(three.toString())).longValue();
    }

    /**
     * one - two - three + four
     */
    public static Long subtractSubtractAdd(Long one, Long two, Long three, Long four) {
        return new BigDecimal(one.toString()).subtract(new BigDecimal(two.toString()))
                .subtract(new BigDecimal(three.toString())).add(new BigDecimal(four.toString())).longValue();
    }

    /**
     * one * two 相乘,结果是整数,进行四舍五入
     */
    public static Long multiply(Long one, Long two) {
        return new BigDecimal(one.toString()).multiply(new BigDecimal(two.toString()))
                .setScale(0, RoundingMode.HALF_UP).longValue();
    }

    /**
     * Long * Double 相乘,结果是整数,进行四舍五入
     */
    public static Long longMultiplyDouble(Long one, Double two) {
        return new BigDecimal(one.toString()).multiply(new BigDecimal(two.toString()))
                .setScale(0, RoundingMode.HALF_UP).longValue();
    }

    /**
     * double/int 保留2位小数，四舍五入
     */
    public static Double doubleDivideInt(Double one, Integer two) {
        return new BigDecimal(one.toString()).divide(new BigDecimal(two.toString()), 2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * one / two计算投资占比和项目进度 相除,结果保留7位小数,四舍五入
     */
    public static Double divide(Long one, Long two) {
        return new BigDecimal(one.toString()).divide(new BigDecimal(two.toString()), 7, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * recSum/hundred元转分
     */
    public static Long doubleMultiplyInt(Double yuan, Integer hundred) {
        return new BigDecimal(yuan.toString()).multiply(new BigDecimal(hundred.toString()))
                .setScale(0, RoundingMode.HALF_UP).longValue();
    }

    /**
     * fen/hundred分转元
     */
    public static Double longDivideInt(Long fen, Integer hundred) {
        return new BigDecimal(fen.toString()).divide(new BigDecimal(hundred.toString()), 2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * fen/no long除以int，返回long
     */
    public static Long longDivideIntReturnLong(Long fen, Integer no) {
        return new BigDecimal(fen.toString()).divide(new BigDecimal(no.toString()), 0, RoundingMode.HALF_UP)
                .longValue();
    }

    /**
     * yuan * 100將一個字符類型的元轉為分
     */
    public static Long strYuanToFen(String yuan) {
        return new BigDecimal(yuan).multiply(new BigDecimal("100")).longValue();
    }

    /**
     * sum / totalNo 根据应还总金额和还款期數計算每一期应还总额，结果四舍五入
     */
    public static Long sumDivideInt(Long sum, Integer totalNo) {
        return new BigDecimal(sum.toString()).divide(new BigDecimal(totalNo.toString()), 0, RoundingMode.HALF_UP)
                .longValue();
    }
    
    /**
     * 计算每一期投资人应得的本金或者利息 结果是整数,进行四舍五入
     * @param totalMoney 投资人投资的金额或者投资人应得的总利息
     * @param totalNo 还款期数
     * @return totalMoney/totalNo
     */
    public static Long getOneTermMoney(Long totalMoney, Integer totalNo) {
        return new BigDecimal(totalMoney.toString())
                .divide(new BigDecimal(totalNo.toString()), 0, RoundingMode.HALF_UP).longValue();
    }

    /**
     * 逾期违约金 =（月需还款金额－实际还款金额）×逾期天数×0.002
     * @param monthRepayMoney 月需还款金额
     * @param realRepayMoney 实际还款金额
     * @param overdueDays 逾期天数
     * @return (monthRepayMoney-realRepayMoney)*overdueDays*0.002
     */
    public static Long calculateOverdueMoney(Long monthRepayMoney, Long realRepayMoney, Integer overdueDays) {
        Long unpaidAmount = monthRepayMoney - realRepayMoney;// 未还金额
        return new BigDecimal(unpaidAmount.toString()).multiply(new BigDecimal(overdueDays.toString()))
                .multiply(new BigDecimal("0.002")).setScale(0, RoundingMode.HALF_UP).longValue();
    }

    /**
     * 等额本息还款法 获得每月还款额 结果是整数,进行四舍五入
     * @param totalLoan 贷款本金
     * @param lifeOfLoan 借款期限(多少个月)
     * @param monthRate 月利率(小于1的小数)
     * @return (totalLoan*monthRate*(1+monthRate)^lifeOfLoan)/((1+monthRate)^lifeOfLoan-1)
     */
    public static Long getMonthRepayment(Long totalLoan, Integer lifeOfLoan, Double monthRate) {
        Double monthRatePower = Math.pow(1 + monthRate, lifeOfLoan);
        Double left = new BigDecimal(totalLoan.toString()).multiply(new BigDecimal(monthRate.toString()))
                .multiply(new BigDecimal(monthRatePower.toString())).setScale(30, RoundingMode.HALF_DOWN).doubleValue();
        BigDecimal right = new BigDecimal(monthRatePower.toString()).subtract(new BigDecimal("1"));

        return new BigDecimal(left.toString()).divide(right, 0, RoundingMode.HALF_DOWN).longValue();
    }
    
    public static void main(String[] args) {
    	Long amt = getMonthRepayment(1000000L, 6, getMonthRateByYearRate(0.078));
    	System.out.println(amt);
	}

    /**
     * 按月还款(等额本息)时,计算投资人应得的利息
     * @param investMoney 投资金额
     * @param yearRate 年利率
     * @param monthLife 多少个月
     * @return ((investMoney*monthRate*(1+monthRate)^monthLife)/((1+monthRate)^
     *         monthLife-1) * monthLife) - investMoney
     */
    public static Long getInterestWhenMonth(Long investMoney, Double yearRate, Integer monthLife) {
        Double monthRate = getMonthRateByYearRate(yearRate);// 月利率
        Double monthRatePower = Math.pow(1 + monthRate, monthLife);// (1+monthRate)^lifeOfLoan
        BigDecimal moneyBD = new BigDecimal(investMoney.toString());
        // 除号左边
        BigDecimal left = moneyBD.multiply(new BigDecimal(monthRate.toString())).multiply(
                new BigDecimal(monthRatePower.toString()));
        // 除号右边
        BigDecimal right = new BigDecimal(monthRatePower.toString()).subtract(new BigDecimal("1"));
        // 每月还款额
        BigDecimal everyMonthPay = left.divide(right, 30, RoundingMode.HALF_UP);
        return everyMonthPay.multiply(new BigDecimal(monthLife.toString())).subtract(moneyBD)
                .setScale(0, RoundingMode.HALF_UP).longValue();
    }

    /**
     * 按月还息，到期还本时总利息的计算方式
     * @param investMoney 投资金额
     * @param yearRate 年利率
     * @param monthLife 借多少个月
     */
    public static Long getInterestWhenPayPrincipalLast(Long investMoney, Double yearRate, Integer monthLife) {
        Double monthRate = getMonthRateByYearRate(yearRate);
        Long monthInterest = new BigDecimal(investMoney.toString()).multiply(new BigDecimal(monthRate.toString()))
                .setScale(0, RoundingMode.HALF_UP).longValue();
        return new BigDecimal(monthInterest.toString()).multiply(new BigDecimal(monthLife.toString()))
                .setScale(0, RoundingMode.HALF_UP).longValue();
    }

    /**
     * 天标(到期还本息)时计算投资人应得利息
     * @param investMoney 投资金额
     * @param yearRate 年利率
     * @param dayLife 多少天
     * @return investMoney * dayLife * yearRate / 360
     */
    public static Long getInterestWhenDay(Long investMoney, Double yearRate, Integer dayLife) {
        Double dayRate = getDayRateByYearRate(yearRate);
        return new BigDecimal(investMoney.toString()).multiply(new BigDecimal(dayLife.toString()))
                .multiply(new BigDecimal(dayRate.toString())).setScale(0, RoundingMode.HALF_UP).longValue();
    }

    /**
     * 通过年利率获得月利率,结果采用Decimal128
     * @param yearRate 年利率(小于1的小数)
     * @return yearRate/12=月利率
     */
    public static Double getMonthRateByYearRate(Double yearRate) {
        return new BigDecimal(yearRate.toString()).divide(new BigDecimal("12"), MathContext.DECIMAL128).doubleValue();
    }

    /**
     * 通过月利率获得日利率,结果采用Decimal128
     * @param monthRate 月利率(小于1的小数)
     * @return monthRate/30=日利率
     */
    public static Double getDayRateByMonthRate(Double monthRate) {
        return new BigDecimal(monthRate.toString()).divide(new BigDecimal("30"), MathContext.DECIMAL128).doubleValue();
    }

    /**
     * 通过年利率获得日利率,结果采用Decimal128
     * @param yearRate 年利率(小于1的小数)
     * @return yearRate/360=日利率
     */
    public static Double getDayRateByYearRate(Double yearRate) {
        return new BigDecimal(yearRate.toString()).divide(new BigDecimal("360"), MathContext.DECIMAL128).doubleValue();
    }

    /**
     * double * double 返回一个正整数
     */
    public static Long doubleMultiplyDouble(Double investMoney, Double pointsValue) {
        return new BigDecimal(investMoney.toString()).multiply(new BigDecimal(pointsValue.toString()))
                .setScale(0, RoundingMode.DOWN).longValue();

    }

    /**
     * 满标二审计算积分的方法 截取整数
     * @param investMoney
     * @param pointsValue
     * @author 李欣欣
     */
    public static Long doubleMultiplyDouble2(Double investMoney, Double pointsValue) {
        return new BigDecimal(investMoney.toString()).multiply(new BigDecimal(pointsValue.toString()))
                .setScale(0, RoundingMode.DOWN).longValue();
    }
    
    /**
     * 将double转换成string，整数位大于8位数时不会变成科学计数法
     */
    public static String doubleToString(Double money){
        if(null == money){
            return "";
        }
        DecimalFormat f = new DecimalFormat("#0.00");
        return f.format(money);
    }
    
    /**
     * 判断double是否含有小数位
     */
    public static Boolean doubleContainsDecimal(Double money){
        DecimalFormat f = new DecimalFormat("#0.00");
        String m = f.format(money);
        String second = m.substring(m.length()-2);
        if(Integer.valueOf(second) > 0){
            return true;
        }
        return false;
    }
  
}
