package com.hs.order.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseMvpActivity;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.order.R;
import com.hs.order.common.OrderConstant;
import com.hs.order.event.SelectAddreEvent;
import com.hs.order.model.ShipAddre;
import com.hs.order.presenter.ShipAddressPresenter;
import com.hs.order.presenter.view.ShipAddressView;
import com.hs.order.ui.adapter.ShipAddressAdapter;
import com.kennyc.view.MultiStateView;
import org.greenrobot.eventbus.EventBus;
import java.util.List;

/**
 * Created by rnd on 2018/4/20.
 *
 */

public class ShipAddressActivity extends BaseMvpActivity<ShipAddressPresenter>
        implements ShipAddressView,ShipAddressAdapter.OnOptClickListener {

    private Button mAddAddressBtn;

    private RecyclerView mAddressRv;

    private MultiStateView mMultiStateView;

    private ShipAddressAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView() {
        mMultiStateView = findViewById(R.id.mMultiStateView);
        mAddAddressBtn = findViewById(R.id.mAddAddressBtn);
        mAddressRv = findViewById(R.id.mAddressRv);


        mAddressRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShipAddressAdapter(this,this);
        mAddressRv.setAdapter(mAdapter);

        mAddAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipAddressActivity.this,ShipAddressEditActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<ShipAddre>() {
            @Override
            public void onItemClick(ShipAddre item, int position) {
                EventBus.getDefault().post(new SelectAddreEvent(item));
                finish();
            }
        });


        mMultiStateView.getView(MultiStateView.VIEW_STATE_EMPTY).findViewById(R.id.mLoading)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadData();
                    }
                });

    }

    /*
       加载数据
    */
    private void loadData() {
        mPresenter.getShipList(this);
    }


    @Override
    public void onGetShipAddressResult(List<ShipAddre> result) {
        if (result != null && result.size() > 0) {
            mAdapter.setData(result);
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        } else {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        }
    }

    @Override
    public void onSetDefaultAddreResult(Boolean result) {
        loadData();
    }

    @Override
    public void onDeleteShipAddreResult(Boolean result) {
        CommonExt.toast("删除成功");
        loadData();
    }



    @Override
    public void onSetDefault(ShipAddre addre) {
        mPresenter.setDefaultShipAddre(addre,this);
    }

    @Override
    public void onEdit(ShipAddre addre) {
        Intent intent = new Intent(ShipAddressActivity.this,ShipAddressEditActivity.class);
        intent.putExtra(OrderConstant.KEY_SHIP_ADDRESS,addre);
        startActivity(intent);
    }

    @Override
    public void onDelete(final ShipAddre addre) {
        new AlertView("删除", "确定删除该地址？", "取消", null, new String[]{"确定"}
                , this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if(position==0) {
                    onDeleteShipAddre(addre.getShipid());
                }
            }
        }).show();
    }

    private void onDeleteShipAddre(Integer shipId){
        mPresenter.deleteShipAddre(shipId,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
