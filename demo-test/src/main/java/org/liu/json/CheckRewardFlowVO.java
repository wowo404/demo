package org.liu.json;

/**
 * 检查奖励金流水响应VO
 *
 * @author liuzhangsheng
 * @create 2018/3/7
 */
public class CheckRewardFlowVO {
    private Integer source;
    private Integer userId;
    private Integer investRecordId;
    private String info;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInvestRecordId() {
        return investRecordId;
    }

    public void setInvestRecordId(Integer investRecordId) {
        this.investRecordId = investRecordId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
