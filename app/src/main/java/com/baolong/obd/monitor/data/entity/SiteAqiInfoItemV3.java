package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;

// 新平台点位类
public class SiteAqiInfoItemV3 implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * O3 : 99.8
     * city : 340100
     * dwmc : 焦虎站
     * CLXH : undefined
     * LOWOUT : 1
     * county : 340111
     * DWDZ : 河南省安阳市滑县G230
     * CDPD : 0.0
     * SFYHYC : 1
     * NO2 : 68.2
     * DDWD : 35.33521
     * province : 340000
     * SO2 : 5.0
     * rownumber : 1
     * AQI : 85
     * CLFX : 1
     * PORT : 1
     * FACTORYNUMBER : 1
     * DDJD : 114.51775
     * CDSL : 2
     * ISONLINE : 0
     * IP : 1
     * CO : 0.11
     * YCXS : 2
     * HPHM : undefined
     * DWZT : 1
     * YXRQ : 2017-08-30 00:00:00
     * DWBH : B410526002
     * PM10 : 9.5
     * JLSJ : 2019-08-20 14:06:40
     * DWLX : B
     */

    private double O3;
    private String city;
    private String dwmc;
    private String CLXH;
    private String LOWOUT;
    private String county;
    private String DWDZ;
    private double CDPD;
    private String SFYHYC;
    private double NO2;
    private double DDWD;
    private String province;
    private double SO2;
    private int rownumber;
    private int AQI;
    private String CLFX;
    private String PORT;
    private String FACTORYNUMBER;
    private double DDJD;
    private int CDSL;
    private String ISONLINE;
    private String IP;
    private double CO;
    private int YCXS;
    private String HPHM;
    private String DWZT;
    private String YXRQ;
    private String DWBH;
    private double PM10;
    private String JLSJ;
    private String DWLX;

    public double getO3() {
        return O3;
    }

    public void setO3(double O3) {
        this.O3 = O3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getCLXH() {
        return CLXH;
    }

    public void setCLXH(String CLXH) {
        this.CLXH = CLXH;
    }

    public String getLOWOUT() {
        return LOWOUT;
    }

    public void setLOWOUT(String LOWOUT) {
        this.LOWOUT = LOWOUT;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDWDZ() {
        return DWDZ;
    }

    public void setDWDZ(String DWDZ) {
        this.DWDZ = DWDZ;
    }

    public double getCDPD() {
        return CDPD;
    }

    public void setCDPD(double CDPD) {
        this.CDPD = CDPD;
    }

    public String getSFYHYC() {
        return SFYHYC;
    }

    public void setSFYHYC(String SFYHYC) {
        this.SFYHYC = SFYHYC;
    }

    public double getNO2() {
        return NO2;
    }

    public void setNO2(double NO2) {
        this.NO2 = NO2;
    }

    public double getDDWD() {
        return DDWD;
    }

    public void setDDWD(double DDWD) {
        this.DDWD = DDWD;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getSO2() {
        return SO2;
    }

    public void setSO2(double SO2) {
        this.SO2 = SO2;
    }

    public int getRownumber() {
        return rownumber;
    }

    public void setRownumber(int rownumber) {
        this.rownumber = rownumber;
    }

    public int getAQI() {
        return AQI;
    }

    public void setAQI(int AQI) {
        this.AQI = AQI;
    }

    public String getCLFX() {
        return CLFX;
    }

    public void setCLFX(String CLFX) {
        this.CLFX = CLFX;
    }

    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getFACTORYNUMBER() {
        return FACTORYNUMBER;
    }

    public void setFACTORYNUMBER(String FACTORYNUMBER) {
        this.FACTORYNUMBER = FACTORYNUMBER;
    }

    public double getDDJD() {
        return DDJD;
    }

    public void setDDJD(double DDJD) {
        this.DDJD = DDJD;
    }

    public int getCDSL() {
        return CDSL;
    }

    public void setCDSL(int CDSL) {
        this.CDSL = CDSL;
    }

    public String getISONLINE() {
        return ISONLINE;
    }

    public void setISONLINE(String ISONLINE) {
        this.ISONLINE = ISONLINE;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public double getCO() {
        return CO;
    }

    public void setCO(double CO) {
        this.CO = CO;
    }

    public int getYCXS() {
        return YCXS;
    }

    public void setYCXS(int YCXS) {
        this.YCXS = YCXS;
    }

    public String getHPHM() {
        return HPHM;
    }

    public void setHPHM(String HPHM) {
        this.HPHM = HPHM;
    }

    public String getDWZT() {
        return DWZT;
    }

    public void setDWZT(String DWZT) {
        this.DWZT = DWZT;
    }

    public String getYXRQ() {
        return YXRQ;
    }

    public void setYXRQ(String YXRQ) {
        this.YXRQ = YXRQ;
    }

    public String getDWBH() {
        return DWBH;
    }

    public void setDWBH(String DWBH) {
        this.DWBH = DWBH;
    }

    public double getPM10() {
        return PM10;
    }

    public void setPM10(double PM10) {
        this.PM10 = PM10;
    }

    public String getJLSJ() {
        return JLSJ;
    }

    public void setJLSJ(String JLSJ) {
        this.JLSJ = JLSJ;
    }

    public String getDWLX() {
        return DWLX;
    }

    public void setDWLX(String DWLX) {
        this.DWLX = DWLX;
    }
}
