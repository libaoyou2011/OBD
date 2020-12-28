package com.baolong.obd.blackcar.event;

/**
 *  黑烟车审核后，刷新列表数据
 *
 *  发送： RxBus.get().post();
 *  接收：@Subscribe注解 通过注解接受：UpdateBlackListEvent
 */
public class UpdateBlackListEvent {
    //1:待审核列表  2：已审核列表  3：所有黑烟疏忽  4:全包含
    String mId;

    public UpdateBlackListEvent(String paramString) {
        this.mId = paramString;
    }

    public String getId() {
        return this.mId;
    }
}
