package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;

public class MarkerObject implements Serializable {
    private static final long serialVersionUID = -1L;
    private SiteInfoItemV3 siteInfoItem;
    private GetTodayAmountModel2 siteInfoItemCount;
    private SiteAqiInfoItemV3 siteAqiInfoItem;


    public MarkerObject() {
    }

    public SiteInfoItemV3 getSiteInfoItem() {
        return siteInfoItem;
    }

    public void setSiteInfoItem(SiteInfoItemV3 siteInfoItem) {
        this.siteInfoItem = siteInfoItem;
    }

    public GetTodayAmountModel2 getSiteInfoItemCount() {
        return siteInfoItemCount;
    }

    public void setSiteInfoItemCount(GetTodayAmountModel2 siteInfoItemCount) {
        this.siteInfoItemCount = siteInfoItemCount;
    }

    public SiteAqiInfoItemV3 getSiteAqiInfoItem() {
        return siteAqiInfoItem;
    }

    public void setSiteAqiInfoItem(SiteAqiInfoItemV3 siteAqiInfoItem) {
        this.siteAqiInfoItem = siteAqiInfoItem;
    }
}
