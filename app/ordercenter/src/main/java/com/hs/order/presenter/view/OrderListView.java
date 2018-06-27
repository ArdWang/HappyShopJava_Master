package com.hs.order.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.order.model.orders.Orders;

import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 *
 */

public interface OrderListView extends BaseView{
    //获取订单列表回调
    void onGetOrderListResult(List<Orders> result);

    void onConfirmOrderResult(Boolean result);

    void onCancelOrderResult(Boolean result);
}
