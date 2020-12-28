package com.baolong.obd.blackcar.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 点位信息实体类 SiteInfo
 *
 * @author zxl
 */
public class SiteInfo implements Serializable {
	private String dwbh;

	private String dwmc;

	private String dwlx;

	//@StringTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String yxrq;

	private String dwzt;

	private String dwdz;

	private double ddjd;

	private double ddwd;

	private String clfx;

	private Short cdsl;

	private double cdpd;

	private Short ycxs;

	private String hphm;

	private String clxh;

	private String isonline;

	private String ip;

	private String port;


	private String factorynumber;


	private String lowout;

	private String sfyhyc;

	private String city;

	private String county;

	private String province;

	private List<SiteTeleLineInfo> monitoringLineList = new ArrayList<>();




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

	public List<SiteTeleLineInfo> getMonitoringLineList() {
		return monitoringLineList;
	}

	public void setMonitoringLineList(List<SiteTeleLineInfo> monitoringLineList) {
		this.monitoringLineList = monitoringLineList;
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

	public String getFactorynumber() {
		return factorynumber;
	}

	public void setFactorynumber(String factorynumber) {
		this.factorynumber = factorynumber;
	}

	public String getLowout() {
		return lowout;
	}

	public void setLowout(String lowout) {
		this.lowout = lowout;
	}

	public String getSfyhyc() {
		return sfyhyc;
	}

	public void setSfyhyc(String sfyhyc) {
		this.sfyhyc = sfyhyc;
	}

	public String getDwbh() {
		return dwbh;
	}

	public void setDwbh(String dwbh) {
		this.dwbh = dwbh == null ? null : dwbh.trim();
	}

	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline == null ? null : isonline.trim();
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc == null ? null : dwmc.trim();
	}

	public String getDwlx() {
		return dwlx;
	}

	public void setDwlx(String dwlx) {
		this.dwlx = dwlx == null ? null : dwlx.trim();
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
		this.dwzt = dwzt == null ? null : dwzt.trim();
	}

	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz == null ? null : dwdz.trim();
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
		this.clfx = clfx == null ? null : clfx.trim();
	}

	public Short getCdsl() {
		return cdsl;
	}

	public void setCdsl(Short cdsl) {
		this.cdsl = cdsl;
	}

	public double getCdpd() {
		return cdpd;
	}

	public void setCdpd(double cdpd) {
		this.cdpd = cdpd;
	}

	public Short getYcxs() {
		return ycxs;
	}

	public void setYcxs(Short ycxs) {
		this.ycxs = ycxs;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm == null ? null : hphm.trim();
	}

	public String getClxh() {
		return clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh == null ? null : clxh.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}