package com.hs.goods.data.net.api;


import com.hs.base.data.net.protocol.BaseResp;
import com.hs.goods.data.net.protocol.GetGoodsDetailReq;
import com.hs.goods.data.net.protocol.GetGoodsInfoReq;
import com.hs.goods.data.net.protocol.GetGoodsKeyWordReq;
import com.hs.goods.model.goods.GoodsInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GoodsApi {

    @POST("goods/getGoodsInfo")
    Observable<BaseResp<List<GoodsInfo>>> getGoodsInfo(@Body GetGoodsInfoReq req);


    @POST("goods/getGoodsKeyWord")
    Observable<BaseResp<List<GoodsInfo>>> getGoodsKeyWord(@Body GetGoodsKeyWordReq req);


    @POST("goods/getGoodsDetail")
    Observable<BaseResp<GoodsInfo>> getGoodsDetail(@Body GetGoodsDetailReq req);

}
