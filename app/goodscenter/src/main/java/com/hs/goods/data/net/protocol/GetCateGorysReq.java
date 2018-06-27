package com.hs.goods.data.net.protocol;



public class GetCateGorysReq {

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public GetCateGorysReq(Integer categoryId) {
        this.categoryId = categoryId;
    }

    private Integer categoryId;
}
