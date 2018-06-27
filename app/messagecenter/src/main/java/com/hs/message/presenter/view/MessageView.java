package com.hs.message.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.message.model.MsgInfo;

import java.util.List;

public interface MessageView extends BaseView{

    void onGetMessageInfo(List<MsgInfo> result);

    void onUpdateMessage(Boolean result);

    void onDeleteMessage(Boolean result);

}
