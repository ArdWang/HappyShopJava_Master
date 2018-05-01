package com.hs.goods.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hs.base.ui.activity.BaseActivity;
import com.hs.base.ui.fragment.BaseMvpFragment;
import com.hs.base.utils.YuanFenConverter;
import com.hs.base.widgets.BannerImageLoader;
import com.hs.goods.R;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.event.AddCartEvent;
import com.hs.goods.event.GoodsDetailImageEvent;
import com.hs.goods.event.SkuChangedEvent;
import com.hs.goods.event.UpdateCartSizeEvent;
import com.hs.goods.model.goods.GoodsInfo;
import com.hs.goods.presenter.GoodsDetailPresenter;
import com.hs.goods.presenter.view.GoodsDetailView;
import com.hs.goods.widget.GoodsSkuPopView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class GoodsDetailTabOneFragment extends BaseMvpFragment<GoodsDetailPresenter> implements GoodsDetailView{

    private GoodsSkuPopView mSkuPop;
    //SKU弹层出场动画
    private Animation mAnimationStart;
    //SKU弹层退场动画
    private Animation mAnimationEnd;

    private Banner mGoodsDetailBanner;

    private RelativeLayout mSkuView;

    private BaseActivity ba;

    private TextView mGoodsDescTv;
    private TextView mGoodsPriceTv;
    private TextView mSkuSelectedTv;

    private GoodsInfo ggl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
       return inflater.inflate(R.layout.fragment_goods_detail_tab_one,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ba = (BaseActivity)getActivity();
        EventBus.getDefault().register(this);
        initView(view);
        initAnim();
        initSkuPop();
        loadData();
    }

    private void initView(View view) {

        mGoodsDetailBanner = view.findViewById(R.id.mGoodsDetailBanner);
        mSkuView = view.findViewById(R.id.mSkuView);
        mGoodsDescTv = view.findViewById(R.id.mGoodsDescTv);
        mGoodsPriceTv = view.findViewById(R.id.mGoodsPriceTv);
        mSkuSelectedTv = view.findViewById(R.id.mSkuSelectedTv);

        mGoodsDetailBanner.setImageLoader(new BannerImageLoader());
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion);
        mGoodsDetailBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT);

        mSkuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSkuPop.showAtLocation(ba.getContentViews(), Gravity.BOTTOM,
                        0, 0);
                ba.getContentViews().startAnimation(mAnimationStart);
            }

        });

    }

    private void initSkuPop(){
        mSkuPop =new GoodsSkuPopView(getActivity());
        mSkuPop.getBackground().setAlpha(150);
        mSkuPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ba.getContentViews().startAnimation(mAnimationEnd);
            }
        });
    }

    /**
        初始化缩放动画
    */
    private void initAnim() {
        mAnimationStart =new ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimationStart.setDuration(500);
        mAnimationStart.setFillAfter(true);

        mAnimationEnd =new ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimationEnd.setDuration(500);
        mAnimationEnd.setFillAfter(true);
    }

    /**
        加载数据
     */
    private void loadData() {
        int goodsid = getActivity().getIntent().getIntExtra(GoodsConstant.KEY_GOODS_ID,-1);
        mPresenter.getGoodsDateilList(goodsid,this);
    }


    private void loadPopData(GoodsInfo ggl) {
        mSkuPop.setGoodsIcon(ggl.getGoodsdefaulticon());
        mSkuPop.setGoodsCode(ggl.getGoodscode());
        mSkuPop.setGoodsPrice(ggl.getGoodsdefaultprice());
        mSkuPop.setSkuData(ggl.getGoodsSkus());
    }


    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSkuChangedEvent(SkuChangedEvent event) {
        mSkuSelectedTv.setText(mSkuPop.getSelectSku() +GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount()+"件");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddCartEvent(AddCartEvent event){
        addCart();
    }

    /**
     * 加入到购物车
     */
    private void addCart() {
        try {
            //int userid = Integer.parseInt(AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_ID));
            int goodsid = ggl.getGoodsid();
            String goodsdesc = ggl.getGoodsdesc();
            String goodsicon = ggl.getGoodsdefaulticon();
            Float goodsprice = ggl.getGoodsdefaultprice();
            int goodscount = mSkuPop.getSelectCount();
            String sku = mSkuPop.getSelectSku();

            mPresenter.addCart(goodsid, goodsdesc, goodsicon, goodsprice, goodscount, sku, this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onGetGoodsDetailResult(GoodsInfo result) {
        try {
            ggl = result;
            List<String> a = new ArrayList<>();
            String b[] = ggl.getGoodsbanner().split(",");
            a.addAll(Arrays.asList(b));
            mGoodsDetailBanner.setImages(a);
            mGoodsDetailBanner.start();
            mGoodsDescTv.setText(ggl.getGoodsdesc());
            mGoodsPriceTv.setText("¥"+YuanFenConverter.changeY2FSG(ggl.getGoodsdefaultprice()));
            mSkuSelectedTv.setText(ggl.getGoodsdefaultsku());

            //EventBus 发送数据 到第二个界面
            EventBus.getDefault().post(new GoodsDetailImageEvent(ggl.getGoodsdetailone(),ggl.getGoodsdetailtwo()));

            loadPopData(ggl);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onGetCartAddResult(Integer result) {
        EventBus.getDefault().post(new UpdateCartSizeEvent());
    }

}
