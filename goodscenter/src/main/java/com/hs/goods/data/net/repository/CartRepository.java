package com.hs.goods.data.net.repository;

import com.hs.base.data.net.http.RetrofitFactory;
import com.hs.base.data.net.protocol.BaseResp;
import com.hs.base.data.net.repository.BaseRepository;
import com.hs.base.rx.BaseFunction;
import com.hs.base.rx.BaseFunction1;
import com.hs.base.rx.BaseFunctionBoolean;
import com.hs.goods.data.net.api.CartApi;
import com.hs.goods.data.net.protocol.AddCartReq;
import com.hs.goods.data.net.protocol.DeleteCartReq;
import com.hs.goods.data.net.protocol.GetCartReq;
import com.hs.goods.data.net.protocol.SubmitCartReq;
import com.hs.goods.model.cart.CartGoods;
import com.hs.goods.model.cart.CartResp;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by rnd on 2018/4/18.
 * V1.0.0
 * 购物车的 Repository
 */

public class CartRepository extends BaseRepository{

    private CartApi cartApi;

    public CartRepository(){
        cartApi = RetrofitFactory.getInstance().create(CartApi.class);
    }


    /**
     * 得到购物车的商品
     * @param lifeProvider
     */
    public Observable<CartResp> getCart(LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(cartApi.getCart(new GetCartReq()),lifeProvider)
                .map(new BaseFunction1<CartResp>());
    }


    /**
     * 添加购物车的商品
     * @param lifeProvider
     * @return
     */
    public Observable<Integer> addCart(Integer goodsid,String goodsdesc,
                                         String goodsicon,Float goodsprice,
                                         Integer goodscount,String goodssku,
                                         LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(RetrofitFactory.getInstance()
                .create(CartApi.class)
                .addCart(new AddCartReq(goodsid,goodsdesc,goodsicon,goodsprice,goodscount,goodssku)),lifeProvider)
                .flatMap(new BaseFunction<Integer>());
    }


    /**
     * 删除购物车的商品
     * @return
     */
    public Observable<Boolean> deleteCart(List<Integer> cartIdList,LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(RetrofitFactory.getInstance().create(CartApi.class)
                .deleteCart(new DeleteCartReq(cartIdList)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }


    /**
     * 提交购物车的商品
     * @return
     */
    public Observable<Integer> submitCart(List<CartGoods> cartGoods, float mTotalPrice, LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(RetrofitFactory.getInstance().create(CartApi.class)
                .submitCart(new SubmitCartReq(cartGoods,mTotalPrice)),lifeProvider)
                .flatMap(new BaseFunction<Integer>());
    }



}
