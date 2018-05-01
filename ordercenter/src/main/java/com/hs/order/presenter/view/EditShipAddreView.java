package com.hs.order.presenter.view;



import com.hs.base.presenter.view.BaseView;


public interface EditShipAddreView extends BaseView{
    //添加收货人回调
    void onAddShipAddreResult(Boolean result);

    //修改收货人回调
    void onEditShipAddreResult(Boolean result);
}
