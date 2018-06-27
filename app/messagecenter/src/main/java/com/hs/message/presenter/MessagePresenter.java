package com.hs.message.presenter;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.message.data.repository.MessageRepository;
import com.hs.message.model.MsgInfoResp;
import com.hs.message.presenter.view.MessageView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;



public class MessagePresenter extends BasePresenter<MessageView>{

    private MessageRepository messageRepository;


    public void getMessage(Integer pageIndex,Integer pageSize,LifecycleProvider<FragmentEvent> lifecycleProvider){

        messageRepository = new MessageRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        messageRepository.getMessage(pageIndex,pageSize,lifecycleProvider).subscribe(new BaseObserver<MsgInfoResp>(mView){
            @Override
            public void onNext(MsgInfoResp msgInfoResp) {
                mView.onGetMessageInfo(msgInfoResp.getMsgInfos());
            }
        });
    }

    public void updateMessage(Integer msgId, final Integer msgRead, LifecycleProvider<FragmentEvent> lifeProvider){
        messageRepository = new MessageRepository();

        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        messageRepository.updateMessage(msgId,msgRead,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onUpdateMessage(aBoolean);
            }
        });
    }


    public void deleteMessage(Integer msgId,LifecycleProvider<FragmentEvent> lifeProvider){
        messageRepository = new MessageRepository();
        if(!checkNetWork()){
            return;
        }

        mView.showLoading();

        messageRepository.deleteMessage(msgId,lifeProvider).subscribe(new BaseObserver<Boolean>(mView){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.onDeleteMessage(aBoolean);
            }
        });
    }
}
