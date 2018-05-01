package com.hs.goods.data.net.protocol;


import com.hs.goods.model.cart.CartGoods;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 * 提交商品总价格 和 商品详细列表
 */

public class SubmitCartReq {

    public List<CartGoods> getCartGoods() {
        return cartGoods;
    }

    public void setCartGoods(List<CartGoods> cartGoods) {
        this.cartGoods = cartGoods;
    }


    public SubmitCartReq(List<CartGoods> cartGoods, Float totalPrice) {
        this.cartGoods = cartGoods;
        this.totalPrice = totalPrice;
    }

    private List<CartGoods> cartGoods;


    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    private Float totalPrice;

}
