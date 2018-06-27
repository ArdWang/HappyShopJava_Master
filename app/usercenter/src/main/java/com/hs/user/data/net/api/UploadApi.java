package com.hs.user.data.net.api;


import com.hs.base.data.net.protocol.BaseResp;
import com.hs.user.data.net.protocol.UploadDataReq;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface UploadApi {
    /**
     * 获取token
     */
    @POST("common/getUploadToken")
    Observable<BaseResp<String>> uploadData(@Body UploadDataReq req);
}
