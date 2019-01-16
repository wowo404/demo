package org.liu.eventbus;

/**
 * Created by liuzhsh on 2017/11/21.
 */
public class OrderEvent {
    private Long orderId;
    private Double amount;

    public OrderEvent(Long orderId, Double amount){
        this.orderId = orderId;
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
