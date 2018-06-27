package com.hs.order.data.protocol;


public class CancelOrderReq {
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public CancelOrderReq(Integer orderId) {
        this.orderId = orderId;
    }

    private Integer orderId;
}
