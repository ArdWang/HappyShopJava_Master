package com.hs.goods.data.net.repository;

import com.hs.base.data.net.http.RetrofitFactory;
import com.hs.base.data.net.repository.BaseRepository;
import com.hs.base.rx.BaseFunction;
import com.hs.goods.data.net.api.GoodsApi;
import com.hs.goods.data.net.protocol.GetGoodsDetailReq;
import com.hs.goods.data.net.protocol.GetGoodsInfoReq;
import com.hs.goods.data.net.protocol.GetGoodsKeyWordReq;
import com.hs.goods.model.goods.GoodsInfo;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import java.util.List;
import io.reactivex.Observable;


public class GoodsRepository extends BaseRepository{
    private GoodsApi goodsApi;

    public GoodsRepository(){
        goodsApi = RetrofitFactory.getInstance().create(GoodsApi.class);
    }

    public Observable<List<GoodsInfo>> getGoodsInfo(Integer categorypid, Integer categorysid,
                                                    Integer pageIndex, Integer pageSize,
                                                    LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(goodsApi.getGoodsInfo(new GetGoodsInfoReq(categorypid,categorysid,pageIndex,pageSize)),lifeProvider)
                .flatMap(new BaseFunction<List<GoodsInfo>>());
    }


    public Observable<List<GoodsInfo>> getGoodsKeyWord(Integer pageIndex,
                                                       Integer pageSize, String keyWord,
                                                       LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(goodsApi.getGoodsKeyWord(new GetGoodsKeyWordReq(pageIndex,pageSize,keyWord)),lifeProvider)
                .flatMap(new BaseFunction<List<GoodsInfo>>());
    }


    public Observable<GoodsInfo> getGoodsDetail(Integer goodsId,LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(goodsApi.getGoodsDetail(new GetGoodsDetailReq(goodsId)),lifeProvider)
                .flatMap(new BaseFunction<GoodsInfo>());
    }

}
