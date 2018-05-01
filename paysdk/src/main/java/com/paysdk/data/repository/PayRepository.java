package com.paysdk.data.repository;


import com.hs.base.data.net.http.RetrofitFactory;
import com.hs.base.data.net.repository.BaseRepository;
import com.hs.base.rx.BaseFunction;
import com.hs.base.rx.BaseFunctionBoolean;
import com.paysdk.data.api.PayApi;
import com.paysdk.data.protocol.GetPaySignReq;
import com.paysdk.data.protocol.PayOrderReq;
import com.paysdk.model.OrderResp;
import com.paysdk.model.PayObject;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.Observable;


public class PayRepository extends BaseRepository{

    private PayApi payApi;

    public PayRepository(){
        payApi = RetrofitFactory.getInstance().create(PayApi.class);
    }

    /**
     根据订单状态获取订单
     */
    public Observable<String> getPaySign(Integer orderId,Float totalPrice, LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(payApi.getPaySign(new GetPaySignReq(orderId,totalPrice)),lifeProvider)
                .flatMap(new BaseFunction<String>());
    }


    /**
        支付完成调用
     */
    public Observable<Boolean> payOrder(Integer orderId,LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(payApi.payOrder(new PayOrderReq(orderId)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }


}
