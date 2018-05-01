package com.hs.goods.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.goods.model.goods.GoodsInfo;

/**
 * Created by Administrator on 2018/4/15.
 *
 */

public interface GoodsDetailView extends BaseView{
    //获取商品详情
    void onGetGoodsDetailResult(GoodsInfo goodsInfo);

    //添加商品
    void onGetCartAddResult(Integer result);
}
