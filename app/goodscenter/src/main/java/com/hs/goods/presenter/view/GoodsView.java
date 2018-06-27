package com.hs.goods.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.goods.model.goods.GoodsInfo;
import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 *
 */

public interface GoodsView extends BaseView {

    void onGetGoodsResult(List<GoodsInfo> result);

}
