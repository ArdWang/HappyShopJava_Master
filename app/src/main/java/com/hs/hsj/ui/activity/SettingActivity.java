package com.hs.hsj.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseActivity;
import com.hs.hsj.R;
import com.hs.user.utils.UserPrefsUtils;

/**
 * Created by rnd on 2018/4/13.
 *
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private Button mLogoutBtn;
    private TextView mAboutTv;
    private TextView mFeedBackTv;
    private TextView mUserProtocolTv;

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

        mLogoutBtn.setOnClickListener(this);
        mAboutTv.setOnClickListener(this);
        mFeedBackTv.setOnClickListener(this);
        mUserProtocolTv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mUserProtocolTv:
                CommonExt.toast("用户协议");
                break;

            case R.id.mFeedBackTv:
                CommonExt.toast("反馈意见");
                break;

            case R.id.mAboutTv:
                CommonExt.toast("关于");
                break;

            case R.id.mLogoutBtn:
                UserPrefsUtils.putClearUserInfo();
                finish();
                break;
        }
    }
}
