package com.hs.goods.data.net.repository;


import com.hs.base.data.net.http.RetrofitFactory;
import com.hs.base.data.net.repository.BaseRepository;
import com.hs.base.rx.BaseFunction;
import com.hs.base.rx.BaseFunction1;
import com.hs.goods.data.net.api.CateGoryApi;
import com.hs.goods.data.net.protocol.GetCateGoryReq;
import com.hs.goods.data.net.protocol.GetCateGorysReq;
import com.hs.goods.model.category.CateGoryp;
import com.hs.goods.model.category.CateGorys;
import com.hs.goods.model.category.GateGorysResp;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import java.util.List;
import io.reactivex.Observable;


public class CateGoryRepository extends BaseRepository{

    private CateGoryApi cateGoryApi;

    public CateGoryRepository(){
        cateGoryApi = RetrofitFactory.getInstance().create(CateGoryApi.class);
    }

    /**
       得到分类
     */
    public Observable<List<CateGoryp>> getCatepGory(LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(cateGoryApi.getCatepGory(new GetCateGoryReq())
                ,lifeProvider).flatMap(new BaseFunction<List<CateGoryp>>());
    }

    /**
        得到子类
     */
    public Observable<GateGorysResp> getCatesGory(Integer categoryId, LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(cateGoryApi.getCatesGory(new GetCateGorysReq(categoryId))
                ,lifeProvider).map(new BaseFunction1<GateGorysResp>());
    }



}
