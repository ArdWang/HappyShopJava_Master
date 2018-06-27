package com.hs.user.data.net.protocol;



public class ResetPwdReq {
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResetPwdReq(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    private String mobile;
    private String password;
}
