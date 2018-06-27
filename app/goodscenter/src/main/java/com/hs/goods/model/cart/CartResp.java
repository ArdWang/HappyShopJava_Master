package com.hs.goods.model.cart;


import java.util.List;

public class CartResp {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CartGoods> getCartGoods() {
        return cartGoods;
    }

    public void setCartGoods(List<CartGoods> cartGoods) {
        this.cartGoods = cartGoods;
    }

    private String message;
    private List<CartGoods> cartGoods;
}
