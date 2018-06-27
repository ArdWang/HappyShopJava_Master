package com.hs.goods.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseActivity;
import com.hs.base.ui.adapter.BaseRecyclerViewAdapter;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.goods.R;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.ui.adapter.SearchHistoryAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/15.
 *
 */

public class SearchGoodsActivity extends BaseActivity implements View.OnClickListener {

    private SearchHistoryAdapter searchHistoryAdapter;
    private ImageView mLeftIv;
    private EditText mKeywordEt;
    private TextView mSearchTv;
    private TextView mNoDataTv;
    private LinearLayout mDataView;
    private RecyclerView mSearchHistoryRv;
    private Button mClearBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    private void initView() {
        mLeftIv = findViewById(R.id.mLeftIv);
        mKeywordEt = findViewById(R.id.mKeywordEt);
        mSearchTv = findViewById(R.id.mSearchTv);
        mNoDataTv = findViewById(R.id.mNoDataTv);
        mDataView = findViewById(R.id.mDataView);
        mSearchHistoryRv = findViewById(R.id.mSearchHistoryRv);
        mClearBtn = findViewById(R.id.mClearBtn);

        mClearBtn.setOnClickListener(this);
        mSearchTv.setOnClickListener(this);
        mLeftIv.setOnClickListener(this);

        //RecyclerView适配器
        searchHistoryAdapter =new SearchHistoryAdapter(this);
        mSearchHistoryRv.setLayoutManager(new LinearLayoutManager(this));
        mSearchHistoryRv.setAdapter(searchHistoryAdapter);
        //item点击事件

        searchHistoryAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String item, int position) {
                //输入不为空，进入商品列表
                Intent intent = new Intent(SearchGoodsActivity.this,GoodsActivity.class);
                intent.putExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE,GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD);
                intent.putExtra(GoodsConstant.KEY_GOODS_KEYWORD,item);
                startActivity(intent);
            }
        });
    }

    /**
     从SP中加载数据
     */
    private void loadData() {
        Set<String> set = AppPrefsUtils.getInstance().getStringSet(GoodsConstant.SP_SEARCH_HISTORY);
        CommonExt.setVisible(mNoDataTv,set.isEmpty());
        CommonExt.setVisible(mDataView,!set.isEmpty());
        if (!set.isEmpty()) {
            List<String> list = new ArrayList<>();
            list.addAll(set);
            searchHistoryAdapter.setData(list);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.mLeftIv) {
            finish();

        } else if (i == R.id.mSearchTv) {
            doSearch();

        } else if (i == R.id.mClearBtn) {
            AppPrefsUtils.getInstance().remove(GoodsConstant.SP_SEARCH_HISTORY);
            loadData();
        }
    }

    //搜索
    private void doSearch() {
        if (mKeywordEt.getText().toString().isEmpty()) {
            CommonExt.toast("请输入需要搜索的关键字");
        } else {
            Set<String> as =new HashSet<>();
            String inputValue = mKeywordEt.getText().toString().trim();
            as.add(inputValue);
            AppPrefsUtils.getInstance().putStringSet(GoodsConstant.SP_SEARCH_HISTORY, as);
            enterGoodsList(inputValue);
        }
    }

    /**
     进入商品列表界面
     */
    private void enterGoodsList(String value) {
        //输入不为空，进入商品列表
        Intent intent = new Intent(SearchGoodsActivity.this,GoodsActivity.class);
        intent.putExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE,GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD);
        intent.putExtra(GoodsConstant.KEY_GOODS_KEYWORD,value);
        startActivity(intent);
    }
}
