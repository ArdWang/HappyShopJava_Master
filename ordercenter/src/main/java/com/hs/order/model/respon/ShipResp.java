package com.hs.order.model.respon;

/**
 * Created by rnd on 2018/4/20.
 *
 */

public class ShipResp {
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

    private String code;
    private String message;
}
