package com.hs.goods.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.base.utils.GlideUtils;
import com.hs.base.utils.YuanFenConverter;
import com.hs.goods.R;
import com.hs.goods.event.CartAllCheckedEvent;
import com.hs.goods.event.UpdateTotalPriceEvent;
import com.hs.goods.model.cart.CartGoods;
import org.greenrobot.eventbus.EventBus;
import ren.qinc.numberbutton.NumberButton;

/**
 * Created by rnd on 2018/4/17.
 *
 */

public class CartGoodsAdapter extends BaseRecyclerViewAdapter<CartGoods,CartGoodsAdapter.ViewHolder>{

    private boolean isAllChecked;

    public CartGoodsAdapter(Context mContext) {
        super(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_cart_goods_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try{
            final CartGoods cartGoods = dataList.get(position);
            //是否选中
            holder.mCheckedCb.setChecked(cartGoods.getSelected());
            //加载图片
            GlideUtils.loadUrlImage(mContext,cartGoods.getGoodsicon(),holder.mGoodsIconIv);
            holder.mGoodsDescTv.setText(cartGoods.getGoodsdesc());
            holder.mGoodsSkuTv.setText(cartGoods.getGoodssku());
            holder.mGoodsPriceTv.setText("¥ "+YuanFenConverter.changeY2FSG(cartGoods.getGoodsprice()));
            holder.mGoodsCountBtn.setCurrentNumber(cartGoods.getGoodscount());

            holder.mCheckedCb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartGoods.setSelected(holder.mCheckedCb.isChecked());
                    isAllChecked = isChecked();
                    EventBus.getDefault().post(new CartAllCheckedEvent(isAllChecked));
                    notifyDataSetChanged();
                }
            });

            EditText editText = holder.mGoodsCountBtn.findViewById(R.id.text_count);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   cartGoods.setGoodscount(Integer.parseInt(charSequence.toString()));
                   EventBus.getDefault().post(new UpdateTotalPriceEvent());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 是否全部选中按钮
     * @return
     */
    private boolean isChecked(){
        for(CartGoods cg:dataList) {
            //当没有全选中时候从这里面返回值
            if(!cg.getSelected()) {
                return cg.getSelected();
            }
        }
        return true;
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CheckBox mCheckedCb;
        ImageView mGoodsIconIv;
        TextView mGoodsDescTv;
        TextView mGoodsSkuTv;
        TextView mGoodsPriceTv;
        NumberButton mGoodsCountBtn;

        public ViewHolder(View view){
            super(view);
            mCheckedCb = view.findViewById(R.id.mCheckedCb);
            mGoodsIconIv = view.findViewById(R.id.mGoodsIconIv);
            mGoodsDescTv = view.findViewById(R.id.mGoodsDescTv);
            mGoodsSkuTv = view.findViewById(R.id.mGoodsSkuTv);
            mGoodsPriceTv = view.findViewById(R.id.mGoodsPriceTv);
            mGoodsCountBtn = view.findViewById(R.id.mGoodsCountBtn);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
