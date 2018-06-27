package com.hs.goods.model.goods;


import java.util.List;

public class GoodsSku{
    private Integer goodsskuid;

    public Integer getGoodsskuid() {
        return goodsskuid;
    }

    public void setGoodsskuid(Integer goodsskuid) {
        this.goodsskuid = goodsskuid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsskutitle() {
        return goodsskutitle;
    }

    public void setGoodsskutitle(String goodsskutitle) {
        this.goodsskutitle = goodsskutitle;
    }

    private Integer goodsid;
    private String goodsskutitle;

    public List<String> getGoodsskucon() {
        return goodsskucon;
    }

    public void setGoodsskucon(List<String> goodsskucon) {
        this.goodsskucon = goodsskucon;
    }

    private List<String> goodsskucon;
}
