package com.hs.order.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.order.data.repository.OrderRepository;
import com.hs.order.model.Order;
import com.hs.order.model.orders.Orders;
import com.hs.order.presenter.view.OrderDetailView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;


public class OrderDetailPresenter extends BasePresenter<OrderDetailView>{


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
                mView.onGetOrderDetailResult(orders);
            }
        });
    }
}
