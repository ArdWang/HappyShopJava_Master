package com.hs.goods.data.net.protocol;



public class GetGoodsDetailReq {
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public GetGoodsDetailReq(Integer goodsId) {
        this.goodsId = goodsId;
    }

    private Integer goodsId;
}
