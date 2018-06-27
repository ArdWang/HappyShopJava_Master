package com.hs.goods.data.net.protocol;


import java.util.List;

/**
 * V1.0.0
 * 删除购物车的协议
 */

public class DeleteCartReq {
    public List<Integer> getCartIdList() {
        return cartIdList;
    }

    public void setCartIdList(List<Integer> cartIdList) {
        this.cartIdList = cartIdList;
    }

    List<Integer> cartIdList;

    public DeleteCartReq(List<Integer> cartIdList){
        this.cartIdList = cartIdList;
    }


}
