package com.hs.goods.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.hs.goods.R;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.event.SkuChangedEvent;
import com.hs.goods.model.goods.GoodsSku;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2018/4/15.
 *
 */

public class SkuView extends FrameLayout {

    private GoodsSku skuBean;
    private TextView mSkuTitleTv;
    private TagFlowLayout mSkuContentView;
    private Context context;
    private int pos = 0;

    public SkuView(@NonNull Context context) {
        super(context);
        this.context = context;
        View view = View.inflate(context, R.layout.layout_sku_view, this);
        mSkuTitleTv = view.findViewById(R.id.mSkuTitleTv);
        mSkuContentView = view.findViewById(R.id.mSkuContentView);
    }

    public SkuView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkuView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
       动态设置SKU数据
    */
    public void setSkuData(GoodsSku skuBeans){
        skuBean = skuBeans;
        mSkuTitleTv.setText(skuBean.getGoodsskutitle());
        mSkuContentView.setAdapter(new TagAdapter(skuBean.getGoodsskucon()) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                View view = LayoutInflater.from(context)
                        .inflate(R.layout.layout_sku_item,parent,false);
                TextView mSkuViewTv = view.findViewById(R.id.mSkuViewTv);
                mSkuViewTv.setText(o.toString());
                return view;
            }
        });
        mSkuContentView.getAdapter().setSelectedList(0);

        mSkuContentView.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                pos = position;
                //RxBus.get().send(new SkuChangedEvent());
                //发送事件
                EventBus.getDefault().post(new SkuChangedEvent());
                return true;
            }
        });
    }


    /**
       获取选中的SKU
    */
    public String getSkuInfo() {
        return mSkuTitleTv.getText().toString() + GoodsConstant.SKU_SEPARATOR +
                skuBean.getGoodsskucon().get(pos);
    }

}
