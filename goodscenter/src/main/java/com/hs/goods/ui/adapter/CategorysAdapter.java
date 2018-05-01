package com.hs.goods.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.base.utils.GlideUtils;
import com.hs.goods.R;
import com.hs.goods.model.category.CateGorys;

/**
 * Created by Administrator on 2018/4/14.
 * 父类的adapter
 */

public class CategorysAdapter extends BaseRecyclerViewAdapter<CateGorys,CategorysAdapter.ViewHolder>{

    public CategorysAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_s_category_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        try{
            CateGorys cateGorys = dataList.get(position);
            //加载图片
            GlideUtils.loadUrlImage(mContext,cateGorys.getCategorysicon(),holder.mCategoryIconIv);
            holder.mSCategoryNameTv.setText(cateGorys.getCategorysname());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mCategoryIconIv;
        TextView mSCategoryNameTv;

        public ViewHolder(View view){
            super(view);
            mCategoryIconIv = view.findViewById(R.id.mCategoryIconIv);
            mSCategoryNameTv = view.findViewById(R.id.mSecondCategoryNameTv);
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
