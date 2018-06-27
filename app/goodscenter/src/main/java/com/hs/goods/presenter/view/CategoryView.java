package com.hs.goods.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.goods.model.category.CateGoryp;
import com.hs.goods.model.category.CateGorys;
import java.util.List;

/**
 * Created by rnd on 2018/4/12.
 *
 */

public interface CategoryView extends BaseView{

    void onGetCategorypResult(List<CateGoryp> result);

    void onGetCategorysResult(List<CateGorys> result);

}
