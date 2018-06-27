package com.hs.order.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseMvpActivity;
import com.hs.base.utils.YuanFenConverter;
import com.hs.order.R;
import com.hs.order.event.SelectAddreEvent;
import com.hs.order.model.orders.Orders;
import com.hs.order.presenter.OrderConfirmPresenter;
import com.hs.order.presenter.view.OrderConfirmView;
import com.hs.order.ui.adapter.OrderGoodsAdapter;
import com.hs.provider.common.ProviderConstant;
import com.hs.provider.router.RouterPath;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by rnd on 2018/4/20.
 * 选择所以的订单确认Activity
 */

@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
public class OrderConfirmActivity extends BaseMvpActivity<OrderConfirmPresenter> implements OrderConfirmView{
    private OrderGoodsAdapter mAdapter;
    private RecyclerView mOrderGoodsRv;
    //选择收货人
    private RelativeLayout mShipView;

    private TextView mSelectShipTv;

    private TextView mTotalPriceTv;

    private TextView mShipNameTv;

    private TextView mShipAddressTv;

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    Integer mOrderId=0;

    private Orders orderList;

    private Button mSubmitOrderBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        loadData();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_order_confirm;
    }

    @Override
    protected void initView() {
        mOrderGoodsRv = findViewById(R.id.mOrderGoodsRv);
        mShipView = findViewById(R.id.mShipView);
        mTotalPriceTv = findViewById(R.id.mTotalPriceTv);
        mSelectShipTv = findViewById(R.id.mSelectShipTv);
        mShipNameTv = findViewById(R.id.mShipNameTv);
        mShipAddressTv = findViewById(R.id.mShipAddressTv);
        mSubmitOrderBtn = findViewById(R.id.mSubmitOrderBtn);

        mOrderGoodsRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OrderGoodsAdapter(this);
        mOrderGoodsRv.setAdapter(mAdapter);

        mShipView.setOnClickListener(this);
        mSelectShipTv.setOnClickListener(this);
        mSubmitOrderBtn.setOnClickListener(this);
    }

    private void loadData(){
        mPresenter.getOrderById(mOrderId,this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        Intent intent;
        if(i==R.id.mShipView){
            intent = new Intent(OrderConfirmActivity.this,ShipAddressActivity.class);
            startActivity(intent);
        }
        else if(i==R.id.mSelectShipTv){
            intent = new Intent(OrderConfirmActivity.this,ShipAddressActivity.class);
            startActivity(intent);
        }
        else if(i==R.id.mSubmitOrderBtn){
            if(orderList.getShipAddre()==null){
                CommonExt.toast("收货地址不能为空");
                return;
            }
            mPresenter.submitOrder(orderList,this);

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectAddreEvent(SelectAddreEvent event){
        if(event.getShipAddre()!=null){
            orderList.setShipAddre(event.getShipAddre());
        }
        updateAddressView();
    }

    @SuppressLint("SetTextI18n")
    private void updateAddressView() {
        if(orderList.getShipAddre()==null){
            mSelectShipTv.setVisibility(View.VISIBLE);
            mShipView.setVisibility(View.GONE);
        }else{
            mSelectShipTv.setVisibility(View.GONE);
            mShipView.setVisibility(View.VISIBLE);
            mShipNameTv.setText(orderList.getShipAddre().getShipusername()+"     "+orderList.getShipAddre().getShipusermobile());
            mShipAddressTv.setText(orderList.getShipAddre().getShipaddress());
        }
    }




    @SuppressLint("SetTextI18n")
    @Override
    public void onGetOrderByIdResult(Orders result) {
        try {
            orderList = result;
            mAdapter.setData(result.getOrderGoods());
            String pricce = YuanFenConverter.changeY2FSG(result.getTotalPrice());
            mTotalPriceTv.setText("合计："+"￥ "+pricce);

            updateAddressView();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onSubmitOrderResult(Boolean result) {
        CommonExt.toast("提交成功");
        ARouter.getInstance().build(RouterPath.PaySDK.PATH_PAY)
                .withInt(ProviderConstant.KEY_ORDER_ID,orderList.getOrderId())
                .withFloat(ProviderConstant.KEY_ORDER_PRICE,orderList.getTotalPrice())
                .navigation();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
