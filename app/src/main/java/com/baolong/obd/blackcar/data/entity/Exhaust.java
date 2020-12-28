package com.baolong.obd.blackcar.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

//Intent 在传递对象时，切记不仅外部的实体类要序列化，其嵌套的实体类均序列化
public class Exhaust implements Parcelable {

    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * dataScope : null
     * params : {}
     * jlbh : A1101190010420201028084857
     * siteInfo : {"dwbh":"A110119001","dwmc":"延庆","dwlx":null,"yxrq":null,"dwzt":null,"dwdz":"三里河公交站","ddjd":0,"ddwd":0,"sfysp":null,"clfx":null,"cdsl":null,"cdpd":0.2,"ycxs":null,"hphm":"京AMU788","clxh":null,"lwzt":null,"ip":null,"port":null,"ccbh":null,"dpqy":null,"sfyhyc":null,"city":"110100","county":"110119","province":"110000","del_flag":null,"address":null}
     * dwbh : A110119001
     * dwbhs : null
     * ycxbh : 04
     * jcdwrzh : 20201028
     * jcryxm :
     * cdxh : 1
     * jcrq : 2020-10-28 08:48:57
     * ddjd : 0.0
     * ddwd : 0.0
     * cdpd : 0.2
     * pdjg : 0
     * wxyy : -1
     * hphm : 京AMU788
     * cpys : 1
     * hpzl : 02
     * csys :
     * sbzxd : 89.0
     * rlzl : A
     * co2scz : 1.35
     * coscz : 0.07
     * hcscz : 59.0
     * noscz : 143.0
     * co2jg : 14.46
     * coco2 : 0.05
     * hcco2 : 43.48
     * noco2 : 105.39
     * cojg : 0.82
     * hcjg : 628.93
     * nojg : 1524.37
     * btgdjg : 1.14
     * lgmhd : 0
     * btgdpj : 1.14
     * btgdzd : 2.3
     * btgdzx : 0.0
     * btgxs : null
     * no2pj : 0.0
     * no2zd : 0.0
     * no2zx : 0.0
     * co2xz : 0.0
     * coxz : 2.5
     * noxz : 1500.0
     * hcxz : 1200.0
     * btgdxz : 30.0
     * hdxz : 1
     * btgxsxz : 0.0
     * clsd : 46.81
     * cljsd : 0.21
     * vsp : 6.89
     * fs : 0.0
     * fx : 0
     * hjwd : 6.2
     * sd : 40.4
     * dqy : 97.1
     * gjxxbh : 201028957705
     * cllx :
     * clxh : null
     * tp1 : http://36.7.144.182:8015/Qualified/20201028/11/4/08/head/head_4857659_京AMU788.jpg
     * tp2 :
     * sp1 :
     * tp3 :
     * tp4 :
     * tp5 :
     * tpList : []
     * cbcsysf : null
     * cbcs : null
     * fsysf : null
     * hjwdysf : null
     * lgmhdysf : null
     * sdysf : null
     * dqyysf : null
     * vspysf : null
     * cojgysf : null
     * hcjgysf : null
     * nojgysf : null
     * btgdjgysf : null
     * co2sczysf : null
     * sbzxdysf : null
     * siteTeleLineInfo : null
     * clgs : null
     * bdhp : null
     * sfcf : null
     * datepart : null
     * num : 0
     * cishu : 0
     * yearxz : null
     * tplasttime : null
     * tpsize : null
     * splasttime : null
     * spsize : null
     * csTableDatas : []
     */

