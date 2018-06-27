package com.hs.goods.presenter;



import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.goods.data.net.repository.CartRepository;
import com.hs.goods.model.cart.CartGoods;
import com.hs.goods.model.cart.CartResp;
import com.hs.goods.presenter.view.CartListView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import java.util.List;


/**
 * Created by rnd on 2018/4/12.
 *
 */

public class CartListPresenter extends BasePresenter<CartListView>{

    private CartRepository cartRepository;

    /**
     * 获取购物车数据
     * @param lifecycleProvider
     */
    public void getCart(LifecycleProvider<FragmentEvent> lifecycleProvider){
        cartRepository = new CartRepository();

        //检查网络是否可以使用
        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        cartRepository.getCart(lifecycleProvider).subscribe(new BaseObserver<CartResp>(mView){
            @Override
            public void onNext(CartResp cartResp) {
                if(!cartResp.getCode().equals("0")){
                    mView.onGetCartListResult(cartResp.getCartGoods());
                }
            }
        });
    }


    /**
     * 删除数据 服务器为Body请求不能带参数请求
     * @param cartIdList
     * @param lifecycleProvider
     */
    public void deleteCart(List<Integer> cartIdList,LifecycleProvider<FragmentEvent> lifecycleProvider){
        cartRepository = new CartRepository();
        //检查网络是否可以使用
        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        cartRepository.deleteCart(cartIdList,lifecycleProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onDeleteCartListResult(aBoolean);
            }
        });
    }

    /**
     * 提交商品数据 服务器为Body请求不能带参数请求
     */
    public void submitCart(List<CartGoods> cartGoods, float mTotalPrice,LifecycleProvider<FragmentEvent> lifeProvider){
        cartRepository = new CartRepository();
        //检查网络是否可以使用
        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        cartRepository.submitCart(cartGoods,mTotalPrice,lifeProvider).subscribe(new BaseObserver<Integer>(mView){
            @Override
            public void onNext(Integer integer) {
                mView.onSubmitCartResult(integer);
            }
        });
    }




}
