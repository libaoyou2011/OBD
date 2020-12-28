package com.baolong.obd.blackcar.data.entity;

public class BlackCarDetailModel {
    //"记录编号"
    private String jlbh;
    //点位编号
    private String dwbh;
    //监测时间
    private String jcrq;
    //车道序号
    private String cdxh;
    //车道坡度
    private String cdpd;
    //号牌号码
    private String hphm;
    //车牌颜色
    private String cpys;
    //K(不透光烟度)限值
    private String btgdxz;
    //K(不透光烟度)结果
    private String btgdjg;
    //烟度(林格曼黑度)限值
    private String hdxz;
    //烟度(林格曼黑度)结果
    private String lgmhd;
    //是否黑烟车
    private String isBlackCar;
    //CO2实测值
    private String co2scz;
    //识别置信度
    private double sbzxd;

    public String getJlbh() {
        return jlbh;
    }

    public void setJlbh(String jlbh) {
        this.jlbh = jlbh;
    }

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getJcrq() {
        return jcrq;
    }

    public void setJcrq(String jcrq) {
        this.jcrq = jcrq;
    }

    public String getCdxh() {
        return cdxh;
    }

    public void setCdxh(String cdxh) {
        this.cdxh = cdxh;
    }

    public String getCdpd() {
        return cdpd;
    }

    public void setCdpd(String cdpd) {
        this.cdpd = cdpd;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getCpys() {
        return cpys;
    }

    public void setCpys(String cpys) {
        this.cpys = cpys;
    }

    public String getBtgdxz() {
        return btgdxz;
    }

    public void setBtgdxz(String btgdxz) {
        this.btgdxz = btgdxz;
    }

    public String getBtgdjg() {
        return btgdjg;
    }

    public void setBtgdjg(String btgdjg) {
        this.btgdjg = btgdjg;
    }

    public String getHdxz() {
        return hdxz;
    }

    public void setHdxz(String hdxz) {
        this.hdxz = hdxz;
    }

    public String getLgmhd() {
        return lgmhd;
    }

    public void setLgmhd(String lgmhd) {
        this.lgmhd = lgmhd;
    }

    public String getIsBlackCar() {
        return isBlackCar;
    }

    public void setIsBlackCar(String isBlackCar) {
        this.isBlackCar = isBlackCar;
    }

    public String getCo2scz() {
        return co2scz;
    }

    public void setCo2scz(String co2scz) {
        this.co2scz = co2scz;
    }

    public double getSbzxd() {
        return sbzxd;
    }

    public void setSbzxd(double sbzxd) {
        this.sbzxd = sbzxd;
    }
}
