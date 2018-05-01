package com.hs.base.rx;


import io.reactivex.functions.Function;

/**
 公共数据类型转换
 */
public class BaseFunction1<T> implements Function<T,T> {
    @Override
    public T apply(T t) throws Exception {
        return t;
    }
}

