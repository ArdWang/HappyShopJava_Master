package com.hs.user.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseMvpActivity;
import com.hs.base.widgets.VerifyButton;
import com.hs.user.R;
import com.hs.user.presenter.RegisterPresenter;
import com.hs.user.presenter.view.RegisterView;

/**
 * Created by rnd on 2018/4/8.
 * 登录的Activity界面
 */
public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterView {

    private EditText mMobileEt;
    private EditText mVerifyCodeEt;
    private EditText mPwdEt;
    private EditText mPwdConfirmEt;
    private Button mRegisterBtn;
    private VerifyButton mVerifyCodeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mMobileEt = findViewById(R.id.mMobileEt);
        mVerifyCodeEt = findViewById(R.id.mVerifyCodeEt);
        mPwdEt = findViewById(R.id.mPwdEt);
        mPwdConfirmEt = findViewById(R.id.mPwdConfirmEt);
        mVerifyCodeBtn = findViewById(R.id.mVerifyCodeBtn);
        mRegisterBtn = findViewById(R.id.mRegisterBtn);

        mVerifyCodeBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);

        //按钮是否可以使用
        buttonEnable(mMobileEt, mRegisterBtn);
        buttonEnable(mPwdEt, mRegisterBtn);
        buttonEnable(mVerifyCodeEt, mRegisterBtn);
        buttonEnable(mPwdConfirmEt, mRegisterBtn);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mVerifyCodeBtn) {
            mVerifyCodeBtn.requestSendVerifyNumber();
            CommonExt.toast("发送验证码成功");

        } else if (i == R.id.mRegisterBtn) {
            if (!mPwdEt.getText().toString().equals(mPwdConfirmEt.getText().toString())) {
                CommonExt.toast("两次密码输入的不一致");
                return;
            }

            mPresenter.register(mMobileEt.getText().toString(),mVerifyCodeEt.getText().toString(),mPwdEt.getText().toString(),
                     this);

        }

    }

    /**
     * 按钮是否可以点击
     * @return
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
                !mPwdEt.getText().toString().isEmpty()&&!mPwdConfirmEt.getText().toString().isEmpty()
                &&!mVerifyCodeEt.getText().toString().isEmpty();
    }


    @Override
    public void resultRegisterSuccess(Boolean result) {
        CommonExt.toast("注册成功");
        finish();
    }
}

