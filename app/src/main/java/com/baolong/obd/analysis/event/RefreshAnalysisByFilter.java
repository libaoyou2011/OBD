package com.baolong.obd.analysis.event;

import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;

import java.util.List;

/*
 *   RxBus.get().post() 发送：BlackCarFilterActivity 过滤页面点击确认时
 *
 *   @Subscribe 通过注解接受：BlackCarListFragment
 *
 * */
public class RefreshAnalysisByFilter {
    private List<FilterCategoryModel> filterCategoryModelList;

    public RefreshAnalysisByFilter(final List<FilterCategoryModel> filterCategoryModelList) {
        super();
        this.filterCategoryModelList = filterCategoryModelList;
    }

    public List<FilterCategoryModel> getFilterCategoryModelList() {
        return this.filterCategoryModelList;
    }
}