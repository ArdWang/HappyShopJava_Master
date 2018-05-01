package com.hs.order.event;

import com.hs.order.model.ShipAddre;

/**
 * Created by rnd on 2018/4/20.
 *
 */

public class SelectAddreEvent {

    public ShipAddre getShipAddre() {
        return shipAddre;
    }

    public void setShipAddre(ShipAddre shipAddre) {
        this.shipAddre = shipAddre;
    }

    public SelectAddreEvent(ShipAddre shipAddre) {
        this.shipAddre = shipAddre;
    }

    private ShipAddre shipAddre;

}
