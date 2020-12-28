package com.baolong.obd.monitor.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
* 老版设备预警数据对象
*/
@Deprecated
public class GetFaultstateDataListResponseModel {
    private int count;
    private List<GetFaultstateData> list = new ArrayList();
    private int pageNo;
    private int pageSize;

    public int getCount() {
        return this.count;
    }

    public List<GetFaultstateData> getList() {
        return this.list;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setCount(int paramInt) {
        this.count = paramInt;
    }

    public void setList(List<GetFaultstateData> paramList) {
        this.list = paramList;
    }

    public void setPageNo(int paramInt) {
        this.pageNo = paramInt;
    }

    public void setPageSize(int paramInt) {
        this.pageSize = paramInt;
    }

    public class GetFaultstateData {
        private String ddjd;
        private String ddwd;
        private String equipmenttype;
        private String faultdatestart;
        private String id;
        private boolean isNewRecord;
        private String jzbh;
        private String jzmc;
        private String state;

        public GetFaultstateData() {
        }

        public String getDdjd() {
            return this.ddjd;
        }

        public String getDdwd() {
            return this.ddwd;
        }

        public String getEquipmenttype() {
            return this.equipmenttype;
        }

        public String getFaultdatestart() {
            return this.faultdatestart;
        }

        public String getId() {
            return this.id;
        }

        public String getJzbh() {
            return this.jzbh;
        }

        public String getJzmc() {
            return this.jzmc;
        }

        public String getState() {
            return this.state;
        }

        public boolean isNewRecord() {
            return this.isNewRecord;
        }

        public void setDdjd(String paramString) {
            this.ddjd = paramString;
        }

        public void setDdwd(String paramString) {
            this.ddwd = paramString;
        }

        public void setEquipmenttype(String paramString) {
            this.equipmenttype = paramString;
        }

        public void setFaultdatestart(String paramString) {
            this.faultdatestart = paramString;
        }

        public void setId(String paramString) {
            this.id = paramString;
        }

        public void setJzbh(String paramString) {
            this.jzbh = paramString;
        }

        public void setJzmc(String paramString) {
            this.jzmc = paramString;
        }

        public void setNewRecord(boolean paramBoolean) {
            this.isNewRecord = paramBoolean;
        }

        public void setState(String paramString) {
            this.state = paramString;
        }
    }
}
