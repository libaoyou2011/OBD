package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;


// V2版本点位类
@Deprecated
public class SiteInfoItem implements Serializable {
    private static final long serialVersionUID = 5612326148366366286L;

    /**
     * CLFX : 1
     * PORT : 8181
     * FACTORYNUMBER : 1
     * DDJD : 117.59
     * CDSL : 1
     * ISONLINE : 0
     * city : 340100
     * CLXH :
     * IP : 10.10.10.243
     * LOWOUT : 1
     * county : 340104
     * DWMC : 雪霁路
     * DWDZ : 雪霁路
     * CDPD : 1
     * SFYHYC : 1
     * YCXS : 1
     * HPHM :
     * DWZT : 1
     * DDWD : 31.68
     * province : 340000
     * YXRQ : 2019-06-12 10:47:14
     * DWBH : B410927001
     * DWLX : B
     */

    private String DWBH;    //点位编号
    private String DWMC;    //点位名称
    private String DWDZ;    //点位地址
    private double DDJD;    //点位经度
    private double DDWD;    //点位纬度
    private String DWLX;    //点位类型  A:垂直  B:水平  C:移动
    private int CDSL;       //车道数量
    private double CDPD;    //车道坡度
    private String CLFX;    //车流方向
    private int YCXS;       //遥测线数
    private String DWZT;    //点位状态  1:正常  2：维护  3：停用
    private String IP;      //IP
    private String PORT;    //端口号
    private String ISONLINE;//是否在线  0:断网 1：正常  字段弃用
    private String CLXH;    //装载车型号
    private String LOWOUT;
    private String county;      //县
    private String city;        //市
    private String province;    //省
    private String SFYHYC;   //是否有黑烟车
    private String HPHM;     //号牌号码
    private String YXRQ;     //运行日期
    private String FACTORYNUMBER;   //设备出厂编号
    private boolean sfysp;   //设备出厂编号


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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCLXH() {
        return CLXH;
    }

    public void setCLXH(String CLXH) {
        this.CLXH = CLXH;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
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

    public String getDWMC() {
        return DWMC;
    }

    public void setDWMC(String DWMC) {
        this.DWMC = DWMC;
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

    public String getDWLX() {
        return DWLX;
    }

    public void setDWLX(String DWLX) {
        this.DWLX = DWLX;
    }

    public boolean isSfysp() {
        return sfysp;
    }

    public void setSfysp(boolean sfysp) {
        this.sfysp = sfysp;
    }

}


