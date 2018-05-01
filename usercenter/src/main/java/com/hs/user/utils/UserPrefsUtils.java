package com.hs.user.utils;

import com.hs.base.common.BaseConstant;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.provider.common.ProviderConstant;
import com.hs.user.model.User;


/**
 * Created by rnd on 2018/4/11.
 * 存储本地用户信息
 */
public class UserPrefsUtils {

    public static void putUserInfo(User user){
        AppPrefsUtils.getInstance().putString(BaseConstant.KEY_SP_TOKEN,user.getUserid()+"");
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_ID,user.getUserid()+"");
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_NAME, user.getUsername());
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_ICON, user.getUserimg());
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_MOBILE,user.getPhone());
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_GENDER,user.getSex()+"");
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_SIGN,user.getSign());
    }

    /**
     * 清空用户消息
     */
    public static void putClearUserInfo(){
        AppPrefsUtils.getInstance().putString(BaseConstant.KEY_SP_TOKEN,"");
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_ID,"");
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_NAME, "");
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_ICON, "");
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_MOBILE,"");
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_GENDER,"");
        AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_SIGN,"");
    }


}
