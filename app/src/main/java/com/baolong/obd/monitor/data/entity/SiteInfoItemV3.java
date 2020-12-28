package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;


// 新平台点位类
public class SiteInfoItemV3 implements Serializable {
    private static final long serialVersionUID = 5612326148366366286L;
    /**
     * dwbh : A340104001
     * dwmc : 仰桥路垂直式设备
     * dwlx : A
     * yxrq : 2019-06-12 10:47:14
     * dwzt : 1
     * dwdz : 仰桥路
     * ddjd : 117.15308
     * ddwd : 31.8604
     * clfx : 1
     * cdsl : 1
     * cdpd : 1.0
     * ycxs : 1
     * hphm :
     * clxh :
     * lwzt : 0
     * ip : 0
     * port : 0
     * ccbh : 1
     * dpqy : Y
     * sfyhyc : 1
     * city : 340100
     * county : 340104
     * province : 340000
     * del_flag : 0
     * address : null
     *
     * sfysp:true
     */
    private String dwbh;
    private String dwmc;
    private String dwlx;
    private String yxrq;
    private String dwzt;
    private String dwdz;
    private double ddjd;
    private double ddwd;
    private String clfx;
    private int cdsl;
    private double cdpd;
    private int ycxs;
    private String hphm;
    private String clxh;
    private String lwzt;
    private String ip;
    private String port;
    private String ccbh;
    private String dpqy;
    private String sfyhyc;
    private String city;
    private String county;
    private String province;
    private String del_flag;
    private String address;
    private boolean sfysp;  // 后端要添加


    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDwlx() {
        return dwlx;
    }

    public void setDwlx(String dwlx) {
        this.dwlx = dwlx;
    }

    public String getYxrq() {
        return yxrq;
    }

    public void setYxrq(String yxrq) {
        this.yxrq = yxrq;
    }

    public String getDwzt() {
        return dwzt;
    }

    public void setDwzt(String dwzt) {
        this.dwzt = dwzt;
    }

    public String getDwdz() {
        return dwdz;
    }

    public void setDwdz(String dwdz) {
        this.dwdz = dwdz;
    }

    public double getDdjd() {
        return ddjd;
    }

    public void setDdjd(double ddjd) {
        this.ddjd = ddjd;
    }

    public double getDdwd() {
        return ddwd;
    }

    public void setDdwd(double ddwd) {
        this.ddwd = ddwd;
    }

    public String getClfx() {
        return clfx;
    }

    public void setClfx(String clfx) {
        this.clfx = clfx;
    }

    public int getCdsl() {
        return cdsl;
    }

    public void setCdsl(int cdsl) {
        this.cdsl = cdsl;
    }

    public double getCdpd() {
        return cdpd;
    }

    public void setCdpd(double cdpd) {
        this.cdpd = cdpd;
    }

    public int getYcxs() {
        return ycxs;
    }

    public void setYcxs(int ycxs) {
        this.ycxs = ycxs;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getClxh() {
        return clxh;
    }

    public void setClxh(String clxh) {
        this.clxh = clxh;
    }

    public String getLwzt() {
        return lwzt;
    }

    public void setLwzt(String lwzt) {
        this.lwzt = lwzt;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCcbh() {
        return ccbh;
    }

    public void setCcbh(String ccbh) {
        this.ccbh = ccbh;
    }

    public String getDpqy() {
        return dpqy;
    }

    public void setDpqy(String dpqy) {
        this.dpqy = dpqy;
    }

    public String getSfyhyc() {
        return sfyhyc;
    }

    public void setSfyhyc(String sfyhyc) {
        this.sfyhyc = sfyhyc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSfysp() {
        return sfysp;
    }

    public void setSfysp(boolean sfysp) {
        this.sfysp = sfysp;
    }
}


