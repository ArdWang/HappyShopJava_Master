package com.hs.message.data.protocol;



public class DeleteMessageReq {
    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public DeleteMessageReq(Integer msgId) {
        this.msgId = msgId;
    }

    private Integer msgId;
}
