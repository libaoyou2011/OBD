package com.baolong.obd.blackcar.data.entity;

import java.io.Serializable;

/**
 * 点位遥测线信息实体类 SiteTeleLineInfo
 * 
 * @author zxl
 */
public class SiteTeleLineInfo implements Serializable {
    private String dwbh;

    private String ycxbh;

    private String cdxh;

    private String jcxtxh;

    private String jcxtmc;

    private String jcxtbh;

    private String jcxtzzc;

    private String csyxh;

    private String csyscc;
    //@StringTimeFormat(pattern="yyyy-MM-dd")
    private String csyyxq;

    private String qtcsyxh;

    private String qtcsyscc;
    //@StringTimeFormat(pattern="yyyy-MM-dd")
    private String qtcsyyxq;

    private String ydjxh;

    private String ydjscc;
    //@StringTimeFormat(pattern="yyyy-MM-dd")
    private String ydjyxq;

    private String sxxtxh;

    private String sxxtscc;
    //@StringTimeFormat(pattern="yyyy-MM-dd")
    private String sxxtyxq;

    private String pdjxh;

    private String pdjscc;
    //@StringTimeFormat(pattern="yyyy-MM-dd")
    private String pdjyxq;

    private String qxzxh;

    private String qxzscc;
    //@StringTimeFormat(pattern="yyyy-MM-dd")
    private String qxzyxq;
    
    private String del_flag;
    
    

    public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh == null ? null : dwbh.trim();
    }

    public String getYcxbh() {
        return ycxbh;
    }

    public void setYcxbh(String ycxbh) {
        this.ycxbh = ycxbh == null ? null : ycxbh.trim();
    }

    public String getCdxh() {
        return cdxh;
    }

    public void setCdxh(String cdxh) {
        this.cdxh = cdxh == null ? null : cdxh.trim();
    }

    public String getJcxtxh() {
        return jcxtxh;
    }

    public void setJcxtxh(String jcxtxh) {
        this.jcxtxh = jcxtxh == null ? null : jcxtxh.trim();
    }

    public String getJcxtmc() {
        return jcxtmc;
    }

    public void setJcxtmc(String jcxtmc) {
        this.jcxtmc = jcxtmc == null ? null : jcxtmc.trim();
    }

    public String getJcxtbh() {
        return jcxtbh;
    }

    public void setJcxtbh(String jcxtbh) {
        this.jcxtbh = jcxtbh == null ? null : jcxtbh.trim();
    }

    public String getJcxtzzc() {
        return jcxtzzc;
    }

    public void setJcxtzzc(String jcxtzzc) {
        this.jcxtzzc = jcxtzzc == null ? null : jcxtzzc.trim();
    }

    public String getCsyxh() {
        return csyxh;
    }

    public void setCsyxh(String csyxh) {
        this.csyxh = csyxh == null ? null : csyxh.trim();
    }

    public String getCsyscc() {
        return csyscc;
    }

    public void setCsyscc(String csyscc) {
        this.csyscc = csyscc == null ? null : csyscc.trim();
    }

    public String getCsyyxq() {
        return csyyxq;
    }

    public void setCsyyxq(String csyyxq) {
        this.csyyxq = csyyxq;
    }

    public String getQtcsyxh() {
        return qtcsyxh;
    }

    public void setQtcsyxh(String qtcsyxh) {
        this.qtcsyxh = qtcsyxh == null ? null : qtcsyxh.trim();
    }

    public String getQtcsyscc() {
        return qtcsyscc;
    }

    public void setQtcsyscc(String qtcsyscc) {
        this.qtcsyscc = qtcsyscc == null ? null : qtcsyscc.trim();
    }

    public String getQtcsyyxq() {
        return qtcsyyxq;
    }

    public void setQtcsyyxq(String qtcsyyxq) {
        this.qtcsyyxq = qtcsyyxq;
    }

    public String getYdjxh() {
        return ydjxh;
    }

    public void setYdjxh(String ydjxh) {
        this.ydjxh = ydjxh == null ? null : ydjxh.trim();
    }

    public String getYdjscc() {
        return ydjscc;
    }

    public void setYdjscc(String ydjscc) {
        this.ydjscc = ydjscc == null ? null : ydjscc.trim();
    }

    public String getYdjyxq() {
        return ydjyxq;
    }

    public void setYdjyxq(String ydjyxq) {
        this.ydjyxq = ydjyxq;
    }

    public String getSxxtxh() {
        return sxxtxh;
    }

    public void setSxxtxh(String sxxtxh) {
        this.sxxtxh = sxxtxh == null ? null : sxxtxh.trim();
    }

    public String getSxxtscc() {
        return sxxtscc;
    }

    public void setSxxtscc(String sxxtscc) {
        this.sxxtscc = sxxtscc == null ? null : sxxtscc.trim();
    }

    public String getSxxtyxq() {
        return sxxtyxq;
    }

    public void setSxxtyxq(String sxxtyxq) {
        this.sxxtyxq = sxxtyxq;
    }

    public String getPdjxh() {
        return pdjxh;
    }

    public void setPdjxh(String pdjxh) {
        this.pdjxh = pdjxh == null ? null : pdjxh.trim();
    }

    public String getPdjscc() {
        return pdjscc;
    }

    public void setPdjscc(String pdjscc) {
        this.pdjscc = pdjscc == null ? null : pdjscc.trim();
    }

    public String getPdjyxq() {
        return pdjyxq;
    }

    public void setPdjyxq(String pdjyxq) {
        this.pdjyxq = pdjyxq;
    }

    public String getQxzxh() {
        return qxzxh;
    }

    public void setQxzxh(String qxzxh) {
        this.qxzxh = qxzxh == null ? null : qxzxh.trim();
    }

    public String getQxzscc() {
        return qxzscc;
    }

    public void setQxzscc(String qxzscc) {
        this.qxzscc = qxzscc == null ? null : qxzscc.trim();
    }

    public String getQxzyxq() {
        return qxzyxq;
    }

    public void setQxzyxq(String qxzyxq) {
        this.qxzyxq = qxzyxq;
    }
}