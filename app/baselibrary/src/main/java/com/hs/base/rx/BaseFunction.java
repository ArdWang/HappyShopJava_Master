package com.hs.base.rx;


import com.hs.base.data.net.protocol.BaseResp;
import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
    公共数据类型转换
 */
public class BaseFunction<T> implements Function<BaseResp<T>, Observable<T>> {

    @Override
    public Observable<T> apply(BaseResp<T> t) throws Exception {
        if(!t.getCode().equals("200")){
            return Observable.error(new BaseException(t.getCode(),t.getMessage()));
        }
        return Observable.just(t.getData());
    }
}

/*
 @Override
    public Observable<T> apply(BaseResp<T> t) throws Exception {
        if(t.getCode().equals("0")){
            return Observable.error(new BaseException(t.getCode(),t.getMessage()));
        }
        return Observable.just(t.getData());
    }
 */