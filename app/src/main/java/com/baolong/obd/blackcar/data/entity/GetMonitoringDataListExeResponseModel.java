package com.baolong.obd.blackcar.data.entity;

import java.util.ArrayList;
import java.util.List;

public class GetMonitoringDataListExeResponseModel {
    private int count;
    private List<MonitoringDataDetail> list = new ArrayList();
    private int pageNo;
    private int pageSize;
    private int tempSize;

    public int getCount() {
        return this.count;
    }

    public List<MonitoringDataDetail> getList() {
        return this.list;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTempSize() {
        return this.tempSize;
    }

    public void setCount(int paramInt) {
        this.count = paramInt;
    }

    public void setList(List<MonitoringDataDetail> paramList) {
        this.list = paramList;
    }

    public void setPageNo(int paramInt) {
        this.pageNo = paramInt;
    }

    public void setPageSize(int paramInt) {
        this.pageSize = paramInt;
    }

    public void setTempSize(int paramInt) {
        this.tempSize = paramInt;
    }

    public class MonitoringDataDetail {
        // 编号
        private String id;
        // 纪录编号
        private String testno;
        // 点位编号
        private String jzbh;
        // 监测时间
        private String testdate;

        private String blackSmokeReview;
        // 车道编号
        private String cdbh;
        // 号牌号码
        private String hphm;
        // 号牌颜色
        private String hpys;
        private String hpysname;
        // 是否是黑烟车
        private String isBlackCar;
        private boolean isNewRecord;

        private String pdjg;
        private String stationname;

        //图像 1
        private String tp1;

        public MonitoringDataDetail() {
        }

        public String getCdbh() {
            return this.cdbh;
        }

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

        public String getIsBlackCar() {
            return this.isBlackCar;
        }

        public String getIsSh() {
            return this.blackSmokeReview;
        }

        public String getJzbh() {
            return this.jzbh;
        }

        public String getPdjg() {
            return this.pdjg;
        }

        public String getStationname() {
            return this.stationname;
        }

        public String getTestdate() {
            return this.testdate;
        }

        public String getTestno() {
            return this.testno;
        }

        public String getTp1() {
            return this.tp1;
        }

        public boolean isNewRecord() {
            return this.isNewRecord;
        }

        public void setCdbh(String paramString) {
            this.cdbh = paramString;
        }

        public void setHphm(String paramString) {
            this.hphm = paramString;
        }

        public void setHpys(String paramString) {
            this.hpys = paramString;
        }

        public void setHpysname(String paramString) {
            this.hpysname = paramString;
        }

        public void setId(String paramString) {
            this.id = paramString;
        }

        public void setIsBlackCar(String paramString) {
            this.isBlackCar = paramString;
        }

        public void setIsSh(String paramString) {
            this.blackSmokeReview = paramString;
        }

        public void setJzbh(String paramString) {
            this.jzbh = paramString;
        }

        public void setNewRecord(boolean paramBoolean) {
            this.isNewRecord = paramBoolean;
        }

        public void setPdjg(String paramString) {
            this.pdjg = paramString;
        }

        public void setStationname(String paramString) {
            this.stationname = paramString;
        }

        public void setTestdate(String paramString) {
            this.testdate = paramString;
        }

        public void setTestno(String paramString) {
            this.testno = paramString;
        }

        public void setTp1(String paramString) {
            this.tp1 = paramString;
        }
    }
}
