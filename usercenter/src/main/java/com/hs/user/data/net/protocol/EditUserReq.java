package com.hs.user.data.net.protocol;



public class EditUserReq {
    //int userid,String username,String userimg,int sex,String sign,
    private Integer userid;
    private String username;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    private String userimg;

    public EditUserReq(Integer userid, String username, String userimg, Integer sex, String sign) {
        this.userid = userid;
        this.username = username;
        this.userimg = userimg;
        this.sex = sex;
        this.sign = sign;
    }

    private Integer sex;
    private String sign;
}
