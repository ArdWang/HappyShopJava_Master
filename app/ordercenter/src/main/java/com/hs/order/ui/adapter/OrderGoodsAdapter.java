package com.hs.order.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.base.utils.GlideUtils;
import com.hs.base.utils.YuanFenConverter;
import com.hs.order.R;
import com.hs.order.model.orders.OrderGoods;

/**
 * 订单商品列表
 */

public class OrderGoodsAdapter extends BaseRecyclerViewAdapter<OrderGoods,OrderGoodsAdapter.ViewHolder>{

    public OrderGoodsAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_goods_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            OrderGoods goods = dataList.get(position);
            //加载图片
            GlideUtils.loadUrlImage(mContext,goods.getGoodsicon(),holder.mGoodsIconIv);
            holder.mGoodsDescTv.setText(goods.getGoodsdesc());
            holder.mGoodsSkuTv.setText(goods.getGoodssku());
            holder.mGoodsPriceTv.setText("¥ "+YuanFenConverter.changeY2FSG(goods.getGoodsprice()));
            holder.mGoodsCountTv.setText("x"+goods.getGoodscount());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView mGoodsIconIv;
        TextView mGoodsDescTv;
        TextView mGoodsSkuTv;
        TextView mGoodsPriceTv;
        TextView mGoodsCountTv;

        public ViewHolder(View view){
            super(view);
            mGoodsIconIv = view.findViewById(R.id.mGoodsIconIv);
            mGoodsDescTv = view.findViewById(R.id.mGoodsDescTv);
            mGoodsSkuTv = view.findViewById(R.id.mGoodsSkuTv);
            mGoodsPriceTv = view.findViewById(R.id.mGoodsPriceTv);
            mGoodsCountTv = view.findViewById(R.id.mGoodsCountTv);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
