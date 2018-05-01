package com.hs.goods.model.category;


public class CateGorys {

    public Integer getCategorysid() {
        return categorysid;
    }

    public void setCategorysid(Integer categorysid) {
        this.categorysid = categorysid;
    }

    public Integer getCategorypid() {
        return categorypid;
    }

    public void setCategorypid(Integer categorypid) {
        this.categorypid = categorypid;
    }

    public String getCategorysname() {
        return categorysname;
    }

    public void setCategorysname(String categorysname) {
        this.categorysname = categorysname;
    }

    public String getCategorysicon() {
        return categorysicon;
    }

    public void setCategorysicon(String categorysicon) {
        this.categorysicon = categorysicon;
    }

    public String getCategorysnote() {
        return categorysnote;
    }

    public void setCategorysnote(String categorysnote) {
        this.categorysnote = categorysnote;
    }

    private Integer categorysid;
    private Integer categorypid;
    private String categorysname;
    private String categorysicon;
    private String categorysnote;
}
