package com.baolong.obd.monitor.event;

import com.baolong.obd.monitor.data.entity.GetMonitoringDataDetailNewModel;

public class UpdateJcEvent{
    private GetMonitoringDataDetailNewModel mModel;

    public UpdateJcEvent(GetMonitoringDataDetailNewModel paramGetMonitoringDataDetailNewModel)    {
        this.mModel = paramGetMonitoringDataDetailNewModel;
    }

    public GetMonitoringDataDetailNewModel getModel()    {
        return this.mModel;
    }
}