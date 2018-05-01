package com.hs.order.data.protocol;

import com.hs.order.model.orders.Orders;

/**
 * Created by Administrator on 2018/4/21.
 *
 */

public class SubmitOrderReq {


    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    private Orders orders;

    public SubmitOrderReq(Orders orders){
        this.orders = orders;
    }
}
