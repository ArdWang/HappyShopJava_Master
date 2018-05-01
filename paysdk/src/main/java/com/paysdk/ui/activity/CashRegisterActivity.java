package com.paysdk.ui.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseMvpActivity;
import com.hs.base.utils.YuanFenConverter;
import com.hs.provider.common.ProviderConstant;
import com.hs.provider.router.RouterPath;
import com.paysdk.R;
import com.paysdk.presenter.PayPresenter;
import com.paysdk.presenter.view.PayView;
import java.util.Map;

/**
 * 收银台界面
 */

@Route(path = RouterPath.PaySDK.PATH_PAY)
public class CashRegisterActivity extends BaseMvpActivity<PayPresenter> implements PayView{

    //订单号
    private Integer mOrderId=0;
    //订单总价格
    private Float mTotalPrice;
    private TextView mTotalPriceTv;
    private TextView mAlipayTypeTv,mWeixinTypeTv,mBankCardTypeTv;
    private Button mPayBtn;
    private String payFail;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        ARouter.getInstance().inject(this);

        initData();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_cash_register;
    }

    @Override
    protected void initView() {
        mTotalPriceTv = findViewById(R.id.mTotalPriceTv);
        mAlipayTypeTv = findViewById(R.id.mAlipayTypeTv);
        mWeixinTypeTv = findViewById(R.id.mWeixinTypeTv);
        mBankCardTypeTv = findViewById(R.id.mBankCardTypeTv);
        mPayBtn = findViewById(R.id.mPayBtn);


        mAlipayTypeTv.setSelected(true);
        mAlipayTypeTv.setOnClickListener(this);
        mWeixinTypeTv.setOnClickListener(this);
        mBankCardTypeTv.setOnClickListener(this);
        mPayBtn.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void initData(){
        try {
            mOrderId = getIntent().getIntExtra(ProviderConstant.KEY_ORDER_ID, -1);
            mTotalPrice = getIntent().getFloatExtra(ProviderConstant.KEY_ORDER_PRICE, -1);
            mTotalPriceTv.setText("￥ "+YuanFenConverter.changeY2FSG(mTotalPrice));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mAlipayTypeTv) {
            updatePayType(true, false, false);

        } else if (i == R.id.mWeixinTypeTv) {
            updatePayType(false, true, false);

        } else if (i == R.id.mBankCardTypeTv) {
            updatePayType(false, false, true);

        } else if (i == R.id.mPayBtn) {
            mPresenter.getPaySign(mOrderId, mTotalPrice,this);

        }
    }

    /**
           选择支付类型，UI变化
        */
    private void updatePayType(Boolean isAliPay,Boolean isWeixinPay,Boolean isBankCardPay){
        mAlipayTypeTv.setSelected(isAliPay);
        mWeixinTypeTv.setSelected(isWeixinPay);
        mBankCardTypeTv.setSelected(isBankCardPay);
    }

    @Override
    public void onGetSignResult(final String result) {
        Log.i("result",result);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                Map<String,String> params = new PayTask(CashRegisterActivity.this).payV2(result,true);
                if(params.get("resultStatus").equals("9000")){
                    handler.sendEmptyMessage(200);
                }else{
                    payFail = params.get("memo");
                    handler.sendEmptyMessage(0);
                }
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void onPayOrderResult(Boolean result) {
        CommonExt.toast("支付成功");
        finish();
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    CommonExt.toast("支付失败！，"+payFail);
                    break;

                case 200:
                    payOrder(mOrderId);
                    break;
            }

        }
    };

    private void payOrder(Integer orderId){
        mPresenter.payOrder(orderId,this);
    }


}