    private Object searchValue;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private Object dataScope;
    private ParamsBean params;
    private String jlbh;
    private SiteInfoBean siteInfo;
    private String dwbh;
    private Object dwbhs;
    private String ycxbh;
    private String jcdwrzh;
    private String jcryxm;
    private int cdxh;
    private String jcrq;
    private double ddjd;
    private double ddwd;
    private double cdpd;
    private String pdjg;
    private String wxyy;
    private String hphm;
    private String cpys;
    private String hpzl;
    private String csys;
    private double sbzxd;
    private String rlzl;
    private double co2scz;
    private double coscz;
    private double hcscz;
    private double noscz;
    private double co2jg;
    private double coco2;
    private double hcco2;
    private double noco2;
    private double cojg;
    private double hcjg;
    private double nojg;
    private double btgdjg;
    private int lgmhd;
    private double btgdpj;
    private double btgdzd;
    private double btgdzx;
    private Object btgxs;
    private double no2pj;
    private double no2zd;
    private double no2zx;
    private double co2xz;
    private double coxz;
    private double noxz;
    private double hcxz;
    private double btgdxz;
    private int hdxz;
    private double btgxsxz;
    private double clsd;
    private double cljsd;
    private double vsp;
    private double fs;
    private String fx;
    private double hjwd;
    private double sd;
    private double dqy;
    private String gjxxbh;
    private String cllx;
    private Object clxh;
    private String tp1;
    private String tp2;
    private String sp1;
    private String tp3;
    private String tp4;
    private String tp5;
    private Object cbcsysf;
    private Object cbcs;
    private Object fsysf;
    private Object hjwdysf;
    private Object lgmhdysf;
    private Object sdysf;
    private Object dqyysf;
    private Object vspysf;
    private Object cojgysf;
    private Object hcjgysf;
    private Object nojgysf;
    private Object btgdjgysf;
    private Object co2sczysf;
    private Object sbzxdysf;
    private Object siteTeleLineInfo;
    private Object clgs;
    private Object bdhp;
    private Object sfcf;
    private Object datepart;
    private int num;
    private int cishu;
    private Object yearxz;
    private Object tplasttime;
    private Object tpsize;
    private Object splasttime;
    private Object spsize;
    private List<?> tpList;
    private List<?> csTableDatas;

