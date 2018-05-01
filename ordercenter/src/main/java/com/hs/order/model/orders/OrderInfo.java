package com.hs.order.model.orders;

public class OrderInfo {

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getShipid() {
        return shipid;
    }

    public void setShipid(Integer shipid) {
        this.shipid = shipid;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Integer getOrderinfoid() {
        return orderinfoid;
    }

    public void setOrderinfoid(Integer orderinfoid) {
        this.orderinfoid = orderinfoid;
    }

    private Integer orderinfoid;
    private Integer userid;
    private Integer shipid;
    private Integer paytype;

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    private Float totalprice;
    private Integer orderstatus;
}
