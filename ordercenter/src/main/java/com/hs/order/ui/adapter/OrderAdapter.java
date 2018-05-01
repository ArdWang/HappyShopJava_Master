package com.hs.order.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.base.utils.DisplayUtils;
import com.hs.base.utils.YuanFenConverter;
import com.hs.order.R;
import com.hs.order.common.OrderConstant;
import com.hs.order.common.OrderStatus;
import com.hs.order.model.orders.OrderGoods;
import com.hs.order.model.orders.Orders;

/**
 * Created by Administrator on 2018/4/22.
 *
 */

public class OrderAdapter extends BaseRecyclerViewAdapter<Orders,OrderAdapter.ViewHolder> {

    public OnOptClickListener listener;

    public OrderAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            final Orders orders = dataList.get(position);
            int mTotalCount = 0;

            if(orders.getOrderGoods().size()==1){//单个商品的时候
                holder.mSingleGoodsView.setVisibility(View.VISIBLE);
                holder.mMultiGoodsView.setVisibility(View.GONE);
                OrderGoods orderGoods = orders.getOrderGoods().get(0);
                CommonExt.loadUrl(orderGoods.getGoodsicon(),holder.mGoodsIconIv);

                holder.mGoodsDescTv.setText(orderGoods.getGoodsdesc());//商品描述
                holder.mGoodsPriceTv.setText("￥ "+YuanFenConverter.changeY2FSG(orderGoods.getGoodsprice()));//商品价格
                holder.mGoodsCountTv.setText("x "+orderGoods.getGoodscount());//商品数量
                mTotalCount = orderGoods.getGoodscount();
            }else{//多个商品显示
                holder.mSingleGoodsView.setVisibility(View.GONE);
                holder.mMultiGoodsView.setVisibility(View.VISIBLE);

                holder.mMultiGoodsView.removeAllViews();
                for (OrderGoods orderGoods : orders.getOrderGoods()){//动态添加商品视图
                    ImageView imageView =new ImageView(mContext);
                    //TextView textView = new TextView(mContext);
                    ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(DisplayUtils.dp2px(mContext,80.0f)
                            ,DisplayUtils.dp2px(mContext,80.0f));

                    lp.rightMargin = DisplayUtils.dp2px(mContext,15.0f);
                    imageView.setLayoutParams(lp);
                    //textView.setLayoutParams(lp);
                    CommonExt.loadUrl(orderGoods.getGoodsicon(),imageView);
                    holder.mMultiGoodsView.addView(imageView);
                    mTotalCount += orderGoods.getGoodscount();
                }
            }

            holder.mOrderInfoTv.setText("合计 "+mTotalCount+"件商品，总价 ￥ "
                    +YuanFenConverter.changeY2FSG(orders.getTotalPrice()));

            switch (orders.getOrderStatus()){
                case OrderStatus.ORDER_WAIT_PAY:
                    holder.mOrderStatusNameTv.setText("待支付");
                    holder.mOrderStatusNameTv.setTextColor(mContext.getResources().getColor(R.color.common_red));
                    setOptVisible(false,true,true,holder);
                    break;

                case OrderStatus.ORDER_WAIT_CONFIRM:
                    holder.mOrderStatusNameTv.setText("待收货");
                    holder.mOrderStatusNameTv.setTextColor(mContext.getResources().getColor(R.color.common_blue));
                    setOptVisible(true,false,true,holder);
                    break;

                case OrderStatus.ORDER_COMPLETED:
                    holder.mOrderStatusNameTv.setText("已完成");
                    holder.mOrderStatusNameTv.setTextColor(mContext.getResources().getColor(R.color.common_yellow));
                    setOptVisible(false,false,false,holder);
                    break;

                case OrderStatus.ORDER_CANCELED:
                    holder.mOrderStatusNameTv.setText("已取消");
                    holder.mOrderStatusNameTv.setTextColor(mContext.getResources().getColor(R.color.common_gray));
                    setOptVisible(false,false,false,holder);
                    break;
            }

            holder.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onOptClick(OrderConstant.OPT_ORDER_CONFIRM,orders);
                }
            });

            holder.mCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onOptClick(OrderConstant.OPT_ORDER_CANCEL,orders);
                }
            });

            holder.mPayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onOptClick(OrderConstant.OPT_ORDER_PAY,orders);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
       设置操作按钮显示或隐藏
    */
    private void setOptVisible(Boolean confirmVisible, Boolean waitPayVisible,Boolean cancelVisible,ViewHolder holder) {
        CommonExt.setVisible(holder.mConfirmBtn,confirmVisible);
        CommonExt.setVisible(holder.mPayBtn,waitPayVisible);
        CommonExt.setVisible(holder.mCancelBtn,cancelVisible);

        if (confirmVisible || waitPayVisible || cancelVisible){
            CommonExt.setVisible(holder.mBottomView,true);
        }else{
            CommonExt.setVisible(holder.mBottomView,false);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        RelativeLayout mSingleGoodsView;
        LinearLayout mMultiGoodsView;
        LinearLayout mBottomView;
        ImageView mGoodsIconIv;
        TextView mGoodsDescTv;
        TextView mGoodsPriceTv;
        TextView mGoodsCountTv;
        TextView mOrderInfoTv;
        TextView mOrderStatusNameTv;

        Button mConfirmBtn;
        Button mPayBtn;
        Button mCancelBtn;

        public ViewHolder(View view){
            super(view);
            mSingleGoodsView = view.findViewById(R.id.mSingleGoodsView);
            mMultiGoodsView = view.findViewById(R.id.mMultiGoodsView);
            mGoodsIconIv = view.findViewById(R.id.mGoodsIconIv);
            mGoodsDescTv = view.findViewById(R.id.mGoodsDescTv);
            mGoodsPriceTv = view.findViewById(R.id.mGoodsPriceTv);
            mGoodsCountTv = view.findViewById(R.id.mGoodsCountTv);
            mOrderInfoTv = view.findViewById(R.id.mOrderInfoTv);
            mOrderStatusNameTv = view.findViewById(R.id.mOrderStatusNameTv);
            mBottomView = view.findViewById(R.id.mBottomView);

            mCancelBtn = view.findViewById(R.id.mCancelBtn);
            mConfirmBtn = view.findViewById(R.id.mConfirmBtn);
            mPayBtn = view.findViewById(R.id.mPayBtn);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(mItemClickListener!=null){
                mItemClickListener.onItemClick(dataList.get(getAdapterPosition()),getAdapterPosition());
            }
        }
    }

    public interface OnOptClickListener {
        void onOptClick(int optType,Orders orders);
    }
}
