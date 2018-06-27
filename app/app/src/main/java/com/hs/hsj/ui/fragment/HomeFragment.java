package com.hs.hsj.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.base.widgets.BannerImageLoader;
import com.hs.goods.ui.activity.SearchGoodsActivity;
import com.hs.hsj.R;
import com.hs.base.ui.fragment.BaseFragment;
import com.hs.hsj.ext.AppExt;
import com.hs.hsj.ui.adapter.HomeDiscountAdapter;
import com.hs.hsj.ui.adapter.TopicAdapter;
import com.hs.hsj.utils.CommonUtil;
import com.hs.hsj.zxing.activity.CaptureActivity;
import com.hs.message.widgets.NewsFlipperView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import me.crosswall.lib.coverflow.CoverFlow;

/**
 * Created by rnd on 2018/4/12.
 * 主界面的fragment
 */

    public class HomeFragment extends BaseFragment implements TopicAdapter.OnItemClick,View.OnClickListener{
    private Banner mHomeBanner;
    private NewsFlipperView mNewsFlipperView;
    private RecyclerView mHomeDiscountRv;
    private ViewPager mTopicPager;
    private EditText mSearchEt;
    private ImageView mScanIv;
    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner();
        initNews();
        initDiscount();
        initTopic();
    }


    /**
     * 创建view
     * @param view
     */
    private void initView(View view) {
        mHomeBanner = view.findViewById(R.id.mHomeBanner);
        mNewsFlipperView = view.findViewById(R.id.mNewsFlipperView);
        mHomeDiscountRv = view.findViewById(R.id.mHomeDiscountRv);
        mTopicPager = view.findViewById(R.id.mTopicPager);
        mScanIv = view.findViewById(R.id.mScanIv);
        mSearchEt = view.findViewById(R.id.mSearchEt);

        mSearchEt.setOnClickListener(this);
        mScanIv.setOnClickListener(this);

        AppExt.getHomeList();

    }

    /**
        初始化Banner
     */
    private void initBanner() {
        //设置banner样式
        //mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mHomeBanner.setImageLoader(new BannerImageLoader());
        mHomeBanner.setImages(AppExt.imgUrlList);
        //设置标题集合（当banner样式有显示title时）
        //mHomeBanner.setBannerTitles(AppExt.titles);
        mHomeBanner.setBannerAnimation(Transformer.Accordion);
        //设置自动轮播，默认为true
        //mHomeBanner.isAutoPlay(true);
        //设置轮播时间
        mHomeBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mHomeBanner.start();
    }

    /**
       初始化公告
    */
    private void initNews(){
        //公告
        mNewsFlipperView.setData(AppExt.hotsList);
    }

    /**
       初始化折扣
    */
    private void initDiscount(){
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHomeDiscountRv.setLayoutManager(manager);

        HomeDiscountAdapter discountAdapter  =new  HomeDiscountAdapter(getActivity());
        mHomeDiscountRv.setAdapter(discountAdapter);
        discountAdapter.setData(AppExt.discList);

        discountAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String item, int position) {
                CommonExt.toast("点击了"+position);
            }
        });
    }

    /*
        初始化主题
     */
    private void initTopic(){
        //话题
        TopicAdapter mTopicAdapter =new TopicAdapter(getActivity(),AppExt.topcList,this);
        mTopicPager.setAdapter(mTopicAdapter);
        mTopicPager.setCurrentItem(1);
        mTopicPager.setOffscreenPageLimit(5);
        new CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f).build();
    }


    @Override
    public void onItemClick(View view, int pos) {
        CommonExt.toast("点击了"+pos);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.mSearchEt:
                intent = new Intent(getActivity(), SearchGoodsActivity.class);
                startActivity(intent);
                break;
            case R.id.mScanIv:
                CommonExt.toast("扫描");
                //打开二维码扫描界面
                if(CommonUtil.isCameraCanUse()){
                    intent = new Intent(getActivity(), CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }else{
                    Toast.makeText(getContext(),"请打开此应用的摄像头权限！",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            //将扫描出的信息显示出来
            CommonExt.toast(scanResult);
        }
    }
}
