package com.paysdk.data.api;

import com.hs.base.data.net.protocol.BaseResp;
import com.paysdk.data.protocol.GetPaySignReq;
import com.paysdk.data.protocol.PayOrderReq;
import com.paysdk.model.OrderResp;
import com.paysdk.model.PayObject;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 支付接口
 */
public interface PayApi {

    @POST("pay/getPaySign")
    Observable<BaseResp<String>> getPaySign(@Body GetPaySignReq req);

    @POST("order/payOrder")
    Observable<BaseResp<Boolean>> payOrder(@Body PayOrderReq req);


}
