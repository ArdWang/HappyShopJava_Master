package com.hs.goods.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.fragment.BaseMvpFragment;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.base.utils.YuanFenConverter;
import com.hs.goods.R;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.event.CartAllCheckedEvent;
import com.hs.goods.event.UpdateCartSizeEvent;
import com.hs.goods.event.UpdateTotalPriceEvent;
import com.hs.goods.model.cart.CartGoods;
import com.hs.goods.presenter.CartListPresenter;
import com.hs.goods.presenter.view.CartListView;
import com.hs.goods.ui.adapter.CartGoodsAdapter;
import com.hs.provider.common.ProviderConstant;
import com.hs.provider.router.RouterPath;
import com.kennyc.view.MultiStateView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by rnd on 2018/4/12.
 *
 */

public class CartFragment extends BaseMvpFragment<CartListPresenter> implements CartListView,View.OnClickListener{

    private MultiStateView mMultiStateView;
    private CartGoodsAdapter cartGoodsAdapter;
    private RecyclerView mCartGoodsRv;
    private CheckBox mAllCheckedCb;
    List<CartGoods> data;
    private float mTotalPrice=0;
    private TextView mTotalPriceTv;
    private TextView mRightTv;
    private Button mSettleAccountsBtn;
    private Button mDeleteBtn;
    private ImageView mLeftIv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_cart,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EventBus.getDefault().register(this);

        initView(view);
    }

    private void initView(View view) {

        mCartGoodsRv = view.findViewById(R.id.mCartGoodsRv);
        mMultiStateView = view.findViewById(R.id.mMultiStateView);
        mAllCheckedCb = view.findViewById(R.id.mAllCheckedCb);
        mRightTv = view.findViewById(R.id.mRightTv);
        mTotalPriceTv = view.findViewById(R.id.mTotalPriceTv);
        mSettleAccountsBtn = view.findViewById(R.id.mSettleAccountsBtn);
        mDeleteBtn = view.findViewById(R.id.mDeleteBtn);
        mLeftIv = view.findViewById(R.id.mLeftIv);

        mCartGoodsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        cartGoodsAdapter = new CartGoodsAdapter(getContext());
        mCartGoodsRv.setAdapter(cartGoodsAdapter);

        mAllCheckedCb.setOnClickListener(this);
        mRightTv.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);
        mSettleAccountsBtn.setOnClickListener(this);

        mMultiStateView.getView(MultiStateView.VIEW_STATE_EMPTY).findViewById(R.id.mLoading)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadData();
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    private void loadData() {
        CommonExt.startMultiLoading(mMultiStateView);
        mPresenter.getCart(this);
    }


    @Override
    public void onGetCartListResult(List<CartGoods> result) {
        if (result != null && result.size() > 0) {
            data = result;
            cartGoodsAdapter.setData(result);
            mAllCheckedCb.setChecked(false);
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        }else{
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
            CommonExt.setVisible(mRightTv,false);
        }

        //本地存储并发送事件刷新UI
        if(result==null){
            AppPrefsUtils.getInstance().putInt(GoodsConstant.SP_CART_SIZE,0);
        }else {
            AppPrefsUtils.getInstance().putInt(GoodsConstant.SP_CART_SIZE, result.size());
        }
        EventBus.getDefault().post(new UpdateCartSizeEvent());

        //更新总价
        updateTotalPrice();
    }

    @Override
    public void onDeleteCartListResult(Boolean result) {
        CommonExt.toast("删除成功");
        refreshEditStatus();
        loadData();
    }

    @Override
    public void onSubmitCartResult(Integer result) {
        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
                .withInt(ProviderConstant.KEY_ORDER_ID,result)
                .navigation();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCartAllCheckedEvent(CartAllCheckedEvent event){
        mAllCheckedCb.setChecked(event.getAllChecked());
        updateTotalPrice();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateTotalPriceEvent(UpdateTotalPriceEvent event){
        updateTotalPrice();
    }

    /**
        刷新是否为编辑状态
     */
    private void refreshEditStatus() {
        boolean isEditStatus = Objects.equals(getString(R.string.common_edit), mRightTv.getText().toString());
        CommonExt.setVisible(mTotalPriceTv,!isEditStatus);
        CommonExt.setVisible(mSettleAccountsBtn,!isEditStatus);
        CommonExt.setVisible(mDeleteBtn,isEditStatus);

        if(isEditStatus){
            mRightTv.setText(getString(R.string.common_complete));
        }else{
            mRightTv.setText(getString((R.string.common_edit)));
        }
    }


    @SuppressLint("SetTextI18n")
    private void updateTotalPrice() {
        try {
            mTotalPrice = totalPrice();
            mTotalPriceTv.setText("合计 ¥ "+YuanFenConverter.changeY2FSG(mTotalPrice));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取选中的价格
     * @return
     */
    private float totalPrice(){
        float a;
        float sum = 0;
        if(cartGoodsAdapter.dataList!=null) {
            for (CartGoods ccbb : cartGoodsAdapter.dataList) {
                if (ccbb.getSelected()) {
                    a = ccbb.getGoodscount() * ccbb.getGoodsprice();
                    sum += a;
                }
            }
        }
        return sum;
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        //所有选中的
        if (i == R.id.mAllCheckedCb) {
            for (CartGoods cb : cartGoodsAdapter.dataList) {
                //checkbox点击可以取消和选中状态 依次去设置checkbox的值的选择
                cb.setSelected(mAllCheckedCb.isChecked());
            }
            cartGoodsAdapter.notifyDataSetChanged();
            updateTotalPrice();
        }

        //删除购物车操作
        else if(i==R.id.mRightTv){
           refreshEditStatus();
        }

        //删除的按钮
        else if(i==R.id.mDeleteBtn){
            List<Integer> cartidList = new ArrayList<>();
            for(CartGoods ccg:cartGoodsAdapter.dataList){
                if(ccg.getSelected()) {
                    cartidList.add(ccg.getCartid());
                }
            }

            if(cartidList.size()==0){
                CommonExt.toast("请选择需要删除的数据");
            }else{
                mPresenter.deleteCart(cartidList,this);
            }
        }


        //结算按钮
        else if(i==R.id.mSettleAccountsBtn){
            List<CartGoods> ccGbs = new ArrayList<>();
            for(CartGoods ccg:cartGoodsAdapter.dataList){
                if(ccg.getSelected()){
                    ccGbs.add(ccg);
                }
            }

            if(ccGbs.size()>0){
                mPresenter.submitCart(ccGbs,mTotalPrice,this);
            }else{
                CommonExt.toast("请选择需要提交商品的数据");
            }
        }
    }

    /**
     设置Back是否可见
     */
     public void setBackVisible(Boolean isVisible){
         CommonExt.setVisible(mLeftIv,isVisible);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
