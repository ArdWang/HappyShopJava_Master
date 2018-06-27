package com.paysdk.model;

/**
 * Created by rnd on 2018/4/24.
 *
 */

public class PayObject {
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

    public String getSiginCode() {
        return siginCode;
    }

    public void setSiginCode(String siginCode) {
        this.siginCode = siginCode;
    }

    private String message;
    private String siginCode;
}
