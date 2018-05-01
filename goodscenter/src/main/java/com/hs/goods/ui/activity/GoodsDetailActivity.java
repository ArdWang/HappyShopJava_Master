package com.hs.goods.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hs.base.ui.activity.BaseActivity;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.goods.R;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.event.AddCartEvent;
import com.hs.goods.event.UpdateCartSizeEvent;
import com.hs.goods.ui.adapter.GoodsDetailVpAdapter;
import com.hs.provider.common.CommonUtils;
import com.hs.provider.router.RouterPath;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2018/4/15.
 *
 */

public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener{
    private TabLayout mGoodsDetailTab;
    private ViewPager mGoodsDetailVp;
    private GoodsDetailVpAdapter goodsDetailVpAdapter;
    private List<String> titles = new ArrayList<>();
    private ImageView mLeftIv;
    private Button mAddCartBtn;
    private TextView mEnterCartTv;
    private QBadgeView mCartBdage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        EventBus.getDefault().register(this);

        initView();
        loadCartSize();
    }

    private void initView() {
        titles.add("商品");
        titles.add("详情");

        mGoodsDetailTab = findViewById(R.id.mGoodsDetailTab);
        mGoodsDetailVp = findViewById(R.id.mGoodsDetailVp);
        mLeftIv = findViewById(R.id.mLeftIv);
        mAddCartBtn = findViewById(R.id.mAddCartBtn);
        mEnterCartTv = findViewById(R.id.mEnterCartTv);
        mLeftIv.setOnClickListener(this);
        mAddCartBtn.setOnClickListener(this);
        mEnterCartTv.setOnClickListener(this);

        mGoodsDetailTab.setTabMode(TabLayout.MODE_FIXED);
        goodsDetailVpAdapter = new GoodsDetailVpAdapter(getSupportFragmentManager(),titles);
        mGoodsDetailVp.setAdapter(goodsDetailVpAdapter);
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp);

        mCartBdage =new QBadgeView(this);

        mEnterCartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsDetailActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
       加载购物车数量
    */
    private void loadCartSize() {
        setCartBadge();
    }

    private void setCartBadge(){
        mCartBdage.setBadgeGravity(Gravity.END|Gravity.TOP);
        mCartBdage.setGravityOffset(22f,-2f,true);
        mCartBdage.setBadgeTextSize(10f,true);
        mCartBdage.bindTarget(mEnterCartTv).setBadgeNumber(AppPrefsUtils
                .getInstance().getInt(GoodsConstant.SP_CART_SIZE));

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateCartSizeEvent(UpdateCartSizeEvent event){
        setCartBadge();
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.mLeftIv) {
            finish();
        }

        else if (i==R.id.mAddCartBtn){
            if(CommonUtils.isLogined()) {
                EventBus.getDefault().post(new AddCartEvent());
            }else {
                ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation();
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
