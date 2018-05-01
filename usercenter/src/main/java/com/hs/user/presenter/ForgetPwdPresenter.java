package com.hs.user.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.user.data.net.repository.UserRepository;
import com.hs.user.presenter.view.ForgetPwdView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;



/**
 * Created by rnd on 2018/4/10.
 * 忘记密码界面
 */

public class ForgetPwdPresenter extends BasePresenter<ForgetPwdView>{

    private UserRepository userRepository;

    public void forgetPwd(String phone, String verifyCode, LifecycleProvider<ActivityEvent> lifeProvider) {
        userRepository = new UserRepository();

        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        userRepository.forgetPwd(phone,verifyCode,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean t) {
                mView.resultForgetPwdSuccess(t);
            }
        });
    }
}
