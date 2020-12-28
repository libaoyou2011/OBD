package com.baolong.obd.monitor.event;

import com.baolong.obd.monitor.data.entity.SiteInfoItem;

/*
 * 站点预警列表页面，点击“定位到地图”， RxBus.get().post();
 */
public class JumpToMapEvent {
    private SiteInfoItem mModel;

    public JumpToMapEvent(final SiteInfoItem mModel) {
        super();
        this.mModel = mModel;
    }

    public SiteInfoItem getModel() {
        return this.mModel;
    }
}