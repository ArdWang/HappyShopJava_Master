package com.hs.provider.common;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hs.base.common.BaseConstant;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.provider.router.RouterPath;

import java.lang.reflect.Method;

/**
 * Created by rnd on 2018/4/12.
 * 顶级函数
 */

public class CommonUtils {

    //单列模式
    /*private static class SingletonHolder{
        private static final CommonUtils INSTANCE = new CommonUtils();
    }

    /**
     * 获取
     * @return
     */
    /*public static CommonUtils getInstance(){
        return CommonUtils.SingletonHolder.INSTANCE;
    }*/


    /**
     * 看是否需要登录
     * @return
     */
    public static Boolean isLogined(){
        if(!AppPrefsUtils.getInstance().getString(BaseConstant.KEY_SP_TOKEN).isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    /**
        如果已经登录，进行传入的方法处理
        如果没有登录，进入登录界面
    */
    public static void afterLogin(){
        if (!isLogined()){
            ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation();
        }else{

        }
    }
}
