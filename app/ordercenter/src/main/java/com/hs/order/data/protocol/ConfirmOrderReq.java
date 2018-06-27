package com.hs.order.data.protocol;


public class ConfirmOrderReq {
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ConfirmOrderReq(Integer orderId) {
        this.orderId = orderId;
    }

    private Integer orderId;


}
