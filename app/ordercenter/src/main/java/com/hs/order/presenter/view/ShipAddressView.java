package com.hs.order.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.order.model.ShipAddre;
import java.util.List;

/**
 * Created by rnd on 2018/4/20.
 *
 */

public interface ShipAddressView extends BaseView{

    //获取收货人列表回调
    void onGetShipAddressResult(List<ShipAddre> result);

    void onSetDefaultAddreResult(Boolean result);

    void onDeleteShipAddreResult(Boolean result);

}
