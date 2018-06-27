package com.hs.user.data.net.protocol;



public class RegUserReq {
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String mobile;

    public RegUserReq(String mobile, String verifyCode, String password) {
        this.mobile = mobile;
        this.verifyCode = verifyCode;
        this.password = password;
    }

    private String verifyCode;
    private String password;
}
