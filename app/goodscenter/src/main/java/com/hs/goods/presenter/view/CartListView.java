package com.hs.goods.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.goods.model.cart.CartGoods;
import java.util.List;

/**
 * Created by rnd on 2018/4/12.
 *
 */

public interface CartListView extends BaseView{

    void onGetCartListResult(List<CartGoods> result);

    void onDeleteCartListResult(Boolean result);

    void onSubmitCartResult(Integer result);
}
