package com.hs.goods.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.hs.goods.ui.fragment.GoodsDetailTabOneFragment;
import com.hs.goods.ui.fragment.GoodsDetailTabTwoFragment;
import java.util.List;

/**
 * Created by Administrator on 2018/4/15.
 *
 */

public class GoodsDetailVpAdapter extends FragmentPagerAdapter {
    private List<String> titles;

    public GoodsDetailVpAdapter(FragmentManager fm,List<String> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new GoodsDetailTabOneFragment();
        }
        return new GoodsDetailTabTwoFragment();
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
