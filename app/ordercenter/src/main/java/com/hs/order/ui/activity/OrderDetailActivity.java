package com.hs.order.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.hs.base.ui.activity.BaseMvpActivity;
import com.hs.base.utils.YuanFenConverter;
import com.hs.base.widgets.LabelTextView;
import com.hs.order.R;
import com.hs.order.model.orders.Orders;
import com.hs.order.presenter.OrderDetailPresenter;
import com.hs.order.presenter.view.OrderDetailView;
import com.hs.order.ui.adapter.OrderGoodsAdapter;
import com.hs.provider.common.ProviderConstant;
import com.hs.provider.router.RouterPath;

/**
 * Created by rnd on 2018/4/23.
 *
 */
@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
public class OrderDetailActivity extends BaseMvpActivity<OrderDetailPresenter> implements OrderDetailView {
    private OrderGoodsAdapter mAdapter;
    private RecyclerView mOrderGoodsRv;
    private LabelTextView mShipNameTv,mShipMobileTv
            ,mShipAddressTv,mTotalPriceTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        mOrderGoodsRv = findViewById(R.id.mOrderGoodsRv);
        mShipNameTv = findViewById(R.id.mShipNameTv);
        mShipMobileTv = findViewById(R.id.mShipMobileTv);
        mShipAddressTv = findViewById(R.id.mShipAddressTv);
        mTotalPriceTv = findViewById(R.id.mTotalPriceTv);

        mOrderGoodsRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OrderGoodsAdapter(this);
        mOrderGoodsRv.setAdapter(mAdapter);
    }

    private void loadData(){
        //Log.i("id的值为：",mOrderId+" qq");
        mPresenter.getOrderById(getIntent().getIntExtra(ProviderConstant.KEY_ORDER_ID,
                -1),this);
    }

    @Override
    public void onGetOrderDetailResult(Orders orders) {
        try {
            mShipNameTv.setContentText(orders.getShipAddre().getShipusername());
            mShipMobileTv.setContentText(orders.getShipAddre().getShipusermobile());
            mShipAddressTv.setContentText(orders.getShipAddre().getShipaddress());
            mTotalPriceTv.setContentText("¥ "+YuanFenConverter.changeY2FSG(orders.getTotalPrice()));
            mAdapter.setData(orders.getOrderGoods());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
