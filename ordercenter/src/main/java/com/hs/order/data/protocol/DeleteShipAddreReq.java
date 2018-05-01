package com.hs.order.data.protocol;


public class DeleteShipAddreReq {
    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public DeleteShipAddreReq(Integer shipId) {
        this.shipId = shipId;
    }

    private Integer shipId;
}
