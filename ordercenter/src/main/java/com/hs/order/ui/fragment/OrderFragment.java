package com.hs.order.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.base.ui.fragment.BaseMvpFragment;
import com.hs.order.R;
import com.hs.order.common.OrderConstant;
import com.hs.order.model.orders.Orders;
import com.hs.order.presenter.OrderListPresenter;
import com.hs.order.presenter.view.OrderListView;
import com.hs.order.ui.activity.OrderDetailActivity;
import com.hs.order.ui.adapter.OrderAdapter;
import com.hs.provider.common.ProviderConstant;
import com.hs.provider.router.RouterPath;
import com.kennyc.view.MultiStateView;
import java.util.List;


/**
 * Created by Administrator on 2018/4/22.
 *
 */

public class OrderFragment extends BaseMvpFragment<OrderListPresenter> implements OrderListView{

    private MultiStateView mMultiStateView;
    private RecyclerView mOrderRv;
    private OrderAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_order,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
    }

    private void initView(View view) {
        mMultiStateView = view.findViewById(R.id.mMultiStateView);
        mOrderRv = view.findViewById(R.id.mOrderRv);

        mOrderRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new OrderAdapter(getContext());
        mOrderRv.setAdapter(mAdapter);

        mAdapter.listener = new OrderAdapter.OnOptClickListener() {
            @Override
            public void onOptClick(int optType, final Orders orders) {
                switch (optType){
                    case OrderConstant.OPT_ORDER_PAY:
                        ARouter.getInstance().build(RouterPath.PaySDK.PATH_PAY)
                                .withInt(ProviderConstant.KEY_ORDER_ID,orders.getOrderId())
                                .withFloat(ProviderConstant.KEY_ORDER_PRICE,orders.getTotalPrice())
                                .navigation();
                        break;

                    case OrderConstant.OPT_ORDER_CONFIRM:
                        confirmOrder(orders);
                        break;

                    case OrderConstant.OPT_ORDER_CANCEL:
                        new AlertView("取消", "确定要取消该订单？", "取消", null, new String[]{"确定"}
                                , getContext(), AlertView.Style.Alert, new OnItemClickListener() {
                            @Override
                            public void onItemClick(Object o, int position) {
                                if(position==0) {
                                    cancelOrder(orders);
                                }
                            }
                        }).show();
                        break;
                }
            }
        };

        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<Orders>() {
            @Override
            public void onItemClick(Orders item, int position) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                intent.putExtra(ProviderConstant.KEY_ORDER_ID,item.getOrderId());
                startActivity(intent);
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

    @SuppressLint("SetTextI18n")
    private void loadData(){
        CommonExt.startMultiLoading(mMultiStateView);
        mPresenter.getAllOrder(getArguments().getInt(OrderConstant.KEY_ORDER_STATUS,-1)
                ,this);
        //mPresenter.getAllOrder(getMdata(), this);
    }

    private void confirmOrder(Orders orders){
        mPresenter.confirmOrder(orders.getOrderId(),this);
    }

    private void cancelOrder(Orders orders){
        mPresenter.cancelOrder(orders.getOrderId(),this);
    }

    @Override
    public void onGetOrderListResult(List<Orders> result) {
        if(result!=null){
            if(result.size()>0) {
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                mAdapter.setData(result);
            }else{
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
            }
        }else{
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        }
    }

    @Override
    public void onConfirmOrderResult(Boolean result) {
        CommonExt.toast("确认收货成功");
        loadData();
    }

    @Override
    public void onCancelOrderResult(Boolean result) {
        CommonExt.toast("取消订单成功");
        loadData();
    }

    /**
        创建Fragment
     */
    public static Fragment cerateFragment(int position){
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt(OrderConstant.KEY_ORDER_STATUS,position);
        fragment.setArguments(bundle);
        return fragment;
    }

}
