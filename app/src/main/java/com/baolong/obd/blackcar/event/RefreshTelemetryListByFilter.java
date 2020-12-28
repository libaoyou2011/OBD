package com.baolong.obd.blackcar.event;

import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;

import java.util.List;

/*
*   RxBus.get().post() 发送：BlackCarFilterActivity 过滤页面点击确认时
*
*   @Subscribe 通过注解接受：BlackCarListFragment
*
* */
public class RefreshTelemetryListByFilter {
    private final List<FilterCategoryModel> filterCategoryModelList;

    public RefreshTelemetryListByFilter(final List<FilterCategoryModel> filterCategoryModelList) {
        this.filterCategoryModelList = filterCategoryModelList;
    }

    public List<FilterCategoryModel> getFilterCategoryModelList() {
        return this.filterCategoryModelList;
    }
}