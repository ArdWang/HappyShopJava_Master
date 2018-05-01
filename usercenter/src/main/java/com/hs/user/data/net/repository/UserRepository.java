package com.hs.user.data.net.repository;

import com.hs.base.data.net.http.RetrofitFactory;
import com.hs.base.data.net.repository.BaseRepository;
import com.hs.base.rx.BaseFunction;
import com.hs.base.rx.BaseFunctionBoolean;
import com.hs.user.data.net.api.UserApi;
import com.hs.user.data.net.protocol.EditUserReq;
import com.hs.user.data.net.protocol.ForgetPwdReq;
import com.hs.user.data.net.protocol.GetUserReq;
import com.hs.user.data.net.protocol.RegUserReq;
import com.hs.user.data.net.protocol.ResetPwdReq;
import com.hs.user.model.User;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.Observable;


/**
 * Created by rnd on 2018/4/18.
 * 用户的 处理体
 */

public class UserRepository extends BaseRepository{
    private UserApi userApi;

    public UserRepository(){
        userApi = RetrofitFactory.getInstance().create(UserApi.class);
    }

    /**
     * 用户登录
     */
    public Observable<User> getUser(String phone, String password, String pushid, LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(userApi.getUser(new GetUserReq(phone,password,pushid)),lifeProvider)
                .flatMap(new BaseFunction<User>());
    }

    /**
       用户注册
     */
    public Observable<Boolean> regUser(String mobile, String verifyCode, String password
            , LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(userApi.regUser(new RegUserReq(mobile,verifyCode,password)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }

    /**
        忘记密码
     */
    public Observable<Boolean> forgetPwd(String mobile, String verifyCode,LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(userApi.forgetPwd(new ForgetPwdReq(mobile,verifyCode)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }

    /**
        重置密码
     */
    public Observable<Boolean> resetPwd(String mobile, String password,LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(userApi.resetPwd(new ResetPwdReq(mobile,password)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }


    /**
        编辑用户资料
     */
    public Observable<User> editUser(Integer userid, String username, String userimg, Integer sex, String sign
            ,LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(userApi.editUser(new EditUserReq(userid,username,userimg,sex,sign)),lifeProvider)
                .flatMap(new BaseFunction<User>());
    }




}
