package com.hs.message.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hs.provider.common.ProviderConstant;
import com.hs.provider.event.MessageBadgeEvent;
import com.hs.provider.router.RouterPath;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import cn.jpush.android.api.JPushInterface;

public class MessageReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + bundle);

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            EventBus.getDefault().post(new MessageBadgeEvent(true));
            Log.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            try {
                Log.d(TAG, "用户点击打开了通知");
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                JSONObject json = new JSONObject(extra);
                int orderId = json.getInt("orderId");
                ARouter.getInstance().build(RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
                        .withInt(ProviderConstant.KEY_ORDER_ID, orderId)
                        .navigation();
                EventBus.getDefault().post(new MessageBadgeEvent(true));
            }catch (Exception e){
                e.printStackTrace();
            }

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}
