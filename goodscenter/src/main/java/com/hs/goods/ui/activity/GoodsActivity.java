package com.hs.goods.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hs.base.ui.activity.BaseMvpActivity;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.goods.R;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.model.goods.GoodsInfo;
import com.hs.goods.presenter.GoodsPresenter;
import com.hs.goods.presenter.view.GoodsView;
import com.hs.goods.ui.adapter.GoodsAdapter;
import com.kennyc.view.MultiStateView;
import java.util.List;


/**
 * Created by Administrator on 2018/4/14.
 * 商品Activity
 */

public class GoodsActivity extends BaseMvpActivity<GoodsPresenter> implements GoodsView,OnRefreshListener,
        OnLoadMoreListener {

    private MultiStateView mMultiStateView;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private RecyclerView mGoodsRv;
    private GoodsAdapter goodsAdapter;
    private int currentPage=1;
    private int pageSize=6;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRefreshLayout();
        loadingData();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_goods;
    }

    @Override
    protected void initView() {
        mMultiStateView = findViewById(R.id.mMultiStateView);
        mSwipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);
        mGoodsRv = findViewById(R.id.swipe_target);

        mGoodsRv.setLayoutManager(new GridLayoutManager(this,2));
        goodsAdapter = new GoodsAdapter(this);
        mGoodsRv.setAdapter(goodsAdapter);

        goodsAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<GoodsInfo>() {
            @Override
            public void onItemClick(GoodsInfo item, int position) {
                Intent intent = new Intent(GoodsActivity.this,GoodsDetailActivity.class);
                intent.putExtra(GoodsConstant.KEY_GOODS_ID,item.getGoodsid());
                startActivity(intent);
            }
        });

    }

    private void initRefreshLayout() {
        mSwipeToLoadLayout.setRefreshHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_refresh_header, mSwipeToLoadLayout, false));
        /*设置上拉加载更多布局*/
        mSwipeToLoadLayout.setLoadMoreFooterView(LayoutInflater.from(this).inflate(R.layout.layout_refresh_footer, mSwipeToLoadLayout, false));
        // 设置监听器
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);//上拉;
    }

    private void loadingData(){
        Intent intent = getIntent();
        if(intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE,0)!=0){
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
            String keyword = intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD);
            mPresenter.getGoodsListByKeyWord(keyword,currentPage,pageSize,this);
        }else{
            int categorppid = intent.getIntExtra(GoodsConstant.KEY_CATEGORYP_ID,0);
            int categorysid = intent.getIntExtra(GoodsConstant.KEY_CATEGORYS_ID,0);
            //mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
            //四个参数
            mPresenter.getGoodsList(categorppid,categorysid,currentPage,pageSize,this);
        }

    }


    @Override
    public void onLoadMore() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //页数
                //数据
                pageSize = pageSize+6;
                loadingData();
            }
        },2000);
    }

    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingData();
            }
        },2000);
    }

    @Override
    public void onGetGoodsResult(List<GoodsInfo> result) {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        mSwipeToLoadLayout.setRefreshing(false);
        mSwipeToLoadLayout.setLoadingMore(false);
        if(result.size()>0&&result!=null) {
            //if(currentPage==1){
            goodsAdapter.setData(result);
            //}else{
            // goodsAdapter.dataList.addAll(result);
            goodsAdapter.notifyDataSetChanged();
            //}
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        }else{
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        }
    }
}
