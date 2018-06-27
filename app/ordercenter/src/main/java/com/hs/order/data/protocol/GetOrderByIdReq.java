package com.hs.order.data.protocol;


/**
 * 查询的订单ID
 */
public class GetOrderByIdReq {
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public GetOrderByIdReq(Integer orderId) {
        this.orderId = orderId;
    }

    private Integer orderId;
}
