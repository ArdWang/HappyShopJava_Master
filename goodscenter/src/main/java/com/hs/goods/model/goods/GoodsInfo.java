package com.hs.goods.model.goods;

import java.util.List;

public class GoodsInfo {
    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getCategorypid() {
        return categorypid;
    }

    public void setCategorypid(Integer categorypid) {
        this.categorypid = categorypid;
    }

    public Integer getCategorysid() {
        return categorysid;
    }

    public void setCategorysid(Integer categorysid) {
        this.categorysid = categorysid;
    }

    public String getGoodsdesc() {
        return goodsdesc;
    }

    public void setGoodsdesc(String goodsdesc) {
        this.goodsdesc = goodsdesc;
    }

    public String getGoodsdefaulticon() {
        return goodsdefaulticon;
    }

    public void setGoodsdefaulticon(String goodsdefaulticon) {
        this.goodsdefaulticon = goodsdefaulticon;
    }


    public String getGoodsdetailone() {
        return goodsdetailone;
    }

    public void setGoodsdetailone(String goodsdetailone) {
        this.goodsdetailone = goodsdetailone;
    }

    public String getGoodsdetailtwo() {
        return goodsdetailtwo;
    }

    public void setGoodsdetailtwo(String goodsdetailtwo) {
        this.goodsdetailtwo = goodsdetailtwo;
    }

    public Integer getGoodssalescount() {
        return goodssalescount;
    }

    public void setGoodssalescount(Integer goodssalescount) {
        this.goodssalescount = goodssalescount;
    }

    public Integer getGoodsstockcount() {
        return goodsstockcount;
    }

    public void setGoodsstockcount(Integer goodsstockcount) {
        this.goodsstockcount = goodsstockcount;
    }

    public String getGoodscode() {
        return goodscode;
    }

    public void setGoodscode(String goodscode) {
        this.goodscode = goodscode;
    }

    public String getGoodsdefaultsku() {
        return goodsdefaultsku;
    }

    public void setGoodsdefaultsku(String goodsdefaultsku) {
        this.goodsdefaultsku = goodsdefaultsku;
    }

    public Integer getMaxpage() {
        return maxpage;
    }

    public void setMaxpage(Integer maxpage) {
        this.maxpage = maxpage;
    }

    public String getGoodsbanner() {
        return goodsbanner;
    }

    public void setGoodsbanner(String goodsbanner) {
        this.goodsbanner = goodsbanner;
    }

    public List<GoodsSku> getGoodsSkus() {
        return goodsSkus;
    }

    public void setGoodsSkus(List<GoodsSku> goodsSkus) {
        this.goodsSkus = goodsSkus;
    }

    private Integer goodsid;
    private Integer categorypid;
    private Integer categorysid;
    private String goodsdesc;
    private String goodsdefaulticon;


    public Float getGoodsdefaultprice() {
        return goodsdefaultprice;
    }

    public void setGoodsdefaultprice(Float goodsdefaultprice) {
        this.goodsdefaultprice = goodsdefaultprice;
    }

    private Float goodsdefaultprice;
    private String goodsdetailone;
    private String goodsdetailtwo;
    private Integer goodssalescount;
    private Integer goodsstockcount;
    private String goodscode;
    private String goodsdefaultsku;
    private Integer maxpage;
    private String goodsbanner;
    private List<GoodsSku> goodsSkus;

}
