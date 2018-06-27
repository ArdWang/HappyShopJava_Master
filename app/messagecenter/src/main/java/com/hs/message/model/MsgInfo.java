package com.hs.message.model;


import java.util.Date;

public class MsgInfo {
    private Integer msgid;
    private Integer userid;

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Integer msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsgtitle() {
        return msgtitle;
    }

    public void setMsgtitle(String msgtitle) {
        this.msgtitle = msgtitle;
    }

    public String getMsgcon() {
        return msgcon;
    }

    public void setMsgcon(String msgcon) {
        this.msgcon = msgcon;
    }

    public String getMsgimg() {
        return msgimg;
    }

    public void setMsgimg(String msgimg) {
        this.msgimg = msgimg;
    }

    public Long getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(Long msgtime) {
        this.msgtime = msgtime;
    }

    public Integer getMsgread() {
        return msgread;
    }

    public void setMsgread(Integer msgread) {
        this.msgread = msgread;
    }

    private Integer msgtype;
    private String msgtitle;
    private String msgcon;
    private String msgimg;
    private Long msgtime;
    private Integer msgread;
}
