package com.baolong.obd.execution.event;
/**
 *  处罚、取消处罚后，刷新列表数据
 *
 *  发送： RxBus.get().post();
 *  接收：@Subscribe注解 通过注解接受：UpdateExecListEvent
 */
public class UpdateExecListEvent {
    //1:未处罚列表  2：已处罚列表  3：所有超标车辆  4:全包含

    private String mId;

    public UpdateExecListEvent(final String mId) {
        this.mId = mId;
    }

    public String getId() {
        return this.mId;
    }
}