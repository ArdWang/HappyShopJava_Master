package com.paysdk.data.protocol;


public class GetPaySignReq {
    public GetPaySignReq(Integer orderId, Float totalPrice) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }



    private Integer orderId;

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    private Float totalPrice;
}
