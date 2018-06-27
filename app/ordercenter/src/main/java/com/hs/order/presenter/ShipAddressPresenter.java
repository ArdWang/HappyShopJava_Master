package com.hs.order.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.order.data.repository.ShipAddreRepository;
import com.hs.order.model.ShipAddre;
import com.hs.order.model.ShipAddreResp;
import com.hs.order.presenter.view.ShipAddressView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import java.util.List;

/**
 * Created by rnd on 2018/4/20.
 *
 */

public class ShipAddressPresenter extends BasePresenter<ShipAddressView>{

    private ShipAddreRepository shipAddreRepository;

    public void getShipList(LifecycleProvider<ActivityEvent> lifeProvider){
        shipAddreRepository = new ShipAddreRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        shipAddreRepository.getShipList(lifeProvider).subscribe(new BaseObserver<ShipAddreResp>(mView){
            @Override
            public void onNext(ShipAddreResp shipAddreResp) {
                mView.onGetShipAddressResult(shipAddreResp.getShipAddres());
            }
        });

    }

    public void setDefaultShipAddre(ShipAddre shipAddre, LifecycleProvider<ActivityEvent> lifeProvider){
        shipAddreRepository = new ShipAddreRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        shipAddreRepository.editShipAddre(shipAddre,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onSetDefaultAddreResult(aBoolean);
            }
        });
    }

    public void deleteShipAddre(Integer shipId,LifecycleProvider<ActivityEvent> lifeProvider){
        shipAddreRepository = new ShipAddreRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        shipAddreRepository.deleteShipAddre(shipId,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onDeleteShipAddreResult(aBoolean);
            }
        });
    }


}
