package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class StationListAllModel implements Serializable {
    private static final long serialVersionUID = -2758973559467764575L;

    private ArrayList<SiteInfoItemV3> historyList = new ArrayList<>();


    public ArrayList<SiteInfoItemV3> getHistoryList() {
        return this.historyList;
    }

    public void setHistoryList(ArrayList<SiteInfoItemV3> paramList) {
        this.historyList = paramList;
    }
}