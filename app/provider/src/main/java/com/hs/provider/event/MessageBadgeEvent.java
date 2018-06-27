package com.hs.provider.event;

/**
 * Created by rnd on 2018/4/12.
 *
 */

public class MessageBadgeEvent {

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    private boolean isVisible;

    public MessageBadgeEvent(boolean isVisible){
        this.isVisible = isVisible;
    }
}
