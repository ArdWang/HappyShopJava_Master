package com.hs.order.data.protocol;

/**
 * Created by rnd on 2018/4/20.
 *
 */

public class AddShipAddreReq {
    private String shipName;

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public String getShipAddre() {
        return shipAddre;
    }

    public void setShipAddre(String shipAddre) {
        this.shipAddre = shipAddre;
    }

    public AddShipAddreReq(String shipName, String shipPhone, String shipAddre) {
        this.shipName = shipName;
        this.shipPhone = shipPhone;
        this.shipAddre = shipAddre;
    }

    private String shipPhone;
    private String shipAddre;
}
