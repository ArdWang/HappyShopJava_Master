package com.hs.order.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.hs.order.common.OrderConstant;
import com.hs.order.ui.fragment.OrderFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 *
 */
public class OrderVpAdapter extends FragmentPagerAdapter{

    private List<String> titles = new ArrayList<>();

    public OrderVpAdapter(FragmentManager fm){
        super(fm);
        titles.add("全部");
        titles.add("待付款");
        titles.add("待收货");
        titles.add("已完成");
        titles.add("已取消");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment =new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(OrderConstant.KEY_ORDER_STATUS,position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
