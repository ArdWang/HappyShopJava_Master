package com.hs.user.data.net.protocol;

/**
 * Created by rnd on 2018/4/18.
 * 登录用户接口协议
 */

public class GetUserReq {

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GetUserReq(String phone, String password,String pushid) {
        this.phone = phone;
        this.password = password;
        this.pushid = pushid;
    }

    private String phone;
    private String password;

    public String getPushid() {
        return pushid;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid;
    }

    private String pushid;
}