    protected Exhaust(Parcel in) {
        jlbh = in.readString();
        siteInfo = in.readParcelable(SiteInfoBean.class.getClassLoader());
        dwbh = in.readString();
        ycxbh = in.readString();
        jcdwrzh = in.readString();
        jcryxm = in.readString();
        cdxh = in.readInt();
        jcrq = in.readString();
        ddjd = in.readDouble();
        ddwd = in.readDouble();
        cdpd = in.readDouble();
        pdjg = in.readString();
        wxyy = in.readString();
        hphm = in.readString();
        cpys = in.readString();
        hpzl = in.readString();
        csys = in.readString();
        sbzxd = in.readDouble();
        rlzl = in.readString();
        co2scz = in.readDouble();
        coscz = in.readDouble();
        hcscz = in.readDouble();
        noscz = in.readDouble();
        co2jg = in.readDouble();
        coco2 = in.readDouble();
        hcco2 = in.readDouble();
        noco2 = in.readDouble();
        cojg = in.readDouble();
        hcjg = in.readDouble();
        nojg = in.readDouble();
        btgdjg = in.readDouble();
        lgmhd = in.readInt();
        btgdpj = in.readDouble();
        btgdzd = in.readDouble();
        btgdzx = in.readDouble();
        no2pj = in.readDouble();
        no2zd = in.readDouble();
        no2zx = in.readDouble();
        co2xz = in.readDouble();
        coxz = in.readDouble();
        noxz = in.readDouble();
        hcxz = in.readDouble();
        btgdxz = in.readDouble();
        hdxz = in.readInt();
        btgxsxz = in.readDouble();
        clsd = in.readDouble();
        cljsd = in.readDouble();
        vsp = in.readDouble();
        fs = in.readDouble();
        fx = in.readString();
        hjwd = in.readDouble();
        sd = in.readDouble();
        dqy = in.readDouble();
        gjxxbh = in.readString();
        cllx = in.readString();
        tp1 = in.readString();
        tp2 = in.readString();
        sp1 = in.readString();
        tp3 = in.readString();
        tp4 = in.readString();
        tp5 = in.readString();
        num = in.readInt();
        cishu = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(jlbh);
        dest.writeParcelable(siteInfo, flags);
        dest.writeString(dwbh);
        dest.writeString(ycxbh);
        dest.writeString(jcdwrzh);
        dest.writeString(jcryxm);
        dest.writeInt(cdxh);
        dest.writeString(jcrq);
        dest.writeDouble(ddjd);
        dest.writeDouble(ddwd);
        dest.writeDouble(cdpd);
        dest.writeString(pdjg);
        dest.writeString(wxyy);
        dest.writeString(hphm);
        dest.writeString(cpys);
        dest.writeString(hpzl);
        dest.writeString(csys);
        dest.writeDouble(sbzxd);
        dest.writeString(rlzl);
        dest.writeDouble(co2scz);
        dest.writeDouble(coscz);
        dest.writeDouble(hcscz);
        dest.writeDouble(noscz);
        dest.writeDouble(co2jg);
        dest.writeDouble(coco2);
        dest.writeDouble(hcco2);
        dest.writeDouble(noco2);
        dest.writeDouble(cojg);
        dest.writeDouble(hcjg);
        dest.writeDouble(nojg);
        dest.writeDouble(btgdjg);
        dest.writeInt(lgmhd);
        dest.writeDouble(btgdpj);
        dest.writeDouble(btgdzd);
        dest.writeDouble(btgdzx);
        dest.writeDouble(no2pj);
        dest.writeDouble(no2zd);
        dest.writeDouble(no2zx);
        dest.writeDouble(co2xz);
        dest.writeDouble(coxz);
        dest.writeDouble(noxz);
        dest.writeDouble(hcxz);
        dest.writeDouble(btgdxz);
        dest.writeInt(hdxz);
        dest.writeDouble(btgxsxz);
        dest.writeDouble(clsd);
        dest.writeDouble(cljsd);
        dest.writeDouble(vsp);
        dest.writeDouble(fs);
        dest.writeString(fx);
        dest.writeDouble(hjwd);
        dest.writeDouble(sd);
        dest.writeDouble(dqy);
        dest.writeString(gjxxbh);
        dest.writeString(cllx);
        dest.writeString(tp1);
        dest.writeString(tp2);
        dest.writeString(sp1);
        dest.writeString(tp3);
        dest.writeString(tp4);
        dest.writeString(tp5);
        dest.writeInt(num);
        dest.writeInt(cishu);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Exhaust> CREATOR = new Creator<Exhaust>() {
        @Override
        public Exhaust createFromParcel(Parcel in) {
            return new Exhaust(in);
        }

        @Override
        public Exhaust[] newArray(int size) {
            return new Exhaust[size];
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

    public String getJlbh() {
        return jlbh;
    }

    public void setJlbh(String jlbh) {
        this.jlbh = jlbh;
    }

    public SiteInfoBean getSiteInfo() {
        return siteInfo;
    }

    public void setSiteInfo(SiteInfoBean siteInfo) {
        this.siteInfo = siteInfo;
    }

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public Object getDwbhs() {
        return dwbhs;
    }

    public void setDwbhs(Object dwbhs) {
        this.dwbhs = dwbhs;
    }

    public String getYcxbh() {
        return ycxbh;
    }

    public void setYcxbh(String ycxbh) {
        this.ycxbh = ycxbh;
    }

    public String getJcdwrzh() {
        return jcdwrzh;
    }

    public void setJcdwrzh(String jcdwrzh) {
        this.jcdwrzh = jcdwrzh;
    }

    public String getJcryxm() {
        return jcryxm;
    }

    public void setJcryxm(String jcryxm) {
        this.jcryxm = jcryxm;
    }

    public int getCdxh() {
        return cdxh;
    }

    public void setCdxh(int cdxh) {
        this.cdxh = cdxh;
    }

    public String getJcrq() {
        return jcrq;
    }

    public void setJcrq(String jcrq) {
        this.jcrq = jcrq;
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

    public double getCdpd() {
        return cdpd;
    }

    public void setCdpd(double cdpd) {
        this.cdpd = cdpd;
    }

    public String getPdjg() {
        return pdjg;
    }

    public void setPdjg(String pdjg) {
        this.pdjg = pdjg;
    }

    public String getWxyy() {
        return wxyy;
    }

    public void setWxyy(String wxyy) {
        this.wxyy = wxyy;
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

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getCsys() {
        return csys;
    }

    public void setCsys(String csys) {
        this.csys = csys;
    }

    public double getSbzxd() {
        return sbzxd;
    }

    public void setSbzxd(double sbzxd) {
        this.sbzxd = sbzxd;
    }

    public String getRlzl() {
        return rlzl;
    }

    public void setRlzl(String rlzl) {
        this.rlzl = rlzl;
    }

    public double getCo2scz() {
        return co2scz;
    }

    public void setCo2scz(double co2scz) {
        this.co2scz = co2scz;
    }

    public double getCoscz() {
        return coscz;
    }

    public void setCoscz(double coscz) {
        this.coscz = coscz;
    }

    public double getHcscz() {
        return hcscz;
    }

    public void setHcscz(double hcscz) {
        this.hcscz = hcscz;
    }

    public double getNoscz() {
        return noscz;
    }

    public void setNoscz(double noscz) {
        this.noscz = noscz;
    }

    public double getCo2jg() {
        return co2jg;
    }

    public void setCo2jg(double co2jg) {
        this.co2jg = co2jg;
    }

    public double getCoco2() {
        return coco2;
    }

    public void setCoco2(double coco2) {
        this.coco2 = coco2;
    }

    public double getHcco2() {
        return hcco2;
    }

    public void setHcco2(double hcco2) {
        this.hcco2 = hcco2;
    }

    public double getNoco2() {
        return noco2;
    }

    public void setNoco2(double noco2) {
        this.noco2 = noco2;
    }

    public double getCojg() {
        return cojg;
    }

    public void setCojg(double cojg) {
        this.cojg = cojg;
    }

    public double getHcjg() {
        return hcjg;
    }

    public void setHcjg(double hcjg) {
        this.hcjg = hcjg;
    }

    public double getNojg() {
        return nojg;
    }

    public void setNojg(double nojg) {
        this.nojg = nojg;
    }

    public double getBtgdjg() {
        return btgdjg;
    }

    public void setBtgdjg(double btgdjg) {
        this.btgdjg = btgdjg;
    }

    public int getLgmhd() {
        return lgmhd;
    }

    public void setLgmhd(int lgmhd) {
        this.lgmhd = lgmhd;
    }

    public double getBtgdpj() {
        return btgdpj;
    }

    public void setBtgdpj(double btgdpj) {
        this.btgdpj = btgdpj;
    }

    public double getBtgdzd() {
        return btgdzd;
    }

    public void setBtgdzd(double btgdzd) {
        this.btgdzd = btgdzd;
    }

    public double getBtgdzx() {
        return btgdzx;
    }

    public void setBtgdzx(double btgdzx) {
        this.btgdzx = btgdzx;
    }

    public Object getBtgxs() {
        return btgxs;
    }

    public void setBtgxs(Object btgxs) {
        this.btgxs = btgxs;
    }

    public double getNo2pj() {
        return no2pj;
    }

    public void setNo2pj(double no2pj) {
        this.no2pj = no2pj;
    }

    public double getNo2zd() {
        return no2zd;
    }

    public void setNo2zd(double no2zd) {
        this.no2zd = no2zd;
    }

    public double getNo2zx() {
        return no2zx;
    }

    public void setNo2zx(double no2zx) {
        this.no2zx = no2zx;
    }

    public double getCo2xz() {
        return co2xz;
    }

    public void setCo2xz(double co2xz) {
        this.co2xz = co2xz;
    }

    public double getCoxz() {
        return coxz;
    }

    public void setCoxz(double coxz) {
        this.coxz = coxz;
    }

    public double getNoxz() {
        return noxz;
    }

    public void setNoxz(double noxz) {
        this.noxz = noxz;
    }

    public double getHcxz() {
        return hcxz;
    }

    public void setHcxz(double hcxz) {
        this.hcxz = hcxz;
    }

    public double getBtgdxz() {
        return btgdxz;
    }

    public void setBtgdxz(double btgdxz) {
        this.btgdxz = btgdxz;
    }

    public int getHdxz() {
        return hdxz;
    }

    public void setHdxz(int hdxz) {
        this.hdxz = hdxz;
    }

    public double getBtgxsxz() {
        return btgxsxz;
    }

    public void setBtgxsxz(double btgxsxz) {
        this.btgxsxz = btgxsxz;
    }

    public double getClsd() {
        return clsd;
    }

    public void setClsd(double clsd) {
        this.clsd = clsd;
    }

    public double getCljsd() {
        return cljsd;
    }

    public void setCljsd(double cljsd) {
        this.cljsd = cljsd;
    }

    public double getVsp() {
        return vsp;
    }

    public void setVsp(double vsp) {
        this.vsp = vsp;
    }

    public double getFs() {
        return fs;
    }

    public void setFs(double fs) {
        this.fs = fs;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public double getHjwd() {
        return hjwd;
    }

    public void setHjwd(double hjwd) {
        this.hjwd = hjwd;
    }

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }

    public double getDqy() {
        return dqy;
    }

    public void setDqy(double dqy) {
        this.dqy = dqy;
    }

    public String getGjxxbh() {
        return gjxxbh;
    }

    public void setGjxxbh(String gjxxbh) {
        this.gjxxbh = gjxxbh;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public Object getClxh() {
        return clxh;
    }

    public void setClxh(Object clxh) {
        this.clxh = clxh;
    }

    public String getTp1() {
        return tp1;
    }

    public void setTp1(String tp1) {
        this.tp1 = tp1;
    }

    public String getTp2() {
        return tp2;
    }

    public void setTp2(String tp2) {
        this.tp2 = tp2;
    }

    public String getSp1() {
        return sp1;
    }

    public void setSp1(String sp1) {
        this.sp1 = sp1;
    }

    public String getTp3() {
        return tp3;
    }

    public void setTp3(String tp3) {
        this.tp3 = tp3;
    }

    public String getTp4() {
        return tp4;
    }

    public void setTp4(String tp4) {
        this.tp4 = tp4;
    }

    public String getTp5() {
        return tp5;
    }

    public void setTp5(String tp5) {
        this.tp5 = tp5;
    }

    public Object getCbcsysf() {
        return cbcsysf;
    }

    public void setCbcsysf(Object cbcsysf) {
        this.cbcsysf = cbcsysf;
    }

    public Object getCbcs() {
        return cbcs;
    }

    public void setCbcs(Object cbcs) {
        this.cbcs = cbcs;
    }

    public Object getFsysf() {
        return fsysf;
    }

    public void setFsysf(Object fsysf) {
        this.fsysf = fsysf;
    }

    public Object getHjwdysf() {
        return hjwdysf;
    }

    public void setHjwdysf(Object hjwdysf) {
        this.hjwdysf = hjwdysf;
    }

    public Object getLgmhdysf() {
        return lgmhdysf;
    }

    public void setLgmhdysf(Object lgmhdysf) {
        this.lgmhdysf = lgmhdysf;
    }

    public Object getSdysf() {
        return sdysf;
    }

    public void setSdysf(Object sdysf) {
        this.sdysf = sdysf;
    }

    public Object getDqyysf() {
        return dqyysf;
    }

    public void setDqyysf(Object dqyysf) {
        this.dqyysf = dqyysf;
    }

    public Object getVspysf() {
        return vspysf;
    }

    public void setVspysf(Object vspysf) {
        this.vspysf = vspysf;
    }

    public Object getCojgysf() {
        return cojgysf;
    }

    public void setCojgysf(Object cojgysf) {
        this.cojgysf = cojgysf;
    }

    public Object getHcjgysf() {
        return hcjgysf;
    }

    public void setHcjgysf(Object hcjgysf) {
        this.hcjgysf = hcjgysf;
    }

    public Object getNojgysf() {
        return nojgysf;
    }

    public void setNojgysf(Object nojgysf) {
        this.nojgysf = nojgysf;
    }

    public Object getBtgdjgysf() {
        return btgdjgysf;
    }

    public void setBtgdjgysf(Object btgdjgysf) {
        this.btgdjgysf = btgdjgysf;
    }

    public Object getCo2sczysf() {
        return co2sczysf;
    }

    public void setCo2sczysf(Object co2sczysf) {
        this.co2sczysf = co2sczysf;
    }

    public Object getSbzxdysf() {
        return sbzxdysf;
    }

    public void setSbzxdysf(Object sbzxdysf) {
        this.sbzxdysf = sbzxdysf;
    }

    public Object getSiteTeleLineInfo() {
        return siteTeleLineInfo;
    }

    public void setSiteTeleLineInfo(Object siteTeleLineInfo) {
        this.siteTeleLineInfo = siteTeleLineInfo;
    }

    public Object getClgs() {
        return clgs;
    }

    public void setClgs(Object clgs) {
        this.clgs = clgs;
    }

    public Object getBdhp() {
        return bdhp;
    }

    public void setBdhp(Object bdhp) {
        this.bdhp = bdhp;
    }

    public Object getSfcf() {
        return sfcf;
    }

    public void setSfcf(Object sfcf) {
        this.sfcf = sfcf;
    }

    public Object getDatepart() {
        return datepart;
    }

    public void setDatepart(Object datepart) {
        this.datepart = datepart;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCishu() {
        return cishu;
    }

    public void setCishu(int cishu) {
        this.cishu = cishu;
    }

    public Object getYearxz() {
        return yearxz;
    }

    public void setYearxz(Object yearxz) {
        this.yearxz = yearxz;
    }

    public Object getTplasttime() {
        return tplasttime;
    }

    public void setTplasttime(Object tplasttime) {
        this.tplasttime = tplasttime;
    }

    public Object getTpsize() {
        return tpsize;
    }

    public void setTpsize(Object tpsize) {
        this.tpsize = tpsize;
    }

    public Object getSplasttime() {
        return splasttime;
    }

    public void setSplasttime(Object splasttime) {
        this.splasttime = splasttime;
    }

    public Object getSpsize() {
        return spsize;
    }

    public void setSpsize(Object spsize) {
        this.spsize = spsize;
    }

    public List<?> getTpList() {
        return tpList;
    }

    public void setTpList(List<?> tpList) {
        this.tpList = tpList;
    }

    public List<?> getCsTableDatas() {
        return csTableDatas;
    }

    public void setCsTableDatas(List<?> csTableDatas) {
        this.csTableDatas = csTableDatas;
    }

    public static class ParamsBean {
    }

    public static class SiteInfoBean implements Parcelable {
        /**
         * dwbh : A110119001
         * dwmc : 延庆
         * dwlx : null
         * yxrq : null
         * dwzt : null
         * dwdz : 三里河公交站
         * ddjd : 0.0
         * ddwd : 0.0
         * sfysp : null
         * clfx : null
         * cdsl : null
         * cdpd : 0.2
         * ycxs : null
         * hphm : 京AMU788
         * clxh : null
         * lwzt : null
         * ip : null
         * port : null
         * ccbh : null
         * dpqy : null
         * sfyhyc : null
         * city : 110100
         * county : 110119
         * province : 110000
         * del_flag : null
         * address : null
         */

        private String dwbh;
        private String dwmc;
        private Object dwlx;
        private Object yxrq;
        private Object dwzt;
        private String dwdz;
        private double ddjd;
        private double ddwd;
        private Object sfysp;
        private Object clfx;
        private Object cdsl;
        private double cdpd;
        private Object ycxs;
        private String hphm;
        private Object clxh;
        private Object lwzt;
        private Object ip;
        private Object port;
        private Object ccbh;
        private Object dpqy;
        private Object sfyhyc;
        private String city;
        private String county;
        private String province;
        private Object del_flag;
        private Object address;

        protected SiteInfoBean(Parcel in) {
            dwbh = in.readString();
            dwmc = in.readString();
            dwdz = in.readString();
            ddjd = in.readDouble();
            ddwd = in.readDouble();
            cdpd = in.readDouble();
            hphm = in.readString();
            city = in.readString();
            county = in.readString();
            province = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(dwbh);
            dest.writeString(dwmc);
            dest.writeString(dwdz);
            dest.writeDouble(ddjd);
            dest.writeDouble(ddwd);
            dest.writeDouble(cdpd);
            dest.writeString(hphm);
            dest.writeString(city);
            dest.writeString(county);
            dest.writeString(province);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<SiteInfoBean> CREATOR = new Creator<SiteInfoBean>() {
            @Override
            public SiteInfoBean createFromParcel(Parcel in) {
                return new SiteInfoBean(in);
            }

            @Override
            public SiteInfoBean[] newArray(int size) {
                return new SiteInfoBean[size];
            }
        };

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

        public Object getDwlx() {
            return dwlx;
        }

        public void setDwlx(Object dwlx) {
            this.dwlx = dwlx;
        }

        public Object getYxrq() {
            return yxrq;
        }

        public void setYxrq(Object yxrq) {
            this.yxrq = yxrq;
        }

        public Object getDwzt() {
            return dwzt;
        }

        public void setDwzt(Object dwzt) {
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

        public Object getSfysp() {
            return sfysp;
        }

        public void setSfysp(Object sfysp) {
            this.sfysp = sfysp;
        }

        public Object getClfx() {
            return clfx;
        }

        public void setClfx(Object clfx) {
            this.clfx = clfx;
        }

        public Object getCdsl() {
            return cdsl;
        }

        public void setCdsl(Object cdsl) {
            this.cdsl = cdsl;
        }

        public double getCdpd() {
            return cdpd;
        }

        public void setCdpd(double cdpd) {
            this.cdpd = cdpd;
        }

        public Object getYcxs() {
            return ycxs;
        }

        public void setYcxs(Object ycxs) {
            this.ycxs = ycxs;
        }

        public String getHphm() {
            return hphm;
        }

        public void setHphm(String hphm) {
            this.hphm = hphm;
        }

        public Object getClxh() {
            return clxh;
        }

        public void setClxh(Object clxh) {
            this.clxh = clxh;
        }

        public Object getLwzt() {
            return lwzt;
        }

        public void setLwzt(Object lwzt) {
            this.lwzt = lwzt;
        }

        public Object getIp() {
            return ip;
        }

        public void setIp(Object ip) {
            this.ip = ip;
        }

        public Object getPort() {
            return port;
        }

        public void setPort(Object port) {
            this.port = port;
        }

        public Object getCcbh() {
            return ccbh;
        }

        public void setCcbh(Object ccbh) {
            this.ccbh = ccbh;
        }

        public Object getDpqy() {
            return dpqy;
        }

        public void setDpqy(Object dpqy) {
            this.dpqy = dpqy;
        }

        public Object getSfyhyc() {
            return sfyhyc;
        }

        public void setSfyhyc(Object sfyhyc) {
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

        public Object getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(Object del_flag) {
            this.del_flag = del_flag;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }
    }
}