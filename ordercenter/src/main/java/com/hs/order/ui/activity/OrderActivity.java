package com.hs.order.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.hs.base.ui.activity.BaseActivity;
import com.hs.order.R;
import com.hs.order.common.OrderConstant;
import com.hs.order.ui.adapter.OrderVpAdapter;
import com.hs.order.ui.fragment.OrderFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 *
 */

public class OrderActivity extends BaseActivity{
    private TabLayout mOrderTab;
    private ViewPager mOrderVp;
    private List<String> titles;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //initData();
        initView();
    }

    /**
        此方法不可以
        //"全部","待付款","待收货","已完成","已取消"
     */
    private void initData() {
        titles = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        titles.add("全部");
        titles.add("待付款");
        titles.add("待收货");
        titles.add("已完成");
        titles.add("已取消");

        for(int i=0;i<titles.size();i++){
            mFragmentList.add(OrderFragment.cerateFragment(i));
        }
    }

    private void initView() {
        mOrderTab = findViewById(R.id.mOrderTab);
        mOrderVp = findViewById(R.id.mOrderVp);
        mOrderTab.setTabMode(TabLayout.MODE_FIXED);
        //mOrderVp.setNoScroll(true);
        mOrderVp.setAdapter(new OrderVpAdapter(getSupportFragmentManager()));
        mOrderTab.setupWithViewPager(mOrderVp);
        mOrderVp.setCurrentItem(getIntent().getIntExtra(OrderConstant.KEY_ORDER_STATUS, -1));
        //mOrderVp.setOffscreenPageLimit(5);
    }

}
