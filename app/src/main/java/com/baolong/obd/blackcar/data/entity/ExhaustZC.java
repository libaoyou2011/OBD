package com.baolong.obd.blackcar.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ExhaustZC implements Parcelable {

    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * dataScope : null
     * params : {}
     * id : 202
     * vin : 12345123451234501
     * hphm : 皖A306k7
     * obdbh : 141447161844556
     * sszz : 河南省赛斯电子科技有限公司
     * cjsj : 2020-12-10 11:04:44
     * clsd : 11.0
     * cljd : 114.26573
     * clwd : 30.54558
     * dqyl : 65.0
     * jscnj : -124.46
     * mcnj : -124.9
     * fdjzs : 47.0
     * rlll : 25.0
     * noxjg : -152.0
     * scrrkwd : -230.47
     * scrckwd : -191.47
     * dpfyc : 55.0
     * jql : 41.0
     * fyjyl : 3.75
     * yxyw : 1.0
     * dwzt : null
     * lqywd : 97.0
     * ljlc : 95.0
     * klwnd : 55.0
     * gxsxs : null
     * btgd : null
     * gzzsdzt : null
     * dwbs : null
     * yearxz : null
     * equipment : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"dataScope":null,"params":{},"obdbh":null,"vin":null,"hphm":"皖A306k7","sszz":null,"zxcjsj":null,"clzt":null,"cllx":null,"pfbz":null,"sbzt":null,"rlzl":null,"del_flag":null,"zccarInfo":null,"zdpp":null,"clxh":null,"fdjxh":null,"clscrq":null,"nsxrj":null,"clsccj":null,"fdjgl":null,"jzzl":null,"clsyr":null,"lxhm":null,"zcd":null,"qyxz":null,"jgdm":null,"clmx":null,"cltfd":null,"frdb":null,"fzr":null,"jbr":null,"lxdh":null}
     * noxjgysf : null
     * klwndysf : null
     * dqylysf : null
     * jqlysf : null
     * dpfycysf : null
     * pdjg : 0
     * bjstate : null
     * smsstatus : 0
     */

    private Object searchValue;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private Object dataScope;
    private ParamsBean params;
    private int id;
    private String vin;
    private String hphm;
    private String obdbh;
    private String sszz;
    private String cjsj;
    private double clsd;
    private double cljd;
    private double clwd;
    private double dqyl;
    private double jscnj;
    private double mcnj;
    private double fdjzs;
    private double rlll;
    private double noxjg;
    private double scrrkwd;
    private double scrckwd;
    private double dpfyc;
    private double jql;
    private double fyjyl;
    private double yxyw;
    private Object dwzt;
    private double lqywd;
    private double ljlc;
    private double klwnd;
    private Object gxsxs;
    private Object btgd;
    private Object gzzsdzt;
    private Object dwbs;
    private Object yearxz;
    private EquipmentBean equipment;
    private Object noxjgysf;
    private Object klwndysf;
    private Object dqylysf;
    private Object jqlysf;
    private Object dpfycysf;
    private String pdjg;
    private Object bjstate;
    private String smsstatus;

    protected ExhaustZC(Parcel in) {
        id = in.readInt();
        vin = in.readString();
        hphm = in.readString();
        obdbh = in.readString();
        sszz = in.readString();
        cjsj = in.readString();
        clsd = in.readDouble();
        cljd = in.readDouble();
        clwd = in.readDouble();
        dqyl = in.readDouble();
        jscnj = in.readDouble();
        mcnj = in.readDouble();
        fdjzs = in.readDouble();
        rlll = in.readDouble();
        noxjg = in.readDouble();
        scrrkwd = in.readDouble();
        scrckwd = in.readDouble();
        dpfyc = in.readDouble();
        jql = in.readDouble();
        fyjyl = in.readDouble();
        yxyw = in.readDouble();
        lqywd = in.readDouble();
        ljlc = in.readDouble();
        klwnd = in.readDouble();
        pdjg = in.readString();
        smsstatus = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(vin);
        dest.writeString(hphm);
        dest.writeString(obdbh);
        dest.writeString(sszz);
        dest.writeString(cjsj);
        dest.writeDouble(clsd);
        dest.writeDouble(cljd);
        dest.writeDouble(clwd);
        dest.writeDouble(dqyl);
        dest.writeDouble(jscnj);
        dest.writeDouble(mcnj);
        dest.writeDouble(fdjzs);
        dest.writeDouble(rlll);
        dest.writeDouble(noxjg);
        dest.writeDouble(scrrkwd);
        dest.writeDouble(scrckwd);
        dest.writeDouble(dpfyc);
        dest.writeDouble(jql);
        dest.writeDouble(fyjyl);
        dest.writeDouble(yxyw);
        dest.writeDouble(lqywd);
        dest.writeDouble(ljlc);
        dest.writeDouble(klwnd);
        dest.writeString(pdjg);
        dest.writeString(smsstatus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExhaustZC> CREATOR = new Creator<ExhaustZC>() {
        @Override
        public ExhaustZC createFromParcel(Parcel in) {
            return new ExhaustZC(in);
        }

        @Override
        public ExhaustZC[] newArray(int size) {
            return new ExhaustZC[size];
        }
    };

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Object getDataScope() {
        return dataScope;
    }

    public void setDataScope(Object dataScope) {
        this.dataScope = dataScope;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getObdbh() {
        return obdbh;
    }

    public void setObdbh(String obdbh) {
        this.obdbh = obdbh;
    }

    public String getSszz() {
        return sszz;
    }

    public void setSszz(String sszz) {
        this.sszz = sszz;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public double getClsd() {
        return clsd;
    }

    public void setClsd(double clsd) {
        this.clsd = clsd;
    }

    public double getCljd() {
        return cljd;
    }

    public void setCljd(double cljd) {
        this.cljd = cljd;
    }

    public double getClwd() {
        return clwd;
    }

    public void setClwd(double clwd) {
        this.clwd = clwd;
    }

    public double getDqyl() {
        return dqyl;
    }

    public void setDqyl(double dqyl) {
        this.dqyl = dqyl;
    }

    public double getJscnj() {
        return jscnj;
    }

    public void setJscnj(double jscnj) {
        this.jscnj = jscnj;
    }

    public double getMcnj() {
        return mcnj;
    }

    public void setMcnj(double mcnj) {
        this.mcnj = mcnj;
    }

    public double getFdjzs() {
        return fdjzs;
    }

    public void setFdjzs(double fdjzs) {
        this.fdjzs = fdjzs;
    }

    public double getRlll() {
        return rlll;
    }

    public void setRlll(double rlll) {
        this.rlll = rlll;
    }

    public double getNoxjg() {
        return noxjg;
    }

    public void setNoxjg(double noxjg) {
        this.noxjg = noxjg;
    }

    public double getScrrkwd() {
        return scrrkwd;
    }

    public void setScrrkwd(double scrrkwd) {
        this.scrrkwd = scrrkwd;
    }

    public double getScrckwd() {
        return scrckwd;
    }

    public void setScrckwd(double scrckwd) {
        this.scrckwd = scrckwd;
    }

    public double getDpfyc() {
        return dpfyc;
    }

    public void setDpfyc(double dpfyc) {
        this.dpfyc = dpfyc;
    }

    public double getJql() {
        return jql;
    }

    public void setJql(double jql) {
        this.jql = jql;
    }

    public double getFyjyl() {
        return fyjyl;
    }

    public void setFyjyl(double fyjyl) {
        this.fyjyl = fyjyl;
    }

    public double getYxyw() {
        return yxyw;
    }

    public void setYxyw(double yxyw) {
        this.yxyw = yxyw;
    }

    public Object getDwzt() {
        return dwzt;
    }

    public void setDwzt(Object dwzt) {
        this.dwzt = dwzt;
    }

    public double getLqywd() {
        return lqywd;
    }

    public void setLqywd(double lqywd) {
        this.lqywd = lqywd;
    }

    public double getLjlc() {
        return ljlc;
    }

    public void setLjlc(double ljlc) {
        this.ljlc = ljlc;
    }

    public double getKlwnd() {
        return klwnd;
    }

    public void setKlwnd(double klwnd) {
        this.klwnd = klwnd;
    }

    public Object getGxsxs() {
        return gxsxs;
    }

    public void setGxsxs(Object gxsxs) {
        this.gxsxs = gxsxs;
    }

    public Object getBtgd() {
        return btgd;
    }

    public void setBtgd(Object btgd) {
        this.btgd = btgd;
    }

    public Object getGzzsdzt() {
        return gzzsdzt;
    }

    public void setGzzsdzt(Object gzzsdzt) {
        this.gzzsdzt = gzzsdzt;
    }

    public Object getDwbs() {
        return dwbs;
    }

    public void setDwbs(Object dwbs) {
        this.dwbs = dwbs;
    }

    public Object getYearxz() {
        return yearxz;
    }

    public void setYearxz(Object yearxz) {
        this.yearxz = yearxz;
    }

    public EquipmentBean getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentBean equipment) {
        this.equipment = equipment;
    }

    public Object getNoxjgysf() {
        return noxjgysf;
    }

    public void setNoxjgysf(Object noxjgysf) {
        this.noxjgysf = noxjgysf;
    }

    public Object getKlwndysf() {
        return klwndysf;
    }

    public void setKlwndysf(Object klwndysf) {
        this.klwndysf = klwndysf;
    }

    public Object getDqylysf() {
        return dqylysf;
    }

    public void setDqylysf(Object dqylysf) {
        this.dqylysf = dqylysf;
    }

    public Object getJqlysf() {
        return jqlysf;
    }

    public void setJqlysf(Object jqlysf) {
        this.jqlysf = jqlysf;
    }

    public Object getDpfycysf() {
        return dpfycysf;
    }

    public void setDpfycysf(Object dpfycysf) {
        this.dpfycysf = dpfycysf;
    }

    public String getPdjg() {
        return pdjg;
    }

    public void setPdjg(String pdjg) {
        this.pdjg = pdjg;
    }

    public Object getBjstate() {
        return bjstate;
    }

    public void setBjstate(Object bjstate) {
        this.bjstate = bjstate;
    }

    public String getSmsstatus() {
        return smsstatus;
    }

    public void setSmsstatus(String smsstatus) {
        this.smsstatus = smsstatus;
    }

    public static class ParamsBean {
    }

    public static class EquipmentBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * dataScope : null
         * params : {}
         * obdbh : null
         * vin : null
         * hphm : 皖A306k7
         * sszz : null
         * zxcjsj : null
         * clzt : null
         * cllx : null
         * pfbz : null
         * sbzt : null
         * rlzl : null
         * del_flag : null
         * zccarInfo : null
         * zdpp : null
         * clxh : null
         * fdjxh : null
         * clscrq : null
         * nsxrj : null
         * clsccj : null
         * fdjgl : null
         * jzzl : null
         * clsyr : null
         * lxhm : null
         * zcd : null
         * qyxz : null
         * jgdm : null
         * clmx : null
         * cltfd : null
         * frdb : null
         * fzr : null
         * jbr : null
         * lxdh : null
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private Object dataScope;
        private ParamsBean params;
        private Object obdbh;
        private Object vin;
        private String hphm;
        private Object sszz;
        private Object zxcjsj;
        private Object clzt;
        private Object cllx;
        private Object pfbz;
        private Object sbzt;
        private Object rlzl;
        private Object del_flag;
        private Object zccarInfo;
        private Object zdpp;
        private Object clxh;
        private Object fdjxh;
        private Object clscrq;
        private Object nsxrj;
        private Object clsccj;
        private Object fdjgl;
        private Object jzzl;
        private Object clsyr;
        private Object lxhm;
        private Object zcd;
        private Object qyxz;
        private Object jgdm;
        private Object clmx;
        private Object cltfd;
        private Object frdb;
        private Object fzr;
        private Object jbr;
        private Object lxdh;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getDataScope() {
            return dataScope;
        }

        public void setDataScope(Object dataScope) {
            this.dataScope = dataScope;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public Object getObdbh() {
            return obdbh;
        }

        public void setObdbh(Object obdbh) {
            this.obdbh = obdbh;
        }

        public Object getVin() {
            return vin;
        }

        public void setVin(Object vin) {
            this.vin = vin;
        }

        public String getHphm() {
            return hphm;
        }

        public void setHphm(String hphm) {
            this.hphm = hphm;
        }

        public Object getSszz() {
            return sszz;
        }

        public void setSszz(Object sszz) {
            this.sszz = sszz;
        }

        public Object getZxcjsj() {
            return zxcjsj;
        }

        public void setZxcjsj(Object zxcjsj) {
            this.zxcjsj = zxcjsj;
        }

        public Object getClzt() {
            return clzt;
        }

        public void setClzt(Object clzt) {
            this.clzt = clzt;
        }

        public Object getCllx() {
            return cllx;
        }

        public void setCllx(Object cllx) {
            this.cllx = cllx;
        }

        public Object getPfbz() {
            return pfbz;
        }

        public void setPfbz(Object pfbz) {
            this.pfbz = pfbz;
        }

        public Object getSbzt() {
            return sbzt;
        }

        public void setSbzt(Object sbzt) {
            this.sbzt = sbzt;
        }

        public Object getRlzl() {
            return rlzl;
        }

        public void setRlzl(Object rlzl) {
            this.rlzl = rlzl;
        }

        public Object getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(Object del_flag) {
            this.del_flag = del_flag;
        }

        public Object getZccarInfo() {
            return zccarInfo;
        }

        public void setZccarInfo(Object zccarInfo) {
            this.zccarInfo = zccarInfo;
        }

        public Object getZdpp() {
            return zdpp;
        }

        public void setZdpp(Object zdpp) {
            this.zdpp = zdpp;
        }

        public Object getClxh() {
            return clxh;
        }

        public void setClxh(Object clxh) {
            this.clxh = clxh;
        }

        public Object getFdjxh() {
            return fdjxh;
        }

        public void setFdjxh(Object fdjxh) {
            this.fdjxh = fdjxh;
        }

        public Object getClscrq() {
            return clscrq;
        }

        public void setClscrq(Object clscrq) {
            this.clscrq = clscrq;
        }

        public Object getNsxrj() {
            return nsxrj;
        }

        public void setNsxrj(Object nsxrj) {
            this.nsxrj = nsxrj;
        }

        public Object getClsccj() {
            return clsccj;
        }

        public void setClsccj(Object clsccj) {
            this.clsccj = clsccj;
        }

        public Object getFdjgl() {
            return fdjgl;
        }

        public void setFdjgl(Object fdjgl) {
            this.fdjgl = fdjgl;
        }

        public Object getJzzl() {
            return jzzl;
        }

        public void setJzzl(Object jzzl) {
            this.jzzl = jzzl;
        }

        public Object getClsyr() {
            return clsyr;
        }

        public void setClsyr(Object clsyr) {
            this.clsyr = clsyr;
        }

        public Object getLxhm() {
            return lxhm;
        }

        public void setLxhm(Object lxhm) {
            this.lxhm = lxhm;
        }

        public Object getZcd() {
            return zcd;
        }

        public void setZcd(Object zcd) {
            this.zcd = zcd;
        }

        public Object getQyxz() {
            return qyxz;
        }

        public void setQyxz(Object qyxz) {
            this.qyxz = qyxz;
        }

        public Object getJgdm() {
            return jgdm;
        }

        public void setJgdm(Object jgdm) {
            this.jgdm = jgdm;
        }

        public Object getClmx() {
            return clmx;
        }

        public void setClmx(Object clmx) {
            this.clmx = clmx;
        }

        public Object getCltfd() {
            return cltfd;
        }

        public void setCltfd(Object cltfd) {
            this.cltfd = cltfd;
        }

        public Object getFrdb() {
            return frdb;
        }

        public void setFrdb(Object frdb) {
            this.frdb = frdb;
        }

        public Object getFzr() {
            return fzr;
        }

        public void setFzr(Object fzr) {
            this.fzr = fzr;
        }

        public Object getJbr() {
            return jbr;
        }

        public void setJbr(Object jbr) {
            this.jbr = jbr;
        }

        public Object getLxdh() {
            return lxdh;
        }

        public void setLxdh(Object lxdh) {
            this.lxdh = lxdh;
        }

        public static class ParamsBean {
        }
    }
}
