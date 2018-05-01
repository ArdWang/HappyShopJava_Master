package com.hs.order.model.respon;

import com.hs.order.model.ShipAddre;
import java.util.List;


/**
 * 返回回来地址
 */
public class ShipListAddre {

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

    private String code;
    private String message;
    private List<ShipAddre> shipAddres;


}
