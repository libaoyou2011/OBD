package com.baolong.obd.querycar.data.entity;

import java.util.ArrayList;
import java.util.List;

public class GetYcListResponseModel {
    private int count;
    private List<ModelChild> list;
    private int pageNo;
    private int pageSize;

    public GetYcListResponseModel() {
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
        private String hphm;
        private String hpys;
        private String hpysname;
        private String id;
        private boolean isNewRecord;
        private String pdjg;
        private String stationName;
        private String stationNo;
        private String testdate;
        private String url;

        public String getHphm() {
            return this.hphm;
        }

        public String getHpys() {
            return this.hpys;
        }

        public String getHpysname() {
            return this.hpysname;
        }

        public String getId() {
            return this.id;
        }

        public String getPdjg() {
            return this.pdjg;
        }

        public String getStationName() {
            return this.stationName;
        }

        public String getStationNo() {
            return this.stationNo;
        }

        public String getTestdate() {
            return this.testdate;
        }

        public String getUrl() {
            return this.url;
        }

        public boolean isNewRecord() {
            return this.isNewRecord;
        }

        public void setHphm(final String hphm) {
            this.hphm = hphm;
        }

        public void setHpys(final String hpys) {
            this.hpys = hpys;
        }

        public void setHpysname(final String hpysname) {
            this.hpysname = hpysname;
        }

        public void setId(final String id) {
            this.id = id;
        }

        public void setNewRecord(final boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public void setPdjg(final String pdjg) {
            this.pdjg = pdjg;
        }

        public void setStationName(final String stationName) {
            this.stationName = stationName;
        }

        public void setStationNo(final String stationNo) {
            this.stationNo = stationNo;
        }

        public void setTestdate(final String testdate) {
            this.testdate = testdate;
        }

        public void setUrl(final String url) {
            this.url = url;
        }
    }
}
