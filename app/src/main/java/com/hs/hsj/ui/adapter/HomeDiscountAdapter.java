package com.hs.hsj.ui.adapter;

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
import com.hs.hsj.R;

/**
 * Created by rnd on 2018/4/13.
 * 首页折扣区域Adapter
 */

public class HomeDiscountAdapter extends BaseRecyclerViewAdapter<String,HomeDiscountAdapter.ViewHolder>
{

    public HomeDiscountAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_home_discount_item,parent,false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        try{
            //加载图片
            GlideUtils.loadUrlImage(mContext,dataList.get(position),holder.mGoodsIconIv);
            //静态假数据
            holder.mDiscountAfterTv.setText("￥123.00");
            holder.mDiscountBeforeTv.setText("￥1000.00");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView mGoodsIconIv;
        TextView mDiscountBeforeTv;
        TextView mDiscountAfterTv;


       public ViewHolder(View view){
            super(view);
            mGoodsIconIv = view.findViewById(R.id.mGoodsIconIv);
            mDiscountAfterTv = view.findViewById(R.id.mDiscountAfterTv);
            mDiscountBeforeTv = view.findViewById(R.id.mDiscountBeforeTv);
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
