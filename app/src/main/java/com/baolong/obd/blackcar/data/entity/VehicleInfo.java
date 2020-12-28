package com.baolong.obd.blackcar.data.entity;

import java.io.Serializable;

/**
 *车辆信息库对象
 */
public class VehicleInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /* {
            "searchValue":null,
            "createBy":null,
            "createTime":null,
            "updateBy":null,
            "updateTime":null,
            "remark":null,
            "params":{    },
             "delFlag":null,
            "id":"34",
            "hphm":"皖B20101",  1：号牌号码
            "xzqhdm":"3401",    2：行政区划代码
            "hpys":"1",         3：号牌颜色 ("0=蓝牌,1=黄牌,2=白牌,3=黑牌,4=绿牌")
            "hpzl":"大型汽车",
            "clxh":"",          4：车辆型号
            "scqy":"",          7.生产企业
            "rlzl":"A",         5：燃料种类 （A=汽油、B=柴油、Z=其他）
            "syxz":"C",         6：使用性质
            "ccdjrq":"2019-12-11",  8：初次等级日期
            "clsbdh":"",        9：车辆识别代号
            "pfbzjd":"1",       10：排放标准阶段 (ABCDEFG....)
            "scjyrq":null,      11：上次环保定期检验日期
            "scjyjg":"",        12：上次环保定期检验结果
            "placeBelong":null,
            "beginTime":null,
            "endTime":null
    }*/

    private String id;

    private String hphm;// 号牌号码

    private String xzqhdm;// 行政区划代码

    private String hpys;// 号牌颜色 ("0=蓝牌,1=黄牌,2=白牌,3=黑牌,4=绿牌")

    private String hpzl;// 号牌种类

    private String clxh;// 车辆型号

    private String scqy;// 生产企业

    private String rlzl;// 燃料种类  ("A=汽油,B=柴油,Z=其他")

    private String syxz;// 使用性质  ("A=非营运,B=公路客运,C=公交客运,D=出租客运,E=旅游客运,F=货运,G=租赁,H=警用,I=消防,J=救护,K=工程抢险,L=营转非,M=出租转非,N=教练,O=幼儿校车,P=小学生校车,Q=其他校车,R=危化品运输,Z=其他")

    private String ccdjrq;// 初次登记日期  ("yyyy-MM-dd HH:mm:ss")

    private String clsbdh;// 车辆识别代号

    private String pfbzjd;// 排放标准阶段  ("0=国〇,1=国Ⅰ,2=国Ⅱ,3=国Ⅲ,4=国Ⅳ,5=国Ⅴ,6=国Ⅵ")

    private String scjyrq;// 上次环保定期检验日期

    private String scjyjg;// 上次环保定期检验结果

    private String beginTime;

    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXzqhdm() {
        return xzqhdm;
    }

    public void setXzqhdm(String xzqhdm) {
        this.xzqhdm = xzqhdm;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getHpys() {
        return hpys;
    }

    public void setHpys(String hpys) {
        this.hpys = hpys;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getClxh() {
        return clxh;
    }

    public void setClxh(String clxh) {
        this.clxh = clxh;
    }

    public String getScqy() {
        return scqy;
    }

    public void setScqy(String scqy) {
        this.scqy = scqy;
    }

    public String getRlzl() {
        return rlzl;
    }

    public void setRlzl(String rlzl) {
        this.rlzl = rlzl;
    }

    public String getSyxz() {
        return syxz;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz;
    }

    public String getCcdjrq() {
        return ccdjrq;
    }

    public void setCcdjrq(String ccdjrq) {
        this.ccdjrq = ccdjrq;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getPfbzjd() {
        return pfbzjd;
    }

    public void setPfbzjd(String pfbzjd) {
        this.pfbzjd = pfbzjd;
    }

    public String getScjyrq() {
        return scjyrq;
    }

    public void setScjyrq(String scjyrq) {
        this.scjyrq = scjyrq;
    }

    public String getScjyjg() {
        return scjyjg;
    }

    public void setScjyjg(String scjyjg) {
        this.scjyjg = scjyjg;
    }

}
