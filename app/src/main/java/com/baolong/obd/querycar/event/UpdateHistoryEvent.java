package com.baolong.obd.querycar.event;

public class UpdateHistoryEvent {
    private String mId;

    public UpdateHistoryEvent(final String mId) {
        this.mId = mId;
    }

    public String getId() {
        return this.mId;
    }
}

