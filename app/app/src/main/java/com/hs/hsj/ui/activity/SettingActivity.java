package com.hs.hsj.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseActivity;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.hsj.R;
import com.hs.provider.common.ProviderConstant;
import com.hs.user.utils.UserPrefsUtils;

/**
 * Created by rnd on 2018/4/13.
 *
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private Button mLogoutBtn;
    private TextView mAboutTv;
    private TextView mFeedBackTv;
    private TextView mClearDataTv;
    private TextView mUserProtocolTv;
    private String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        mLogoutBtn = findViewById(R.id.mLogoutBtn);
        mAboutTv = findViewById(R.id.mAboutTv);
        mFeedBackTv = findViewById(R.id.mFeedBackTv);
        mUserProtocolTv = findViewById(R.id.mUserProtocolTv);
        mClearDataTv = findViewById(R.id.mClearDataTv);

        mLogoutBtn.setOnClickListener(this);
        mAboutTv.setOnClickListener(this);
        mFeedBackTv.setOnClickListener(this);
        mUserProtocolTv.setOnClickListener(this);
        mClearDataTv.setOnClickListener(this);

        userName = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_NAME);
        if(userName==null){
            userName = "Demo";
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SettingActivity.this,SettingAllActivity.class);
        switch (v.getId()){
            case R.id.mUserProtocolTv:
                CommonExt.toast("用户协议");
                break;

            case R.id.mFeedBackTv:
                intent.putExtra("value",0x02);
                intent.putExtra(ProviderConstant.KEY_SP_USER_NAME,userName);
                intent.putExtra("titleName","反馈消息");
                break;

            case R.id.mClearDataTv:
                intent.putExtra("value",0x03);
                intent.putExtra("titleName","清理缓存");
                break;

            case R.id.mAboutTv:
                CommonExt.toast("关于");
                intent.putExtra("value",0x04);
                intent.putExtra("titleName","关于");
                break;

            case R.id.mLogoutBtn:
                intent.putExtra("value",0x05);
                intent.putExtra("titleName","退出登录");
                UserPrefsUtils.putClearUserInfo();
                finish();
                break;
        }
        startActivity(intent);
    }
}
