package com.hs.user.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.user.data.net.repository.UploadRepository;
import com.hs.user.model.User;
import com.hs.user.data.net.repository.UserRepository;
import com.hs.user.presenter.view.UserInfoView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;


/**
 * Created by rnd on 2018/4/10.
 * 用户信息
 */
public class UserInfoPresenter extends BasePresenter<UserInfoView>{

    //用户请求体
    private UserRepository userRepository;

    private UploadRepository uploadRepository;


    /**
     * 获取Token
     */
    public void uploadToken(LifecycleProvider<ActivityEvent> lifeProvider){
        uploadRepository = new UploadRepository();

        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        uploadRepository.uploadData(lifeProvider).subscribe(new BaseObserver<String>(mView){
            @Override
            public void onNext(String s) {
                mView.onGetUploadTokenResult(s);
            }
        });
    }

    /**
     * 编辑用户信息
     */
    public void editUser(int userid,String username,String userimg,int sex,String sign,
                         LifecycleProvider<ActivityEvent> lifeProvider) {

        userRepository = new UserRepository();

        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        userRepository.editUser(userid, username, userimg, sex, sign, lifeProvider).subscribe(new BaseObserver<User>(mView) {
            @Override
            public void onNext(User user) {
                mView.onEditUserResult(user);
            }
        });
    }
}
