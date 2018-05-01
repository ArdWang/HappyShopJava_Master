package com.hs.order.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.order.model.orders.Orders;

/**
 * Created by rnd on 2018/4/23.
 *
 */
public interface OrderDetailView extends BaseView{

    void onGetOrderDetailResult(Orders orders);
}
