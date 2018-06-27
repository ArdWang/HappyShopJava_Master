package com.hs.base.rx;

import com.hs.base.presenter.view.BaseView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by rnd on 2018/4/11.
 * Version 1.0.0
 * 实现一个BaseSubscriber 处理其他的网络参数时候共用
 * 1.可以集合处理网络错误
 * 2.获取网络数据完成后关闭 加载框
 * 3.同时要获取到BaseView 构造一个BaseSubscriber方法存放BaseView
 */
public class BaseObserver<T> implements Observer<T> {

    private BaseView baseView;

    public BaseObserver(BaseView v){
        baseView = v;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        baseView.hideLoading();

        if(e instanceof BaseException){
            baseView.onError(e.getMessage());
        }else{
            //服务器网络错误处理块
            baseView.onError(BasesException.handleException(e).message);
        }
    }

    @Override
    public void onComplete() {
        baseView.hideLoading();
    }
}
