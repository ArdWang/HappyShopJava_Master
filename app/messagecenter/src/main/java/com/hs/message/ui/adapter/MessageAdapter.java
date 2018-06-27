package com.hs.message.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.message.R;
import com.hs.message.model.MsgInfo;
import java.text.SimpleDateFormat;


import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends BaseRecyclerViewAdapter<MsgInfo,MessageAdapter.ViewHolder>{

    private OnDragClickListener onDragClickListener;

    public MessageAdapter(Context mContext,OnDragClickListener onDragClickListener) {
        super(mContext);
        this.onDragClickListener = onDragClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_message_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            MsgInfo msgInfos = dataList.get(position);
            if(msgInfos.getMsgtype()==0){
                CommonExt.loadUrl(msgInfos.getMsgimg(),holder.msgImage);
            }else if(msgInfos.getMsgtype()==1){
                holder.msgImage.setImageResource(R.drawable.kf1);
            }else if(msgInfos.getMsgtype()==2){
                holder.msgImage.setImageResource(R.drawable.kf2);
            }else if(msgInfos.getMsgtype()==3){
                holder.msgImage.setImageResource(R.drawable.kf3);
            }else if(msgInfos.getMsgtype()==4){
                holder.msgImage.setImageResource(R.drawable.kf4);
            }

            holder.msgTitle.setText(msgInfos.getMsgtitle());
            holder.msgCon.setText(msgInfos.getMsgcon());
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sf.format(msgInfos.getMsgtime());
            holder.msgTime.setText(time);

            if(msgInfos.getMsgread()==0){
                //显示
                holder.msgRead.setVisibility(View.VISIBLE);
                holder.btnEdit.setText("未读");
            }else{
                //隐藏
                holder.msgRead.setVisibility(View.GONE);
                holder.btnEdit.setText("已读");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView msgImage;
        TextView msgTitle;
        TextView msgCon;
        TextView msgTime;
        TextView msgRead;
        Button btnEdit,btnDelete;

        public ViewHolder(View view){
            super(view);
            msgImage = view.findViewById(R.id.mMsgImage);
            msgTitle = view.findViewById(R.id.mMsgTitle);
            msgCon = view.findViewById(R.id.mMsgCon);
            msgTime = view.findViewById(R.id.mMsgTime);
            msgRead = view.findViewById(R.id.mMsgRead);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);

            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.btnEdit) {
                if (onDragClickListener != null) {
                    onDragClickListener.onEditClick(v, getAdapterPosition());
                }

            } else if (i == R.id.btnDelete) {
                if (onDragClickListener != null) {
                    onDragClickListener.onDeleteClick(v, getAdapterPosition());
                }
            }
        }
    }

    //自定义拖动接口
    public interface OnDragClickListener{
        void onEditClick(View view, int pos);
        void onDeleteClick(View view, int pos);
    }
}
