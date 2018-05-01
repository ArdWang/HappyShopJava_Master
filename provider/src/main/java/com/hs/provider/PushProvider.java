package com.hs.provider;

import com.alibaba.android.arouter.facade.template.IProvider;


public interface PushProvider extends IProvider{
    String getPushId();
}
