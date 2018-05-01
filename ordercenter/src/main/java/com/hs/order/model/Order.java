package com.hs.order.model;


import com.hs.order.model.orders.Orders;

/**
 * 订单的Order 实体 总类
 */
public class Order {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    private String message;
    private Orders orders;
}
