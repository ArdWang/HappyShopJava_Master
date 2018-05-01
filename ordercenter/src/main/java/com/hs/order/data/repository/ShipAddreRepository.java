package com.hs.order.data.repository;

import com.hs.base.data.net.http.RetrofitFactory;
import com.hs.base.data.net.repository.BaseRepository;
import com.hs.base.rx.BaseFunction1;
import com.hs.base.rx.BaseFunctionBoolean;
import com.hs.order.data.api.ShipApi;
import com.hs.order.data.protocol.AddShipAddreReq;
import com.hs.order.data.protocol.DeleteShipAddreReq;
import com.hs.order.data.protocol.EditShipAddreReq;
import com.hs.order.model.ShipAddre;
import com.hs.order.model.ShipAddreResp;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.Observable;


public class ShipAddreRepository extends BaseRepository{

    private ShipApi shipApi;
    public ShipAddreRepository(){
        shipApi = RetrofitFactory.getInstance().create(ShipApi.class);
    }

    /**
         添加收货人地址
     */
    public Observable<Boolean> addShipAddre(String shipName, String shipPhone, String shipAddre, LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(shipApi
                .addShipAddre(new AddShipAddreReq(shipName,shipPhone,shipAddre)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }

    /**
        得到收获信息
     */
    public Observable<ShipAddreResp> getShipList(LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(shipApi.getShipList(),lifeProvider).map(new BaseFunction1<ShipAddreResp>());
    }

    /**
        编辑收获信息
     */
    public Observable<Boolean> editShipAddre(ShipAddre shipAddre, LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(RetrofitFactory.getInstance().create(ShipApi.class)
                .editShipAddre(new EditShipAddreReq(shipAddre)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }

    /**
        删除收货信息
     */
    public Observable<Boolean> deleteShipAddre(Integer shipId, LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(shipApi
                .deleteShipAddre(new DeleteShipAddreReq(shipId)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }

}
