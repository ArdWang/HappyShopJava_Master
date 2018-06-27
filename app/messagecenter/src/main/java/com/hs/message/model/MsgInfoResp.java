package com.hs.message.model;

import java.util.List;

public class MsgInfoResp {
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

    public List<MsgInfo> getMsgInfos() {
        return msgInfos;
    }

    public void setMsgInfos(List<MsgInfo> msgInfos) {
        this.msgInfos = msgInfos;
    }

    private String message;
    private List<MsgInfo> msgInfos;
}
