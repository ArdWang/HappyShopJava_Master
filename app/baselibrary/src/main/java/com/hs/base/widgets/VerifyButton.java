package com.hs.base.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.Button;

import com.hs.base.R;


/**
 * Created by rnd on 2018/4/8.
 * 获取验证码的按钮
 */

@SuppressLint({"ViewConstructor", "AppCompatCustomView"})
public class VerifyButton extends Button{

    private Handler mHandler;

    private int mCount = 60;

    private OnVerifyBtnClick mOnVerifyBtnClick;

    private Context context;

    public VerifyButton(Context context) {
        super(context);
    }

    public VerifyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setText("获取验证码");
        this.context = context;
        mHandler = new Handler();

    }

    public void requestSendVerifyNumber(){
        mHandler.postDelayed(countDown,0);

        if(mOnVerifyBtnClick!=null){
            mOnVerifyBtnClick.onClick();
        }
    }

    private Runnable countDown = new Runnable() {
        @Override
        public void run() {
            VerifyButton.this.setText(mCount+" s");
            VerifyButton.this.setBackgroundColor(context.getResources().getColor(R.color.common_disable));
            VerifyButton.this.setTextColor(context.getResources().getColor(R.color.common_white));
            VerifyButton.this.setEnabled(false);

            if (mCount > 0) {
                mHandler.postDelayed(this, 1000);
            } else {
                resetCounter(VerifyButton.this.getText().toString());
            }
            mCount--;

        }
    };

    private void resetCounter(String text){
        this.setEnabled(true);
        if(!text.isEmpty()&&!text.equals("")){
            this.setText(text);
        }else{
            this.setText("重获验证码");
        }

        this.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        this.setTextColor(context.getResources().getColor(R.color.common_blue));
        mCount = 60;
    }


    public void removeRunable() {
       mHandler.removeCallbacks(countDown);
    }

    /**
       点击事件接口
    */
    interface OnVerifyBtnClick {
        void onClick();
    }

    public void setOnVerifyBtnClick(OnVerifyBtnClick onVerifyBtnClick) {
        this.mOnVerifyBtnClick = onVerifyBtnClick;
    }
}
