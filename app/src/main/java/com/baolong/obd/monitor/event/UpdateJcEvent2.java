package com.baolong.obd.monitor.event;

import com.baolong.obd.blackcar.data.entity.Exhaust;

public class UpdateJcEvent2 {
    private Exhaust mModel;

    public UpdateJcEvent2(Exhaust paramGetMonitoringDataDetailNewModel)    {
        this.mModel = paramGetMonitoringDataDetailNewModel;
    }

    public Exhaust getModel()    {
        return this.mModel;
    }
}