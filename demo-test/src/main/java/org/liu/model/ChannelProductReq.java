package org.liu.model;

/**
 * Created by hello on 2017/11/9.
 */
public class ChannelProductReq {

    private String userId;//渠道平台的用户ID
    private String phone;//用户手机号码

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
