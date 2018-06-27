package com.hs.base.rx;

import com.hs.base.data.net.protocol.BaseResp;
import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * 通用类型转换封装 可以强转T类型变为 true或者false
 */

public class BaseFunctionBoolean<T> implements Function<BaseResp<T>,Observable<Boolean>> {
    @Override
    public Observable<Boolean> apply(BaseResp<T> t) throws Exception {
        if (!t.getCode().equals("200")){
            return Observable.error(new BaseException(t.getCode(), t.getMessage()));
        }
        return Observable.just(true);
    }
}
