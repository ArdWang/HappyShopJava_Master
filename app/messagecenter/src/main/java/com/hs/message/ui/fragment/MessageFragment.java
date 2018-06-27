package com.hs.message.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.fragment.BaseMvpFragment;
import com.hs.base.utils.DividerItemDecoration;
import com.hs.message.R;
import com.hs.message.model.MsgInfo;
import com.hs.message.presenter.MessagePresenter;
import com.hs.message.presenter.view.MessageView;
import com.hs.message.ui.adapter.MessageAdapter;
import com.hs.provider.event.MessageBadgeEvent;
import com.kennyc.view.MultiStateView;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends BaseMvpFragment<MessagePresenter> implements MessageView
        ,MessageAdapter.OnDragClickListener,OnRefreshListener,
        OnLoadMoreListener {

    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private MultiStateView mMultiStateView;
    private MessageAdapter mAdapter;

    private int pageIndex=1;

    private int pageSize=10;

    private List<MsgInfo> msgInfos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_message,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }


    private void initView(View view) {
        msgInfos = new ArrayList<>();
        mMultiStateView = view.findViewById(R.id.mMultiStateView);
        swipeToLoadLayout = view.findViewById(R.id.swipeToLoadLayout);
        recyclerView = view.findViewById(R.id.swipe_target);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new MessageAdapter(getContext(),this);
        recyclerView.setAdapter(mAdapter);

        swipeToLoadLayout.setRefreshHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_refresh_header, swipeToLoadLayout, false));
        /*设置上拉加载更多布局*/
        swipeToLoadLayout.setLoadMoreFooterView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_refresh_footer, swipeToLoadLayout, false));
        // 设置监听器
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        mMultiStateView.getView(MultiStateView.VIEW_STATE_EMPTY).findViewById(R.id.mLoading)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadData();
                    }
                });
    }

    private void loadData(){
        CommonExt.startMultiLoading(mMultiStateView);
        mPresenter.getMessage(pageIndex,pageSize,this);
    }

    @Override
    public void onEditClick(View view, int pos) {
        if(msgInfos.size()>0){
            MsgInfo msgInfo = msgInfos.get(pos);
            //传入1表示读取
            mPresenter.updateMessage(msgInfo.getMsgid(),1,this);
        }
    }

    @Override
    public void onDeleteClick(View view, final int pos) {
        new AlertView("删除消息", "", "取消", null,
                new String[]{"删除"}, getActivity(), AlertView.Style.ActionSheet, new OnItemClickListener(){
            public void onItemClick(Object o,int position){
               if(position==0){
                   onDeleteMsg(pos);
               }
            }
        }).show();
    }

    public void onDeleteMsg(int pos){
        if(msgInfos.size()>0) {
            MsgInfo msgInfo = msgInfos.get(pos);
            mPresenter.deleteMessage(msgInfo.getMsgid(),this);
        }
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //页数
                //数据
                pageSize = pageSize+5;
                loadData();
            }
        },2000);
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        },2000);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            EventBus.getDefault().post(new MessageBadgeEvent(false));
        }
    }

    @Override
    public void onGetMessageInfo(List<MsgInfo> result) {
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
        if(result!=null&&result.size()>0) {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
            msgInfos = result;
            mAdapter.setData(msgInfos);
        }else{
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        }
    }

    @Override
    public void onUpdateMessage(Boolean result) {
        loadData();
    }

    @Override
    public void onDeleteMessage(Boolean result) {
        CommonExt.toast("删除消息成功");
        loadData();
    }
}
