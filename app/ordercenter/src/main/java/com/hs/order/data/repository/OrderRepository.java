package com.hs.order.data.repository;


import com.hs.base.data.net.http.RetrofitFactory;
import com.hs.base.data.net.repository.BaseRepository;
import com.hs.base.rx.BaseFunction;
import com.hs.base.rx.BaseFunction1;
import com.hs.base.rx.BaseFunctionBoolean;
import com.hs.order.data.api.OrderApi;
import com.hs.order.data.protocol.CancelOrderReq;
import com.hs.order.data.protocol.ConfirmOrderReq;
import com.hs.order.data.protocol.GetAllOrderReq;
import com.hs.order.data.protocol.GetOrderByIdReq;
import com.hs.order.data.protocol.SubmitOrderReq;
import com.hs.order.model.orders.OrderResp;
import com.hs.order.model.orders.Orders;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import java.util.List;
import io.reactivex.Observable;

/**
 * 订单数据层
 */
public class OrderRepository extends BaseRepository{
    public OrderApi orderApi;


    public OrderRepository(){
        orderApi = RetrofitFactory.getInstance().create(OrderApi.class);
    }

    /**
        根据订单状态获取订单
     */
    public Observable<OrderResp> getAllOrder(Integer orderStatus, LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(orderApi
                .getAllOrder(new GetAllOrderReq(orderStatus)),lifeProvider)
                .map(new BaseFunction1<OrderResp>());
    }



    /**
     * 通过ID获取订单信息
     */
    public Observable<Orders> getOrderById(Integer orderId,LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(orderApi
                .getOrderById(new GetOrderByIdReq(orderId)),lifeProvider)
                .flatMap(new BaseFunction<Orders>());
    }


    /**
        提交订单
     */
    public Observable<Boolean> submitOrder(Orders orderList, LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(orderApi
                .submitOrder(new SubmitOrderReq(orderList)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }


    /**
        确认订单
     */
    public Observable<Boolean> confirmOrder(Integer orderId, LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(orderApi
                .confirmOrder(new ConfirmOrderReq(orderId)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }


    /**
        取消订单
     */
    public Observable<Boolean> cancelOrder(Integer orderId, LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(orderApi
                .cancelOrder(new CancelOrderReq(orderId)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }






}
