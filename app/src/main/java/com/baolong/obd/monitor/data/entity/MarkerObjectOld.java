package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;

@Deprecated
public class MarkerObjectOld implements Serializable {
    private static final long serialVersionUID = -7699500765664160009L;
    private GetStationListAllResponseModel simpleInfo;
    private GetStationInfoResponseModel detailInfo;

    public MarkerObjectOld() {
    }

    public GetStationListAllResponseModel getSimpleInfo() {
        return simpleInfo;
    }

    public void setSimpleInfo(GetStationListAllResponseModel simpleInfo) {
        this.simpleInfo = simpleInfo;
    }

    public GetStationInfoResponseModel getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(GetStationInfoResponseModel detailInfo) {
        this.detailInfo = detailInfo;
    }
}
