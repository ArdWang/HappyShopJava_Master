package com.hs.base.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hs.base.ext.CommonExt;
import com.hs.base.presenter.BasePresenter;
import com.hs.base.presenter.view.BaseView;
import com.hs.base.utils.PMUtils;
import com.hs.base.widgets.ProgressLoading;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;


/**
 * Created by rnd on 2018/3/15.
 *
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView,
        View.OnClickListener{

    public P mPresenter;

    protected LifecycleProvider<ActivityEvent> lifecycleProvider;

    private ProgressLoading progressLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init(){
        setContentView(getLayoutResID());
        initView();
        mPresenter = PMUtils.getT(this,0);

        if(this instanceof BaseView){
            mPresenter.setMV(this);
        }

        progressLoading = ProgressLoading.create(this);

        ARouter.openLog(); // 开启日志
        ARouter.openDebug(); // 使用InstantRun的时候，需要打开该开关，上线之后关闭，否则有安全风险
        //ARouter注册
        ARouter.getInstance().inject(this);


    }

    protected abstract int getLayoutResID();


    protected void initView(){

    }

    @Override
    public void showLoading() {
        progressLoading.showLoading();
    }

    @Override
    public void hideLoading() {
        progressLoading.hideLoading();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onError(String message) {
        CommonExt.toast(message);
    }
}
