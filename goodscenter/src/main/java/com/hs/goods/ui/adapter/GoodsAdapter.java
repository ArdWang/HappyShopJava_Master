package com.hs.goods.ui.adapter;

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
import com.hs.goods.R;
import com.hs.goods.model.goods.GoodsInfo;

/**
 * Created by Administrator on 2018/4/14.
 *
 */

public class GoodsAdapter extends BaseRecyclerViewAdapter<GoodsInfo,GoodsAdapter.ViewHolder> {

    public GoodsAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_goods_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            GoodsInfo goodsBean = dataList.get(position);
            //加载图片
            GlideUtils.loadUrlImage(mContext,goodsBean.getGoodsdefaulticon(),holder.mGoodsIconIv);
            holder.mGoodsDescTv.setText(goodsBean.getGoodsdesc());
            holder.mGoodsPriceTv.setText("¥ "+YuanFenConverter.changeY2FSG(goodsBean.getGoodsdefaultprice()));
            holder.mGoodsSalesTv.setText("销量 "+goodsBean.getGoodssalescount());
            holder.mGoodsStockTv.setText("库存 "+goodsBean.getGoodsstockcount());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mGoodsIconIv;
        TextView mGoodsDescTv;
        TextView mGoodsPriceTv;
        TextView mGoodsSalesTv;
        TextView mGoodsStockTv;

        public ViewHolder(View view){
            super(view);
            mGoodsIconIv = view.findViewById(R.id.mGoodsIconIv);
            mGoodsDescTv = view.findViewById(R.id.mGoodsDescTv);
            mGoodsPriceTv = view.findViewById(R.id.mGoodsPriceTv);
            mGoodsSalesTv = view.findViewById(R.id.mGoodsSalesTv);
            mGoodsStockTv = view.findViewById(R.id.mGoodsStockTv);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(mItemClickListener!=null){
                mItemClickListener.onItemClick(dataList.get(getAdapterPosition()),getAdapterPosition());
            }
        }
    }
}
