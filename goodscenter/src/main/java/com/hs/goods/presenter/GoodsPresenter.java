package com.hs.goods.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.goods.data.net.repository.GoodsRepository;
import com.hs.goods.model.goods.GoodsInfo;
import com.hs.goods.presenter.view.GoodsView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import java.util.List;


/**
 * Created by Administrator on 2018/4/14.
 *
 */

public class GoodsPresenter extends BasePresenter<GoodsView>{


    private GoodsRepository goodsRepository;



    /**
     * 获取商品列表
     */
    public void getGoodsList(int categorypid,int categorysid,int page,int pagesize,LifecycleProvider<ActivityEvent> lifeProvider){
        goodsRepository = new GoodsRepository();
        //检查网络是否可以使用
        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        goodsRepository.getGoodsInfo(categorypid,categorysid,page,pagesize,lifeProvider).subscribe(new BaseObserver<List<GoodsInfo>>(mView){
            @Override
            public void onNext(List<GoodsInfo> goodsInfos) {
                mView.onGetGoodsResult(goodsInfos);
            }
        });
    }

    /**
     * 查询keyboard
     */
    public void getGoodsListByKeyWord(String keyword,int page,int pagesize,LifecycleProvider<ActivityEvent> lifeProvider){
        goodsRepository = new GoodsRepository();
        //检查网络是否可以使用
        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        goodsRepository.getGoodsKeyWord(page,pagesize,keyword,lifeProvider).subscribe(new BaseObserver<List<GoodsInfo>>(mView){
            @Override
            public void onNext(List<GoodsInfo> goodsInfos) {
                mView.onGetGoodsResult(goodsInfos);
            }
        });
    }


}
