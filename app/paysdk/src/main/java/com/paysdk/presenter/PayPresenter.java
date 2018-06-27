package com.paysdk.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.paysdk.data.repository.PayRepository;
import com.paysdk.presenter.view.PayView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

public class PayPresenter extends BasePresenter<PayView>{

    private PayRepository payRepository;

    public void getPaySign(Integer orderId, Float totalPrice, LifecycleProvider<ActivityEvent> lifeProvider){

        payRepository = new PayRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        payRepository.getPaySign(orderId,totalPrice,lifeProvider).subscribe(new BaseObserver<String>(mView){
            @Override
            public void onNext(String s) {
                mView.onGetSignResult(s);
            }
        });
    }

    public void payOrder(Integer orderId,LifecycleProvider<ActivityEvent> lifeProvider){
        payRepository = new PayRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        payRepository.payOrder(orderId,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onPayOrderResult(aBoolean);
            }
        });

    }
}
