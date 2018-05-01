package com.hs.goods.model.category;

import java.util.List;


public class CateGoryp {
    private Integer categorypid;

    public Integer getCategorypid() {
        return categorypid;
    }

    public void setCategorypid(Integer categorypid) {
        this.categorypid = categorypid;
    }

    public String getCategorypname() {
        return categorypname;
    }

    public void setCategorypname(String categorypname) {
        this.categorypname = categorypname;
    }

    public String getCategorypicon() {
        return categorypicon;
    }

    public void setCategorypicon(String categorypicon) {
        this.categorypicon = categorypicon;
    }

    public String getCategorypnote() {
        return categorypnote;
    }

    public void setCategorypnote(String categorypnote) {
        this.categorypnote = categorypnote;
    }

    public List<CateGorys> getGorys() {
        return gorys;
    }

    public void setGorys(List<CateGorys> gorys) {
        this.gorys = gorys;
    }

    private String categorypname;
    private String categorypicon;
    private String categorypnote;
    private List<CateGorys> gorys;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected;
}
