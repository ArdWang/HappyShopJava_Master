package com.hs.user.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.user.model.User;


public interface UserInfoView extends BaseView {
    /**
        获取上传凭证回调
     */
    void onGetUploadTokenResult(String result);

    /**
        编辑用户资料回调
     */
    void onEditUserResult(User result);
}
