package com.hs.base.presenter.view;

/**
 * Created by rnd on 2018/3/15.
 * View的基础类
 */

public interface BaseView {
    void showLoading();
    void hideLoading();
    void onError(String message);
}
