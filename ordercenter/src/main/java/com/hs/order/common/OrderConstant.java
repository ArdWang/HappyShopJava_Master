package com.hs.order.common;

/**
 * Created by rnd on 2018/4/20.
 *
 */

public class OrderConstant {
    //订单状态
    public static final String KEY_ORDER_STATUS = "order_status";
    //收货地址
    public static final String KEY_SHIP_ADDRESS = "ship_address";
    //选择收货地址请求码
    public static final int REQ_SELECT_ADDRESS = 1001;
    //是否选择收货地址
    public static final String KEY_IS_SELECT_ADDRESS = "is_select_address";
    //是否编辑地址
    public static final String KEY_ADDRESS_IS_EDIT = "address_is_edit";
    //支付订单操作
    public static final int OPT_ORDER_PAY = 1;
    //确认订单操作
    public static final int OPT_ORDER_CONFIRM = 2;
    //取消订单操作
    public static final int OPT_ORDER_CANCEL = 3;
}
