package com.hs.user.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseMvpActivity;
import com.hs.provider.PushProvider;
import com.hs.provider.router.RouterPath;
import com.hs.user.R;
import com.hs.user.model.User;
import com.hs.user.presenter.LoginPresenter;
import com.hs.user.presenter.view.LoginView;
import com.hs.user.utils.UserPrefsUtils;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by rnd on 2018/4/8.
 * 登录的Activity界面
 */

@Route(path = RouterPath.UserCenter.PATH_LOGIN)
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {
    private EditText mMobileEt;
    private EditText mPwdEt;
    private Button mLoginBtn;
    private TextView mRightTv;
    private TextView mForgetPwdTv;

    //@Autowired(name=RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
    //PushProvider mPushProvider;

    private String pushId="ab123";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mMobileEt = findViewById(R.id.mMobileEt);
        mPwdEt = findViewById(R.id.mPwdEt);
        mLoginBtn = findViewById(R.id.mLoginBtn);
        mRightTv = findViewById(R.id.mRightTv);
        mForgetPwdTv = findViewById(R.id.mForgetPwdTv);

        mLoginBtn.setOnClickListener(this);
        mRightTv.setOnClickListener(this);
        mForgetPwdTv.setOnClickListener(this);

        //按钮是否可以使用
        buttonEnable(mMobileEt,mLoginBtn);
        buttonEnable(mPwdEt,mLoginBtn);

        pushId = JPushInterface.getRegistrationID(this);

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        Intent intent;
        if(i==R.id.mLoginBtn){
            //String pushid="";
            /*if(mPushProvider!=null){
                 pushid = mPushProvider.getPushId();
            }*/
            mPresenter.getUser(mMobileEt.getText().toString(),mPwdEt.getText().toString(),pushId,this);
        }
        else if(i==R.id.mForgetPwdTv){
            intent = new Intent(LoginActivity.this,ForgetPwdActivity.class);
            startActivity(intent);
        }

        else if(i==R.id.mRightTv){
            intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 按钮是否可以点击
     */
    private void buttonEnable(EditText et, final Button btn){
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isBtnEnable()) {
                    btn.setEnabled(true);
                }else{
                    btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
        判断按钮是否可用
     */
    private Boolean isBtnEnable(){
        return !mMobileEt.getText().toString().isEmpty() &&
                !mPwdEt.getText().toString().isEmpty();
    }


    @Override
    public void resultLoginSuccess(User result) {
        //存储
        CommonExt.toast("登录成功");
        //存储起来
        UserPrefsUtils.putUserInfo(result);
        finish();
    }
}

