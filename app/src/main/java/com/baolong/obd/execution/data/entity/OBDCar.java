package com.baolong.obd.execution.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class OBDCar implements Parcelable {
    /**
     * {
     * "searchValue":"",
     * "createBy":"",
     * "createTime":"",
     * "updateBy":"",
     * "updateTime":"",
     * "remark":null,
     * "dataScope":null,
     * "params":{
     * <p>
     * },
     * "obdbh":"141447161844556",
     * "vin":"12345123451234501",
     * "hphm":"皖A306k7",
     * "sszz":"河南省赛斯电子科技有限公司",
     * "zxcjsj":"2020年12月10日",
     * "clzt":"1",
     * "cllx":"",
     * "pfbz":"5",
     * "sbzt":"1",
     * "rlzl":"B",
     * "del_flag":"0",
     * "zccarInfo":null,
     * "zdpp":"北京华夏OBD",
     * "clxh":"BJ5319CCY-AA",
     * "fdjxh":"VIS112222345654",
     * "clscrq":"2019年09月04日",
     * "nsxrj":"20",
     * "clsccj":"河南长安汽车有限公司",
     * "fdjgl":"500",
     * "jzzl":"1",
     * "clsyr":"张三",
     * "lxhm":"15313889001",
     * "zcd":"河南",
     * "qyxz":"民企",
     * "jgdm":"",
     * "clmx":null,
     * "cltfd":null,
     * "frdb":null,
     * "fzr":null,
     * "jbr":null,
     * "lxdh":null
     * }
     */

    private String searchValue;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private Object remark;
    private Object dataScope;
    private ParamsBean params;
    private String obdbh;
    private String vin;
    private String hphm;
    private String sszz;
    private String zxcjsj;
    private String clzt;
    private String cllx;
    private String pfbz;
    private String sbzt;
    private String rlzl;
    private String del_flag;
    private Object zccarInfo;
    private String zdpp;
    private String clxh;
    private String fdjxh;
    private String clscrq;
    private String nsxrj;
    private String clsccj;
    private String fdjgl;
    private String jzzl;
    private String clsyr;
    private String lxhm;
    private String zcd;
    private String qyxz;
    private String jgdm;
    private Object clmx;
    private Object cltfd;
    private String frdb;
    private String fzr;
    private String jbr;
    private String lxdh;

    public OBDCar() {

    }

    protected OBDCar(Parcel in) {
        searchValue = in.readString();
        createBy = in.readString();
        createTime = in.readString();
        updateBy = in.readString();
        updateTime = in.readString();
        obdbh = in.readString();
        vin = in.readString();
        hphm = in.readString();
        sszz = in.readString();
        zxcjsj = in.readString();
        clzt = in.readString();
        cllx = in.readString();
        pfbz = in.readString();
        sbzt = in.readString();
        rlzl = in.readString();
        del_flag = in.readString();
        zdpp = in.readString();
        clxh = in.readString();
        fdjxh = in.readString();
        clscrq = in.readString();
        nsxrj = in.readString();
        clsccj = in.readString();
        fdjgl = in.readString();
        jzzl = in.readString();
        clsyr = in.readString();
        lxhm = in.readString();
        zcd = in.readString();
        qyxz = in.readString();
        jgdm = in.readString();
        frdb = in.readString();
    }

    public static final Creator<OBDCar> CREATOR = new Creator<OBDCar>() {
        @Override
        public OBDCar createFromParcel(Parcel in) {
            return new OBDCar(in);
        }

        @Override
        public OBDCar[] newArray(int size) {
            return new OBDCar[size];
        }
    };

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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

    public String getObdbh() {
        return obdbh;
    }

    public void setObdbh(String obdbh) {
        this.obdbh = obdbh;
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

    public String getSszz() {
        return sszz;
    }

    public void setSszz(String sszz) {
        this.sszz = sszz;
    }

    public String getZxcjsj() {
        return zxcjsj;
    }

    public void setZxcjsj(String zxcjsj) {
        this.zxcjsj = zxcjsj;
    }

    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getPfbz() {
        return pfbz;
    }

    public void setPfbz(String pfbz) {
        this.pfbz = pfbz;
    }

    public String getSbzt() {
        return sbzt;
    }

    public void setSbzt(String sbzt) {
        this.sbzt = sbzt;
    }

    public String getRlzl() {
        return rlzl;
    }

    public void setRlzl(String rlzl) {
        this.rlzl = rlzl;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public Object getZccarInfo() {
        return zccarInfo;
    }

    public void setZccarInfo(Object zccarInfo) {
        this.zccarInfo = zccarInfo;
    }

    public String getZdpp() {
        return zdpp;
    }

    public void setZdpp(String zdpp) {
        this.zdpp = zdpp;
    }

    public String getClxh() {
        return clxh;
    }

    public void setClxh(String clxh) {
        this.clxh = clxh;
    }

    public String getFdjxh() {
        return fdjxh;
    }

    public void setFdjxh(String fdjxh) {
        this.fdjxh = fdjxh;
    }

    public String getClscrq() {
        return clscrq;
    }

    public void setClscrq(String clscrq) {
        this.clscrq = clscrq;
    }

    public String getNsxrj() {
        return nsxrj;
    }

    public void setNsxrj(String nsxrj) {
        this.nsxrj = nsxrj;
    }

    public String getClsccj() {
        return clsccj;
    }

    public void setClsccj(String clsccj) {
        this.clsccj = clsccj;
    }

    public String getFdjgl() {
        return fdjgl;
    }

    public void setFdjgl(String fdjgl) {
        this.fdjgl = fdjgl;
    }

    public String getJzzl() {
        return jzzl;
    }

    public void setJzzl(String jzzl) {
        this.jzzl = jzzl;
    }

    public String getClsyr() {
        return clsyr;
    }

    public void setClsyr(String clsyr) {
        this.clsyr = clsyr;
    }

    public String getLxhm() {
        return lxhm;
    }

    public void setLxhm(String lxhm) {
        this.lxhm = lxhm;
    }

    public String getZcd() {
        return zcd;
    }

    public void setZcd(String zcd) {
        this.zcd = zcd;
    }

    public String getQyxz() {
        return qyxz;
    }

    public void setQyxz(String qyxz) {
        this.qyxz = qyxz;
    }

    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
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

    public String getFrdb() {
        return frdb;
    }

    public void setFrdb(String frdb) {
        this.frdb = frdb;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(searchValue);
        dest.writeString(createBy);
        dest.writeString(createTime);
        dest.writeString(updateBy);
        dest.writeString(updateTime);
        dest.writeString(obdbh);
        dest.writeString(vin);
        dest.writeString(hphm);
        dest.writeString(sszz);
        dest.writeString(zxcjsj);
        dest.writeString(clzt);
        dest.writeString(cllx);
        dest.writeString(pfbz);
        dest.writeString(sbzt);
        dest.writeString(rlzl);
        dest.writeString(del_flag);
        dest.writeString(zdpp);
        dest.writeString(clxh);
        dest.writeString(fdjxh);
        dest.writeString(clscrq);
        dest.writeString(nsxrj);
        dest.writeString(clsccj);
        dest.writeString(fdjgl);
        dest.writeString(jzzl);
        dest.writeString(clsyr);
        dest.writeString(lxhm);
        dest.writeString(zcd);
        dest.writeString(qyxz);
        dest.writeString(jgdm);
        dest.writeString(frdb);
    }


    public static class ParamsBean {
    }
}

