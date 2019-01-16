package org.liu.model;

/**
 * Created by liuzhsh on 2017/11/10.
 */
public class InvestToH5Req {

    private String productId;
    private String userId;
    private String phone;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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
