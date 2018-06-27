package com.hs.goods.data.net.protocol;

/**
 * Created by rnd on 2018/4/18.
 * V1.0.0
 * 添加商品协议
 */
public class AddCartReq {
    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsdesc() {
        return goodsdesc;
    }

    public void setGoodsdesc(String goodsdesc) {
        this.goodsdesc = goodsdesc;
    }

    public String getGoodsicon() {
        return goodsicon;
    }

    public void setGoodsicon(String goodsicon) {
        this.goodsicon = goodsicon;
    }



    public Integer getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(Integer goodscount) {
        this.goodscount = goodscount;
    }

    public String getGoodssku() {
        return goodssku;
    }

    public void setGoodssku(String goodssku) {
        this.goodssku = goodssku;
    }

    private Integer goodsid;
    private String goodsdesc;

    public AddCartReq(Integer goodsid, String goodsdesc, String goodsicon, Float goodsprice, Integer goodscount, String goodssku) {
        this.goodsid = goodsid;
        this.goodsdesc = goodsdesc;
        this.goodsicon = goodsicon;
        this.goodsprice = goodsprice;
        this.goodscount = goodscount;
        this.goodssku = goodssku;
    }

    private String goodsicon;

    public Float getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Float goodsprice) {
        this.goodsprice = goodsprice;
    }

    private Float goodsprice;
    private Integer goodscount;
    private String goodssku;
}
