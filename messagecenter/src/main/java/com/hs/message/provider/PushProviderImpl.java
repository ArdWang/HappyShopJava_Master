package com.hs.message.provider;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.hs.provider.PushProvider;
import com.hs.provider.router.RouterPath;
import cn.jpush.android.api.JPushInterface;


@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
public class PushProviderImpl implements PushProvider{

    private Context mContext;

    @Override
    public String getPushId() {
        return JPushInterface.getRegistrationID(mContext);
    }

    @Override
    public void init(Context context) {
        this.mContext = context;
    }
}
