package com.hs.goods.data.net.api;

import com.hs.base.data.net.protocol.BaseResp;
import com.hs.goods.data.net.protocol.AddCartReq;
import com.hs.goods.data.net.protocol.DeleteCartReq;
import com.hs.goods.data.net.protocol.GetCartReq;
import com.hs.goods.data.net.protocol.SubmitCartReq;
import com.hs.goods.model.cart.CartResp;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by rnd on 2018/4/18.
 * V1.0.0
 * 购物车的Api
 */
public interface CartApi {
    /**
     * 获取购物车的数据
     */
    @POST("cart/getCart")
    Observable<CartResp> getCart(@Body GetCartReq req);

    /**
     * 添加商品到购物车
     */
    @POST("cart/addCart")
    Observable<BaseResp<Integer>> addCart(@Body AddCartReq req);

    /**
     *  删除购物车商品
     */
    @POST("cart/deleteCart")
    Observable<BaseResp<Boolean>> deleteCart(@Body DeleteCartReq req);

    /**
     * 提交购物车商品
     */
    @POST("cart/submitCart")
    Observable<BaseResp<Integer>> submitCart(@Body SubmitCartReq req);


}
