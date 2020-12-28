package com.baolong.obd.my.event;

public class UpdateDefaultStationEvent {
    private String mId;

    public UpdateDefaultStationEvent(String paramString) {
        this.mId = paramString;
    }

    public String getId() {
        return this.mId;
    }
}
