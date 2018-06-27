package com.hs.goods.data.net.protocol;


public class GetGoodsKeyWordReq {
    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    private Integer pageIndex;

    public GetGoodsKeyWordReq(Integer pageIndex, Integer pageSize, String keyWord) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.keyWord = keyWord;
    }

    private Integer pageSize;
    private String keyWord;
}
