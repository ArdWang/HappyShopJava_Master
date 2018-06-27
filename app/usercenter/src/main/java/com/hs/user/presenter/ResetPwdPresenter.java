package com.hs.user.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.user.data.net.repository.UserRepository;
import com.hs.user.presenter.view.ResetPwdView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;


/**
 * Created by rnd on 2018/4/10.
 * 忘记密码界面
 */

public class ResetPwdPresenter extends BasePresenter<ResetPwdView>{

    private UserRepository userRepository;

    public void resetPwd(String phone, String password, LifecycleProvider<ActivityEvent> lifeProvider) {
        userRepository = new UserRepository();
        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        userRepository.resetPwd(phone,password,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean t) {
                mView.resultResetPwdSuccess(t);
            }
        });
    }
}
