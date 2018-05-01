package com.hs.provider.router;

/**
 * Created by Administrator on 2018/4/15.
 *
 */

public class RouterPath {
    public static class UserCenter{
        public static final String PATH_LOGIN = "/usercenter/login";
    }

    public static class OrderCenter{
        public static final String PATH_ORDER_CONFIRM = "/ordercenter/confirm";
    }

    public static class PaySDK{
        public static final String PATH_PAY = "/paysdk/pay";
    }

    public static class MessageCenter{
        public static final String PATH_MESSAGE_PUSH = "/msgcenter/push";
        public static final String PATH_MESSAGE_ORDER = "/msgcenter/order";
    }
}
