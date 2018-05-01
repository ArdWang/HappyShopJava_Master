package com.hs.order.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.order.data.repository.OrderRepository;
import com.hs.order.model.orders.OrderResp;
import com.hs.order.model.orders.Orders;
import com.hs.order.presenter.view.OrderListView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import java.util.List;



public class OrderListPresenter extends BasePresenter<OrderListView>{

    private OrderRepository orderRepository;

    public void getAllOrder(Integer orderStatus, LifecycleProvider<FragmentEvent> lifeProvider){
        orderRepository = new OrderRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        orderRepository.getAllOrder(orderStatus,lifeProvider).subscribe(new BaseObserver<OrderResp>(mView){
            @Override
            public void onNext(OrderResp orderResp) {
                mView.onGetOrderListResult(orderResp.getOrders());
            }
        });
    }


    public void confirmOrder(Integer orderId, LifecycleProvider<FragmentEvent> lifeProvider){

        orderRepository = new OrderRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        orderRepository.confirmOrder(orderId,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onConfirmOrderResult(aBoolean);
            }
        });
    }

    public void cancelOrder(Integer orderId, LifecycleProvider<FragmentEvent> lifeProvider){

        orderRepository = new OrderRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        orderRepository.cancelOrder(orderId,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onCancelOrderResult(aBoolean);
            }
        });
    }



}
