package com.hs.order.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.order.model.Order;
import com.hs.order.model.orders.Orders;

/**
 * Created by rnd on 2018/4/20.
 *
 */
public interface OrderConfirmView extends BaseView{

    void onGetOrderByIdResult(Orders result);

    void onSubmitOrderResult(Boolean result);

}
