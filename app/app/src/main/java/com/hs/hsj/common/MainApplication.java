package com.hs.hsj.common;

import com.hs.base.common.BaseApplication;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import cn.jpush.android.api.JPushInterface;
/**
 * Created by rnd on 2018/4/8.
 *
 */
public class MainApplication extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        UMConfigure.init(this,"5ae57549f29d9866eb00016f"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        //UMConfigure.setLogEnabled(true);
        //58edcfeb310c93091c000be2 5965ee00734be40b580001a0
    }

    //各个平台的配置
    {
        //微信
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //新浪微博(第三个参数为回调地址)
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com/sina2/callback");
        //QQ
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }


}
