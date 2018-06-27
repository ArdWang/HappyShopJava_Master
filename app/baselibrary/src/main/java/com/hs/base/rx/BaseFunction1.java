package com.hs.base.rx;


import android.util.Log;

import io.reactivex.functions.Function;

/**
 公共数据类型转换
 */
public class BaseFunction1<T> implements Function<T,T> {
    @Override
    public T apply(T t) throws Exception {
        Log.i("TAG is Message:",t.toString());
        return t;
    }
}

