package com.zyl.xuezhibao.model.eventBusModel;

/**
 * Created by qwe on 2017/9/22.
 */

public class EventBusWXMessage {
    private int payType;//0.余额充值

    public EventBusWXMessage() {
    }
    public EventBusWXMessage(int payType) {
        this.payType = payType;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
}
