package com.hs.order.common;


/*
    订单状态
 */
public class OrderStatus {
     public static final int ORDER_ALL = 0; //全部
     public static final int ORDER_WAIT_PAY = 1;//待支付
     public static final int ORDER_WAIT_CONFIRM = 2;//待收货
     public static final int ORDER_COMPLETED = 3;//已完成
     public static final int ORDER_CANCELED = 4;//已取消
}
