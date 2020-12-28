package com.baolong.obd.blackcar.event;
/**
 *  列表返回时刷新统计数量
 *
 *  发送： RxBus.get().post();
 *  接收：@Subscribe注解 通过注解接受：UpdateBlackSumEvent
 */
public class UpdateBlackSumEvent {
    private String id;
    private int wshSum;
    private int yshSum;
    private int allSum;

    public UpdateBlackSumEvent(final String mId, final int wcfSum, final int ycfSum, final int allSum) {
        this.id = mId;
        this.wshSum = wcfSum;
        this.yshSum = ycfSum;
        this.allSum = allSum;
    }

    public String getId() {
        return id;
    }

    public int getWshSum() {
        return wshSum;
    }

    public int getYshSum() {
        return yshSum;
    }

    public int getAllSum() {
        return allSum;
    }


}