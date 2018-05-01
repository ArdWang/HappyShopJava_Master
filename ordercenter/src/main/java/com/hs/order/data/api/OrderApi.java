package com.hs.order.data.api;

import com.hs.base.data.net.protocol.BaseResp;
import com.hs.order.data.protocol.CancelOrderReq;
import com.hs.order.data.protocol.ConfirmOrderReq;
import com.hs.order.data.protocol.GetAllOrderReq;
import com.hs.order.data.protocol.GetOrderByIdReq;
import com.hs.order.data.protocol.SubmitOrderReq;
import com.hs.order.model.orders.OrderResp;
import com.hs.order.model.orders.Orders;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * 订单的Api
 */
public interface OrderApi {

    /**
     * 根据订单状态获取所有的订单
     */
    @POST("order/getOrderList")
    Observable<OrderResp> getAllOrder(@Body GetAllOrderReq req);


    /**
     * 根据订单Id查询订单
     */
    @POST("order/getOrderById")
    Observable<BaseResp<Orders>> getOrderById(@Body GetOrderByIdReq req);


    /**
        提交订单
     */
    @POST("order/submitOrder")
    Observable<BaseResp<Boolean>> submitOrder(@Body SubmitOrderReq req);


    /**
        确认订单
     */
    @POST("order/confirmOrder")
    Observable<BaseResp<Boolean>> confirmOrder(@Body ConfirmOrderReq req);


    /**
        取消订单
     */
    @POST("order/cancelOrder")
    Observable<BaseResp<Boolean>> cancelOrder(@Body CancelOrderReq req);

}
