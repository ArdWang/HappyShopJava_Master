package com.hs.user.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.user.model.User;



public interface LoginView extends BaseView{
    void resultLoginSuccess(User result);
}
