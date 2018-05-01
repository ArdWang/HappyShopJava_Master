package com.hs.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.presenter.view.BaseView;
import com.hs.base.utils.PMUtils;


/**
 * Created by rnd on 2018/3/15.
 * fragment子类必须要spuer才可以继承他
 *
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView,
        View.OnClickListener{

    public P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    protected void init(){
        mPresenter = PMUtils.getT(this,0);
        if(this instanceof BaseView){
            mPresenter.setMV(this);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {

    }
}
