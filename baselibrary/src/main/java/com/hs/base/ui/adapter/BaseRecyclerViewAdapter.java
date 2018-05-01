package com.hs.base.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by rnd on 2018/4/11.
 * Adapter总管理类
 */

public class BaseRecyclerViewAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;

    public List<T> dataList;

    //ItemClick事件
    protected OnItemClickListener<T> mItemClickListener;

    public BaseRecyclerViewAdapter(Context mContext){
        this.mContext = mContext;
    }

    /**
       设置数据
       处理过为null的情况，必须要判断不为空的情况，不然list出现空值错误
    */
    public void setData(List<T> sources) {
        if(sources!=null) {
            dataList = sources;
            notifyDataSetChanged();
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(dataList.get(position), position);
            }
        });
    }

    /**
     * 此处要判断不为空的情况 kotlin的话可以自动处理不为空 java必须要判断 不然会报
     * list.size()为null值错误
     * @return
     */
    @Override
    public int getItemCount() {
        if(dataList!=null) {
            return dataList.size();
        }
        return 0;
    }

    /**
        ItemClick事件声明
     */
    public interface OnItemClickListener<T> {
        void onItemClick(T item, int position);
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mItemClickListener = listener;
    }
}
