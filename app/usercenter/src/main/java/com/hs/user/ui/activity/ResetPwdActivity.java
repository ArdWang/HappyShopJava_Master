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
import com.hs.user.R;
import com.hs.user.presenter.ResetPwdPresenter;
import com.hs.user.presenter.view.ResetPwdView;

/**
 * Created by rnd on 2018/4/8.
 * 重置密码界面
 */
public class ResetPwdActivity extends BaseMvpActivity<ResetPwdPresenter> implements ResetPwdView {

    private EditText mPwdEt;
    private EditText mPwdConfirmEt;
    private Button mConfirmBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

        mPwdEt = findViewById(R.id.mPwdEt);
        mPwdConfirmEt = findViewById(R.id.mPwdConfirmEt);
        mConfirmBtn = findViewById(R.id.mConfirmBtn);
        mConfirmBtn.setOnClickListener(this);

        //按钮是否可以使用
        buttonEnable(mPwdEt, mConfirmBtn);
        buttonEnable(mPwdConfirmEt, mConfirmBtn);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_reset_pwd;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mConfirmBtn) {
            if (!mPwdEt.getText().toString().equals(mPwdConfirmEt.getText().toString())) {
                CommonExt.toast("两次密码输入的不一致");
                return;
            }

            mPresenter.resetPwd(getIntent().getStringExtra("phone"), mPwdEt.getText().toString(), this);


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
        return !mPwdEt.getText().toString().isEmpty()&&!mPwdConfirmEt.getText().toString().isEmpty();
    }


    @Override
    public void resultResetPwdSuccess(Boolean result) {
        CommonExt.toast("重置密码成功");
        Intent intent = new Intent(ResetPwdActivity.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

