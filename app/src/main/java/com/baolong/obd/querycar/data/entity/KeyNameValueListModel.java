package com.baolong.obd.querycar.data.entity;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class KeyNameValueListModel implements Serializable {
    private List<KeyNameValueModel> items;

    public KeyNameValueListModel() {
        this.items = new ArrayList<KeyNameValueModel>();
    }

    public List<KeyNameValueModel> getItems() {
        return this.items;
    }

    public void setItems(final List<KeyNameValueModel> items) {
        this.items = items;
    }
}
