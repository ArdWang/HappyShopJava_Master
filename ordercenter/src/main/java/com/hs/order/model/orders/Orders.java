package com.hs.order.model.orders;

import com.hs.order.model.ShipAddre;

import java.util.List;

/**
 * Created by rnd on 2018/4/20.
 * 订单实体类
 */

public class Orders {
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }


    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShipAddre getShipAddre() {
        return shipAddre;
    }

    public void setShipAddre(ShipAddre shipAddre) {
        this.shipAddre = shipAddre;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public int getShipid() {
        return shipid;
    }

    public void setShipid(int shipid) {
        this.shipid = shipid;
    }

    private int shipid;
    private int orderId;
    private int payType;

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    private Float totalPrice;
    private int orderStatus;
    private ShipAddre shipAddre;
    private List<OrderGoods> orderGoods;
}
