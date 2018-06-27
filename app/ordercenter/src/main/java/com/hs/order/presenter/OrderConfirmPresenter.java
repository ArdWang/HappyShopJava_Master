package com.hs.order.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.order.data.repository.OrderRepository;
import com.hs.order.model.Order;
import com.hs.order.model.orders.Orders;
import com.hs.order.model.respon.OrderResp;
import com.hs.order.presenter.view.OrderConfirmView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;


/**
 * 订单确认页的 Presenter
 */
public class OrderConfirmPresenter extends BasePresenter<OrderConfirmView>{

    private OrderRepository orderRepository;

    /*
        根据订单id获取
     */
    public void getOrderById(Integer orderId,LifecycleProvider<ActivityEvent> lifeProvider){

        orderRepository = new OrderRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        orderRepository.getOrderById(orderId,lifeProvider).subscribe(new BaseObserver<Orders>(mView){
            @Override
            public void onNext(Orders orders) {
                mView.onGetOrderByIdResult(orders);
            }
        });
    }

    public void submitOrder(Orders orderList, LifecycleProvider<ActivityEvent> lifeProvider){
        orderRepository = new OrderRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        orderRepository.submitOrder(orderList,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onSubmitOrderResult(aBoolean);
            }
        });
    }
}
