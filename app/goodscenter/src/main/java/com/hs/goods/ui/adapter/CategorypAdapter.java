package com.hs.goods.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.goods.R;
import com.hs.goods.model.category.CateGoryp;

/**
 * Created by Administrator on 2018/4/14.
 * 父类的adapter
 */

public class CategorypAdapter extends BaseRecyclerViewAdapter<CateGoryp,CategorypAdapter.ViewHolder>{

    public CategorypAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_p_category_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        try{
            CateGoryp cateGoryp = dataList.get(position);
            holder.mTopCategoryNameTv.setText(cateGoryp.getCategorypname());
            holder.mTopCategoryNameTv.setSelected(cateGoryp.isSelected());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTopCategoryNameTv;
        public ViewHolder(View view){
            super(view);
            mTopCategoryNameTv = view.findViewById(R.id.mTopCategoryNameTv);
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
