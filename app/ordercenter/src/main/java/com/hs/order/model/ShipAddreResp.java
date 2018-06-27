package com.hs.order.model;


import java.io.Serializable;
import java.util.List;

public class ShipAddreResp implements Serializable {
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

    public List<ShipAddre> getShipAddres() {
        return shipAddres;
    }

    public void setShipAddres(List<ShipAddre> shipAddres) {
        this.shipAddres = shipAddres;
    }

    private String message;
    private List<ShipAddre> shipAddres;
}
