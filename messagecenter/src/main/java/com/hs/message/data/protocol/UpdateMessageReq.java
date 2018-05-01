package com.hs.message.data.protocol;



public class UpdateMessageReq {
    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getMsgRead() {
        return msgRead;
    }

    public void setMsgRead(Integer msgRead) {
        this.msgRead = msgRead;
    }

    private Integer msgId;

    public UpdateMessageReq(Integer msgId, Integer msgRead) {
        this.msgId = msgId;
        this.msgRead = msgRead;
    }

    private Integer msgRead;

}
