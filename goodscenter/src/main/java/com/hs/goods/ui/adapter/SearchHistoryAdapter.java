package com.hs.goods.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.goods.R;

/**
 * Created by Administrator on 2018/4/15.
 *
 */

public class SearchHistoryAdapter extends BaseRecyclerViewAdapter<String,SearchHistoryAdapter.ViewHolder>{

    public SearchHistoryAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_search_history_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{

            String model = dataList.get(position);
            holder.mSearchHistoryTv.setText(model);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mSearchHistoryTv;

        public ViewHolder(View view){
            super(view);
            mSearchHistoryTv = view.findViewById(R.id.mSearchHistoryTv);
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
