package com.hs.goods.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.fragment.BaseFragment;
import com.hs.goods.R;
import com.hs.goods.event.GoodsDetailImageEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2018/4/15.
 *
 */

public class GoodsDetailTabTwoFragment extends BaseFragment{

    private ImageView mGoodsDetailOneIv;

    private ImageView mGoodsDetailTwoIv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_goods_detail_tab_two,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        initView(view);
    }

    private void initView(View view) {
        mGoodsDetailOneIv = view.findViewById(R.id.mGoodsDetailOneIv);
        mGoodsDetailTwoIv = view.findViewById(R.id.mGoodsDetailTwoIv);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGoodsDetailImageEvent(GoodsDetailImageEvent event){
        if(event.getImgOne()!=null&&!event.getImgOne().isEmpty()) {
            CommonExt.loadUrl(event.getImgOne(), mGoodsDetailOneIv);
        }

        if(event.getImgTwo()!=null&&!event.getImgTwo().isEmpty()) {
            CommonExt.loadUrl(event.getImgTwo(), mGoodsDetailTwoIv);
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
