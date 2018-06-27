package com.hs.goods.event;

/**
 * Created by rnd on 2018/4/17.
 *
 */

public class CartAllCheckedEvent {

    public Boolean getAllChecked() {
        return isAllChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        isAllChecked = allChecked;
    }

    private Boolean isAllChecked;

    public CartAllCheckedEvent(Boolean isAllChecked){
        this.isAllChecked = isAllChecked;
    }
}
