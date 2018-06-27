package com.hs.goods.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.base.ui.fragment.BaseMvpFragment;
import com.hs.base.widgets.BannerImageLoader;
import com.hs.goods.R;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.model.category.CateGoryp;
import com.hs.goods.model.category.CateGorys;
import com.hs.goods.presenter.CategoryPresenter;
import com.hs.goods.presenter.view.CategoryView;
import com.hs.goods.ui.activity.GoodsActivity;
import com.hs.goods.ui.adapter.CategorypAdapter;
import com.hs.goods.ui.adapter.CategorysAdapter;
import com.kennyc.view.MultiStateView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import java.util.List;
import java.util.Objects;

/**
 * Created by rnd on 2018/4/12.
 *
 */

public class CategoryFragment extends BaseMvpFragment<CategoryPresenter> implements CategoryView{

    private CategorypAdapter categorypAdapter;
    private CategorysAdapter categorysAdapter;
    private RecyclerView mTopCategoryRv,mSecondCategoryRv;
    private MultiStateView mMultiStateView;
    private Banner mTopBanner;
    private int cateGorypid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_category,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initBanner();
        loadPData();
    }

    private void initView(View view) {
        mTopCategoryRv = view.findViewById(R.id.mTopCategoryRv);
        mSecondCategoryRv = view.findViewById(R.id.mSecondCategoryRv);
        mMultiStateView = view.findViewById(R.id.mMultiStateView);
        mTopBanner = view.findViewById(R.id.mTopBanner);
        mTopCategoryRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mSecondCategoryRv.setLayoutManager(new GridLayoutManager(getContext(),3));

        categorypAdapter = new CategorypAdapter(getContext());
        mTopCategoryRv.setAdapter(categorypAdapter);
        categorypAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<CateGoryp>() {
            @Override
            public void onItemClick(CateGoryp item, int position) {
                item = categorypAdapter.dataList.get(position);
                for(CateGoryp categoryp:categorypAdapter.dataList){
                    if(Objects.equals(categoryp.getCategorypid(), item.getCategorypid())){
                        categoryp.setSelected(true);
                    }else{
                        categoryp.setSelected(false);
                    }
                }

                categorypAdapter.notifyDataSetChanged();
                //得到购物车的ID
                cateGorypid = item.getCategorypid();
                loadSData(item.getCategorypid());
            }
        });

        categorysAdapter = new CategorysAdapter(getContext());
        mSecondCategoryRv.setAdapter(categorysAdapter);
        categorysAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<CateGorys>() {
            @Override
            public void onItemClick(CateGorys item, int position) {
                Intent intent = new Intent(getContext(), GoodsActivity.class);
                intent.putExtra(GoodsConstant.KEY_CATEGORYP_ID,item.getCategorypid());
                intent.putExtra(GoodsConstant.KEY_CATEGORYS_ID,item.getCategorysid());
                startActivity(intent);
            }
        });

        mMultiStateView.getView(MultiStateView.VIEW_STATE_EMPTY).findViewById(R.id.mLoading)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadSData(cateGorypid);
                    }
                });
    }

    /**
     初始化Banner
     */
    private void initBanner() {
        CommonExt.getHomeList();
        mTopBanner.setImageLoader(new BannerImageLoader());
        mTopBanner.setImages(CommonExt.imgUrlList);
        mTopBanner.setBannerAnimation(Transformer.Accordion);
        mTopBanner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        mTopBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mTopBanner.start();

        mTopBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                CommonExt.toast("点击Banner的位置为："+position);
            }
        });
    }



    private void loadPData(){
        mPresenter.getCatepgory(this);
    }

    private void loadSData(int categorypid){
        CommonExt.startMultiLoading(mMultiStateView);
        mPresenter.getCatesgory(categorypid,this);
    }



    @Override
    public void onGetCategorypResult(List<CateGoryp> result) {
        if(result.size()>0&&result!=null){
            result.get(1).setSelected(true);
            categorypAdapter.setData(result);
            mPresenter.getCatesgory(result.get(1).getCategorypid(),this);
        }else{
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        }
    }

    @Override
    public void onGetCategorysResult(List<CateGorys> result) {
        if(result!=null&&result.size()>0) {
            categorysAdapter.setData(result);
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        }else{
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        }
    }
}
