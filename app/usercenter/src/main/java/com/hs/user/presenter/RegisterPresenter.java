package com.hs.user.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.user.data.net.repository.UserRepository;
import com.hs.user.presenter.view.RegisterView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;



/**
 * Created by rnd on 2018/4/9.
 * 注册Presenter
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {

    private UserRepository userRepository;

    public void register(String phone,String verifyCode, String pwd, LifecycleProvider<ActivityEvent> lifeProvider) {
        userRepository = new UserRepository();

        if (!checkNetWork()) {
            return;
        }

        //显示加载对话框
        mView.showLoading();
        userRepository.regUser(phone,verifyCode,pwd,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean t) {
                mView.resultRegisterSuccess(t);
            }
        });

    }
}
