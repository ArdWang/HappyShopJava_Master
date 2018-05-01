package com.hs.user.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.user.model.User;
import com.hs.user.data.net.repository.UserRepository;
import com.hs.user.presenter.view.LoginView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rnd on 2018/4/9.
 * 用户信息 Presenter
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    //用户请求体
    private UserRepository userRepository;

    public void getUser(String phone, String pwd,String pushid, LifecycleProvider<ActivityEvent> lifeProvider) {
        userRepository = new UserRepository();
        //检查网络是否可以使用
        if (!checkNetWork()) {
            return;
        }
        mView.showLoading();
        userRepository.getUser(phone,pwd,pushid,lifeProvider).subscribe(new BaseObserver<User>(mView){
            @Override
            public void onNext(User user) {
                mView.resultLoginSuccess(user);
            }
        });
    }
}
