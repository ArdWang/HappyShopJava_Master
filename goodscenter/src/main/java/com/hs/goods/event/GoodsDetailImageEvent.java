package com.hs.goods.event;

/**
 * Created by rnd on 2018/4/16.
 *
 */

public class GoodsDetailImageEvent {

    private String imgOne;

    public String getImgOne() {
        return imgOne;
    }

    public void setImgOne(String imgOne) {
        this.imgOne = imgOne;
    }

    public String getImgTwo() {
        return imgTwo;
    }

    public void setImgTwo(String imgTwo) {
        this.imgTwo = imgTwo;
    }

    private String imgTwo;



    public GoodsDetailImageEvent(String imgOne,String imgTwo){
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
    }
}
