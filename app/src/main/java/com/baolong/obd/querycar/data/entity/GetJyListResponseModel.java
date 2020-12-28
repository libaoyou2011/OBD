package com.baolong.obd.querycar.data.entity;

import java.util.ArrayList;
import java.util.List;

//@Deprecated 年检/环检
public class GetJyListResponseModel {
    private int count;
    private List<ModelChild> list;
    private int pageNo;
    private int pageSize;

    public GetJyListResponseModel() {
        this.list = new ArrayList<ModelChild>();
    }

    public int getCount() {
        return this.count;
    }

    public List<ModelChild> getList() {
        return this.list;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public void setList(final List<ModelChild> list) {
        this.list = list;
    }

    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public class ModelChild {
        private boolean isNewRecord;
        private String result;
        private String testDateEnd;
        private String testStation;

        public String getResult() {
            return this.result;
        }

        public String getTestDateEnd() {
            return this.testDateEnd;
        }

        public String getTestStation() {
            return this.testStation;
        }

        public boolean isNewRecord() {
            return this.isNewRecord;
        }

        public void setNewRecord(final boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public void setResult(final String result) {
            this.result = result;
        }

        public void setTestDateEnd(final String testDateEnd) {
            this.testDateEnd = testDateEnd;
        }

        public void setTestStation(final String testStation) {
            this.testStation = testStation;
        }
    }
}

