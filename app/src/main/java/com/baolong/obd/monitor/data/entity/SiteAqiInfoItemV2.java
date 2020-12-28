package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;

// V2版本
@Deprecated
public class SiteAqiInfoItemV2 extends SiteInfoItemV3 implements Serializable {
    private static final long serialVersionUID = -1L;

    private Double O3;
    private Double NO2;
    private Double SO2;
    private Double AQI;
    private Double CO;
    private Double PM10;
    private Double PM25;
    private String JLSJ; //纪录时间 空气质量

    public Double getO3() {
        return O3;
    }

    public void setO3(Double o3) {
        O3 = o3;
    }

    public Double getNO2() {
        return NO2;
    }

    public void setNO2(Double NO2) {
        this.NO2 = NO2;
    }

    public Double getSO2() {
        return SO2;
    }

    public void setSO2(Double SO2) {
        this.SO2 = SO2;
    }

    public Double getAQI() {
        return AQI;
    }

    public void setAQI(Double AQI) {
        this.AQI = AQI;
    }

    public Double getCO() {
        return CO;
    }

    public void setCO(Double CO) {
        this.CO = CO;
    }

    public Double getPM10() {
        return PM10;
    }

    public void setPM10(Double PM10) {
        this.PM10 = PM10;
    }

    public Double getPM25() {
        return PM25;
    }

    public void setPM25(Double PM25) {
        this.PM25 = PM25;
    }

    public String getJLSJ() {
        return JLSJ;
    }

    public void setJLSJ(String JLSJ) {
        this.JLSJ = JLSJ;
    }
}
