package com.hs.order.data.protocol;

import com.hs.order.model.ShipAddre;

/**
 * Created by Administrator on 2018/4/21.
 *
 */

public class EditShipAddreReq {
    public ShipAddre getShipAddre() {
        return shipAddre;
    }

    public void setShipAddre(ShipAddre shipAddre) {
        this.shipAddre = shipAddre;
    }

    public EditShipAddreReq(ShipAddre shipAddre) {
        this.shipAddre = shipAddre;
    }

    private ShipAddre shipAddre;
}
