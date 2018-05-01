package com.hs.base.common;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by rnd on 2018/4/8.
 *
 */

public class BaseApplication extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

    }

    //获取全局变量的Context
    public static Context getContext(){
        return context;
    }

}
