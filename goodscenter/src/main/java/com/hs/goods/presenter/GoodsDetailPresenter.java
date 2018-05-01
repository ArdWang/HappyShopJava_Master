package com.hs.goods.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.goods.common.GoodsConstant;
import com.hs.goods.data.net.repository.CartRepository;
import com.hs.goods.data.net.repository.GoodsRepository;
import com.hs.goods.model.goods.GoodsInfo;
import com.hs.goods.presenter.view.GoodsDetailView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;


/**
 * Created by Administrator on 2018/4/15.
 *
 */

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailView>{

    //private GoodsLoader goodsLoader;

    private CartRepository cartRepository;

    private GoodsRepository goodsRepository;




    public void getGoodsDateilList(int goodsId,LifecycleProvider<FragmentEvent> lifeProvider){
        goodsRepository = new GoodsRepository();
        //检查网络是否可以使用
        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        goodsRepository.getGoodsDetail(goodsId,lifeProvider).subscribe(new BaseObserver<GoodsInfo>(mView){
            @Override
            public void onNext(GoodsInfo goodsInfo) {
                mView.onGetGoodsDetailResult(goodsInfo);
            }
        });
    }


    /**
     * 添加商品接口新的接口
     * @param goodsid
     * @param goodsdesc
     * @param goodsicon
     * @param goodsprice
     * @param goodscount
     * @param goodssku
     * @param lifecycleProvider
     */
    public void addCart(int goodsid,String goodsdesc,String goodsicon,Float goodsprice,int goodscount,
                        String goodssku,LifecycleProvider<FragmentEvent> lifecycleProvider){
        cartRepository = new CartRepository();
        //检查网络是否可以使用
        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        cartRepository.addCart(goodsid,goodsdesc,goodsicon,goodsprice,goodscount,goodssku,
                lifecycleProvider).subscribe(new BaseObserver<Integer>(mView){
            @Override
            public void onNext(Integer integer) {
                //存储返回的值
                AppPrefsUtils.getInstance().putInt(GoodsConstant.SP_CART_SIZE,integer);
                mView.onGetCartAddResult(integer);
            }
        });
    }


}
