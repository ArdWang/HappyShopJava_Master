package com.hs.message.data.repository;

import com.hs.base.data.net.http.RetrofitFactory;
import com.hs.base.data.net.repository.BaseRepository;
import com.hs.base.rx.BaseFunction;
import com.hs.base.rx.BaseFunction1;
import com.hs.base.rx.BaseFunctionBoolean;
import com.hs.message.data.api.MsgApi;
import com.hs.message.data.protocol.DeleteMessageReq;
import com.hs.message.data.protocol.GetMessageReq;
import com.hs.message.data.protocol.UpdateMessageReq;
import com.hs.message.model.MsgInfo;
import com.hs.message.model.MsgInfoResp;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import io.reactivex.Observable;


public class MessageRepository extends BaseRepository{

    private MsgApi msgApi;


    public MessageRepository(){
        msgApi = RetrofitFactory.getInstance().create(MsgApi.class);
    }


    public Observable<MsgInfoResp> getMessage(Integer pageIndex, Integer pageSize, LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(msgApi.getMessage(new GetMessageReq(pageIndex,pageSize)),lifeProvider)
                .map(new BaseFunction1<MsgInfoResp>());
    }



    public Observable<Boolean> updateMessage(Integer msgId,Integer msgRead,LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(msgApi.updateMessage(new UpdateMessageReq(msgId,msgRead)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());

    }


    public Observable<Boolean> deleteMessage(Integer msgId,LifecycleProvider<FragmentEvent> lifeProvider){
        return observefg(msgApi.deleteMessage(new DeleteMessageReq(msgId)),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }
}
