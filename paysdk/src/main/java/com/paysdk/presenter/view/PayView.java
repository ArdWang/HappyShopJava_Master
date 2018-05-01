package com.paysdk.presenter.view;


import com.hs.base.presenter.view.BaseView;

public interface PayView extends BaseView{
    //获取支付签名
    void onGetSignResult(String result);
    //获取支付签名
    void onPayOrderResult(Boolean result);
}
