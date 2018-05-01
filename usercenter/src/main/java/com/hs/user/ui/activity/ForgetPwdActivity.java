package com.hs.user.ui.activity;

import android.content.Intent;
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
import com.hs.user.presenter.ForgetPwdPresenter;
import com.hs.user.presenter.view.ForgetPwdView;


/**
 * Created by rnd on 2018/4/8.
 * 登录的Activity界面
 */
public class ForgetPwdActivity extends BaseMvpActivity<ForgetPwdPresenter> implements ForgetPwdView {

    private EditText mMobileEt;
    private EditText mVerifyCodeEt;
    private Button mNextBtn;
    private VerifyButton mVerifyCodeBtn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mMobileEt = findViewById(R.id.mMobileEt);
        mVerifyCodeEt = findViewById(R.id.mVerifyCodeEt);
        mVerifyCodeBtn = findViewById(R.id.mVerifyCodeBtn);
        mNextBtn = findViewById(R.id.mNextBtn);

        mVerifyCodeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        //按钮是否可以使用
        buttonEnable(mMobileEt, mNextBtn);
        buttonEnable(mVerifyCodeEt, mNextBtn);

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mVerifyCodeBtn) {
            mVerifyCodeBtn.requestSendVerifyNumber();
            CommonExt.toast("发送验证码成功");

        } else if (i == R.id.mNextBtn) {
            mPresenter.forgetPwd(mMobileEt.getText().toString(), mVerifyCodeEt.getText().toString(), this);

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
        return !mMobileEt.getText().toString().isEmpty()
                &&!mVerifyCodeEt.getText().toString().isEmpty();
    }


    @Override
    public void resultForgetPwdSuccess(Boolean t) {
        Intent intent = new Intent(ForgetPwdActivity.this,ResetPwdActivity.class);
        intent.putExtra("phone",mMobileEt.getText().toString());
        startActivity(intent);
    }
}

