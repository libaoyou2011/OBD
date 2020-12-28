package com.baolong.obd.execution.event;
/**
 *  列表返回时刷新统计数量
 *
 *  发送： RxBus.get().post();
 *  接收：@Subscribe注解 通过注解接受：UpdateExeSumEvent
 */
public class UpdateExeSumEvent {
    private String id;
    private int wcfSum;
    private int ycfSum;
    private int allOverProofSum;

    public UpdateExeSumEvent(final String id, final int wcfSum, final int ycfSum, final int allOverProofSum) {
        this.id = id;
        this.wcfSum = wcfSum;
        this.ycfSum = ycfSum;
        this.allOverProofSum = allOverProofSum;
    }

    public String getId() {
        return id;
    }

    public int getWcfSum() {
        return wcfSum;
    }

    public int getYcfSum() {
        return ycfSum;
    }

    public int getAllOverProofSum() {
        return allOverProofSum;
    }
}