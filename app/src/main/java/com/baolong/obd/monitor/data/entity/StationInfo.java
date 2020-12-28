package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;
import java.util.Date;

public class StationInfo implements Serializable {
    private static final long serialVersionUID = 3859834759473996942L;

    //编号
    private int id;
    //点位编号
    private String jzbh;
    //点位名称
    private String jzmc;
    //点位类型
    private String jzlx;
    //点位日期
    private Date jzrq;
    //点位状态
    private String jzzt;
    //省
    private String province;
    //市
    private String city;
    //县
    private String county;
    //点位地址
    private String jzdz;
    //地点经度
    private double ddjd;
    //地点纬度
    private double ddwd;
    //车流方向
    private String fxlx;
    //车道数量
    private int cdsl;
    //坡度
    private double cdpd;
    //遥测线数
    private int jzjcx;
    //号牌号码
    private String hphm;
    //装载车类型
    private String clxh;
    //设备出厂编号
    private String factory_number;
    //网络IP
    private String ip;
    //端口
    private String port;
    //是否关闭设备
    private boolean is_shutdown;
    //创建者
    private String create_by;
    //创建时间
    private Date create_date;
    //修改者
    private String update_by;
    //修改时间
    private Date update_date;
    //删除标记
    private boolean del_flag;
    //备注
    private String remarks;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJzbh() {
        return jzbh;
    }

    public void setJzbh(String jzbh) {
        this.jzbh = jzbh;
    }

    public String getJzmc() {
        return jzmc;
    }

    public void setJzmc(String jzmc) {
        this.jzmc = jzmc;
    }

    public String getJzlx() {
        return jzlx;
    }

    public void setJzlx(String jzlx) {
        this.jzlx = jzlx;
    }

    public Date getJzrq() {
        return jzrq;
    }

    public void setJzrq(Date jxrq) {
        this.jzrq = jxrq;
    }

    public String getJzzt() {
        return jzzt;
    }

    public void setJzzt(String jzzt) {
        this.jzzt = jzzt;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getJzdz() {
        return jzdz;
    }

    public void setJzdz(String jzdz) {
        this.jzdz = jzdz;
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

    public String getFxlx() {
        return fxlx;
    }

    public void setFxlx(String fxlx) {
        this.fxlx = fxlx;
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

    public int getJzjcx() {
        return jzjcx;
    }

    public void setJzjcx(int jzjcx) {
        this.jzjcx = jzjcx;
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

    public String getFactory_number() {
        return factory_number;
    }

    public void setFactory_number(String factory_number) {
        this.factory_number = factory_number;
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

    public boolean isIs_shutdown() {
        return is_shutdown;
    }

    public void setIs_shutdown(boolean is_shutdown) {
        this.is_shutdown = is_shutdown;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public boolean isDel_flag() {
        return del_flag;
    }

    public void setDel_flag(boolean del_flag) {
        this.del_flag = del_flag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
