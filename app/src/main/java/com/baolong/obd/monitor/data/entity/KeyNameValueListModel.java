package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KeyNameValueListModel implements Serializable {
    private List<KeyNameValueModel> items = new ArrayList();

    public List<KeyNameValueModel> getItems() {
        return this.items;
    }

    public void setItems(List<KeyNameValueModel> paramList) {
        this.items = paramList;
    }
}
