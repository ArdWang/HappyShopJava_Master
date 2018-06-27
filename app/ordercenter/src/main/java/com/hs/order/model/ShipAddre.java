package com.hs.order.model;

import java.io.Serializable;

/**
 * Created by rnd on 2018/4/20.
 *
 */

public class ShipAddre implements Serializable {
    private int shipid;

    public int getShipid() {
        return shipid;
    }

    public void setShipid(int shipid) {
        this.shipid = shipid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getShipusername() {
        return shipusername;
    }

    public void setShipusername(String shipusername) {
        this.shipusername = shipusername;
    }

    public String getShipusermobile() {
        return shipusermobile;
    }

    public void setShipusermobile(String shipusermobile) {
        this.shipusermobile = shipusermobile;
    }

    public String getShipaddress() {
        return shipaddress;
    }

    public void setShipaddress(String shipaddress) {
        this.shipaddress = shipaddress;
    }

    public int getShipisdefault() {
        return shipisdefault;
    }

    public void setShipisdefault(int shipisdefault) {
        this.shipisdefault = shipisdefault;
    }

    private int userid;
    private String shipusername;
    private String shipusermobile;
    private String shipaddress;
    private int shipisdefault;
}
