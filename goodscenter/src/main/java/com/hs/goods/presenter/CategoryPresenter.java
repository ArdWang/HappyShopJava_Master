package com.hs.goods.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.goods.data.net.repository.CateGoryRepository;
import com.hs.goods.model.category.CateGoryp;
import com.hs.goods.model.category.CateGorys;
import com.hs.goods.model.category.GateGorysResp;
import com.hs.goods.presenter.view.CategoryView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import java.util.List;


/**
 * Created by rnd on 2018/4/12.
 *
 */

public class CategoryPresenter extends BasePresenter<CategoryView>{

    private CateGoryRepository cateGoryRepository;

    /**
     * 获取父类的
     */
    public void getCatepgory(LifecycleProvider<FragmentEvent> lifeProvider){
        cateGoryRepository = new CateGoryRepository();
        //检查网络是否可以使用
        if (!checkNetWork()) {
            return;
        }

        cateGoryRepository.getCatepGory(lifeProvider).subscribe(new BaseObserver<List<CateGoryp>>(mView){
            @Override
            public void onNext(List<CateGoryp> cateGoryps) {
                mView.onGetCategorypResult(cateGoryps);
            }
        });
    }

    public void getCatesgory(Integer categoryId,LifecycleProvider<FragmentEvent> lifeProvider){
        cateGoryRepository = new CateGoryRepository();
        if(!checkNetWork()){
            return;
        }

        cateGoryRepository.getCatesGory(categoryId,lifeProvider).subscribe(new BaseObserver<GateGorysResp>(mView){
            @Override
            public void onNext(GateGorysResp gateGorysResp) {
               mView.onGetCategorysResult(gateGorysResp.getCateGorys());
            }
        });
    }






}
