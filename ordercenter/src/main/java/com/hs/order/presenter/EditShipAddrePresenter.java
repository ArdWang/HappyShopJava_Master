package com.hs.order.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.order.data.repository.ShipAddreRepository;
import com.hs.order.model.ShipAddre;
import com.hs.order.presenter.view.EditShipAddreView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;


public class EditShipAddrePresenter extends BasePresenter<EditShipAddreView>{

    private ShipAddreRepository shipAddreRepository;

    public void addShipAddre(String shipName,String shipPhone,String shipAddre,LifecycleProvider<ActivityEvent> lifeProvider){
        shipAddreRepository = new ShipAddreRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        shipAddreRepository.addShipAddre(shipName,shipPhone,shipAddre,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
               mView.onAddShipAddreResult(aBoolean);
            }
        });
    }

    public void editShipAddre(ShipAddre shipAddre, LifecycleProvider<ActivityEvent> lifeProvider){
        shipAddreRepository = new ShipAddreRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        shipAddreRepository.editShipAddre(shipAddre,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onEditShipAddreResult(aBoolean);
            }
        });

    }



}
