package com.hs.base.presenter;


import com.hs.base.common.BaseApplication;
import com.hs.base.presenter.view.BaseView;
import com.hs.base.utils.NetworkUtils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rnd on 2018/3/15.
 *
 */

public class BasePresenter<T extends BaseView>{

    protected T mView;

    public void setMV(T v){
        mView = v;
    }

    /**
        检查网络是否可用
     */
    public Boolean checkNetWork(){
        if(NetworkUtils.isNetworkAvailable(BaseApplication.getContext())){
            return true;
        }
        mView.onError("网络不可用");
        return false;
    }

}
