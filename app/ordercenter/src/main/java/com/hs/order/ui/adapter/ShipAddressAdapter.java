package com.hs.order.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.order.R;
import com.hs.order.model.ShipAddre;

/**
 * Created by rnd on 2018/4/20.
 *
 */

public class ShipAddressAdapter extends BaseRecyclerViewAdapter<ShipAddre,ShipAddressAdapter.ViewHolder>{

    public OnOptClickListener mOptClickListener;

    public ShipAddressAdapter(Context mContext,OnOptClickListener mOptClickListener) {
        super(mContext);
        this.mOptClickListener = mOptClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_address_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try{

            final ShipAddre addre = dataList.get(position);

            if(addre.getShipisdefault()==1) {
                holder.mSetDefaultTv.setSelected(true);
            }else{
                holder.mSetDefaultTv.setSelected(false);
            }

            holder.mShipNameTv.setText(addre.getShipusername()+"    "+addre.getShipusermobile());
            holder.mShipAddressTv.setText(addre.getShipaddress());

            holder.mSetDefaultTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.mSetDefaultTv.isSelected()){
                        return;
                    }
                    addre.setShipisdefault(1);

                    mOptClickListener.onSetDefault(addre);
                }
            });

            holder.mEditTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOptClickListener.onEdit(addre);
                }
            });

            holder.mDeleteTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOptClickListener.onDelete(addre);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mSetDefaultTv;
        TextView mShipNameTv;
        TextView mShipAddressTv;
        TextView mEditTv;
        TextView mDeleteTv;
        public ViewHolder(View view){
            super(view);
            mSetDefaultTv = view.findViewById(R.id.mSetDefaultTv);
            mShipNameTv = view.findViewById(R.id.mShipNameTv);
            mShipAddressTv = view.findViewById(R.id.mShipAddressTv);
            mEditTv = view.findViewById(R.id.mEditTv);
            mDeleteTv = view.findViewById(R.id.mDeleteTv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mItemClickListener!=null){
                mItemClickListener.onItemClick(dataList.get(getAdapterPosition()),getAdapterPosition());
            }
        }
    }

    /*
        对应操作接口
     */
    public interface OnOptClickListener{
        void onSetDefault(ShipAddre addre);
        void onEdit(ShipAddre addre);
        void onDelete(ShipAddre addre);
    }
}
