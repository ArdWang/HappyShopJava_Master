package com.hs.goods.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.hs.base.ui.activity.BaseActivity;
import com.hs.goods.R;
import com.hs.goods.ui.fragment.CartFragment;

/**
    购物车Activity
    只是Fragment一个壳
 */
public class CartActivity extends BaseActivity{
    private CartFragment cartFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Fragment fragmentManager = getSupportFragmentManager().findFragmentById(R.id.fragment_cart);
        cartFragment = (CartFragment) fragmentManager;
        cartFragment.setBackVisible(true);
    }
}
