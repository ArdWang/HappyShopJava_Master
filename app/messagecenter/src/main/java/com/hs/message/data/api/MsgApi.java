package com.hs.message.data.api;


import com.hs.base.data.net.protocol.BaseResp;
import com.hs.message.data.protocol.DeleteMessageReq;
import com.hs.message.data.protocol.GetMessageReq;
import com.hs.message.data.protocol.UpdateMessageReq;
import com.hs.message.model.MsgInfoResp;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MsgApi {

    @POST("message/getMessage")
    Observable<MsgInfoResp> getMessage(@Body GetMessageReq req);


    @POST("message/updateMessage")
    Observable<BaseResp<Boolean>> updateMessage(@Body UpdateMessageReq req);


    @POST("message/deleteMessage")
    Observable<BaseResp<Boolean>> deleteMessage(@Body DeleteMessageReq req);

}
