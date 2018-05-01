package com.hs.order.data.protocol;

/**
 * Created by Administrator on 2018/4/22.
 *
 */

public class GetAllOrderReq {
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public GetAllOrderReq(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    private Integer orderStatus;
}
