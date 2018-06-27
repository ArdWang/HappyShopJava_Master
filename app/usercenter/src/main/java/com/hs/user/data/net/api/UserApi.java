package com.hs.user.data.net.api;

import com.hs.user.data.net.protocol.EditUserReq;
import com.hs.user.data.net.protocol.ForgetPwdReq;
import com.hs.user.data.net.protocol.RegUserReq;
import com.hs.user.data.net.protocol.ResetPwdReq;
import com.hs.user.model.User;
import com.hs.base.data.net.protocol.BaseResp;
import com.hs.user.data.net.protocol.GetUserReq;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by xw on 2018/4/18.
 * V1.0.0
 * 用户Api
 */

public interface UserApi {
    /**
        获取用户信息
     */
    @POST("user/getUser")
    Observable<BaseResp<User>> getUser(@Body GetUserReq req);

    /**
        用户注册
     */
    @POST("user/regUser")
    Observable<BaseResp<Boolean>> regUser(@Body RegUserReq req);

    /**
        忘记密码
     */
    @POST("user/forgetPwd")
    Observable<BaseResp<Boolean>> forgetPwd(@Body ForgetPwdReq req);

    /**
        重置密码
     */
    @POST("user/resetPwd")
    Observable<BaseResp<Boolean>> resetPwd(@Body ResetPwdReq req);

    /**
        编辑用户
     */
    @POST("user/editUser")
    Observable<BaseResp<User>> editUser(@Body EditUserReq req);


}
