package com.hs.order.model.respon;

/**
 * Created by Administrator on 2018/4/21.
 *
 */

public class OrderResp {
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

    private String message;
}
