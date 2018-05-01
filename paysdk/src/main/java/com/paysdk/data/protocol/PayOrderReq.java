package com.paysdk.data.protocol;


public class PayOrderReq {
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public PayOrderReq(Integer orderId) {
        this.orderId = orderId;
    }

    private Integer orderId;


}
