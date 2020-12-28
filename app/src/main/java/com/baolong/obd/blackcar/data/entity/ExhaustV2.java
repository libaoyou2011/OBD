package com.baolong.obd.blackcar.data.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Deprecated //V2超标返回列表中的 item
public class ExhaustV2 implements Serializable {
    private static final long serialVersionUID = -1763732966836230161L;
    //(name="记录编号")     (-0-)
    private String jlbh;
    //(name="点位编号")   (-1-)
    private String dwbh;
    //(name="遥测线编号")
    private String ycxbh;
    //(name="监测点位日志号")
    private String jcdwrzh;
    //(name="监测人员姓名")
    private String jcryxm;
    //(name="车道序号")     (-3-)
    private int cdxh;
    //(name="监测时间"  ,width=20,StringFormat="yyyy-MM-dd HH:mm:ss")   (-2-)
    private String jcrq;
    //(name="地点经度")
    private double ddjd;
    //(name="地点纬度")
    private double ddwd;
    //(name="车道坡度")         (-4-)
    private double cdpd;
    //(name="判定结果")  0：不合格  1:合格   2：无效
    private String pdjg;
    //(name="无效原因")
    private String wxyy;
    //(name="号牌号码")     (-5-)
    private String hphm;
    //(name="车牌颜色") 0-蓝牌 1-黄牌 2-白牌 3-黑牌  (-6-)
    private String cpys;
    //(name="号牌种类")
    private String hpzl;
    //(name="车身颜色")
    private String csys;
    //(name="识别置信度")
    private double sbzxd;
    //(name="燃料种类")  A-汽油 B-柴油 Z-其他
    private String rlzl;
    //(name="CO2结果")
    private double co2jg;

    private double coco2;    // CO/CO2 比率

    private double hcco2;   // HC/CO2 比率

    private double noco2;   // NO/CO2 比率
    //(name="CO结果")
    private double cojg;
    //(name="HC结果")
    private double hcjg;
    //(name="NO结果")
    private double nojg;
    //(name="CO2实测值")                    (-8-)
    private double co2scz;
    //(name="CO实测值")
    private double coscz;
    //(name="HC实测值")
    private double hcscz;
    //(name="NO实测值")
    private double noscz;
    //(name="不透光度结果")       (-10-)
    private double btgdjg;
    //(name="林格曼黑度")        (-7-)       (-12-)
    private int lgmhd;

    private double btgdpj;

    private double btgdzd;

    private double btgdzx;

    private double btgxs;

    private double no2pj;

    private double no2zd;

    private double no2zx;
    //(name="CO2限值")
    private double co2xz;
    //(name="CO限值")
    private double coxz;
    //(name="NO限值")
    private double noxz;
    //(name="HC限值")
    private double hcxz;
    //(name="不透光度限值")       (-9-)
    private double btgdxz;
    //(name="黑度限值")     (-11-)
    private int hdxz;
    //(name="不透光系数限值")
    private double btgxsxz;
    //(name="车辆速度")
    private double clsd;
    //(name="车辆加速度")
    private double cljsd;
    //(name="VSP")
    private double vsp;
    //(name="风速")
    private double fs;
    //(name="风向")
    private String fx;
    //(name="环境温度")
    private double hjwd;
    //(name="湿度")
    private double sd;
    //(name="大气压")
    private double dqy;

    private String gjxxbh;

    private String tp1;

    private String tp2;

    private String sp1;

    private String tp3;

    private String tp4;

    private String tp5;

    private String judge; // 超标次数判断

    private String number; // 超标次数
    //(pattern="yyyy-MM-dd HH:mm:ss")
    private String beginTime; // 开始日期
    //(pattern="yyyy-MM-dd HH:mm:ss")
    private String endTime; // 结束日期

    private String clsdstatus; // 车辆速度状态：速度判断

    private String jsdstatus; // 加速度状态：加速度判断

    private String fsstatus; // 风速判断

    private String hjwdstatus; //环境温度判断

    private String sdstatus; // 湿度判断

    private String dqystatus; // 大气压判断

    private String fxstatus; // 风向判断

    private String vspstatus;  // vsp判断

    private String cojgstatus; // cojg判断

    private String hcjgstatus; // hcjg判断

    private String nojgstatus; // nojg判断

    private String co2jgstatus; // co2jg判断

    private SiteInfo siteInfo;

    private SiteTeleLineInfo siteTeleLineInfo;

    private String placeBelong;//车辆归属地

    private String localHphm;//本地号牌

    private int isBlackCar;// 是否黑烟车  0:不是黑烟车 1：是黑烟车

    private int isornoupString;// 是否更新到中间表

    private int blackSmokeReview;// 是否经过黑烟车复检 0:未审核 1:人工审核 2:其他审核

    private List<Map<String, String>> carList; // 车辆列表，此项不为null是将 hphm 和hpys 置空

    private int isornopunish;

    private String year;//根据年份统计


    public String getTp5() {
        return tp5;
    }

    public void setTp5(String tp5) {
        this.tp5 = tp5;
    }

    public int getIsornoupString() {
        return isornoupString;
    }

    public void setIsornoupString(int isornoupString) {
        this.isornoupString = isornoupString;
    }

    public int getIsornopunish() {
        return isornopunish;
    }

    public void setIsornopunish(int isornopunish) {
        this.isornopunish = isornopunish;
    }

    public List<Map<String, String>> getCarList() {
        return carList;
    }

    public void setCarList(List<Map<String, String>> carList) {
        this.carList = carList;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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

    public SiteTeleLineInfo getSiteTeleLineInfo() {
        return siteTeleLineInfo;
    }

    public void setSiteTeleLineInfo(SiteTeleLineInfo siteTeleLineInfo) {
        this.siteTeleLineInfo = siteTeleLineInfo;
    }


    public int getIsBlackCar() {
        return isBlackCar;
    }

    public void setIsBlackCar(int isBlackCar) {
        this.isBlackCar = isBlackCar;
    }

    public int getBlackSmokeReview() {
        return blackSmokeReview;
    }

    public void setBlackSmokeReview(int blackSmokeReview) {
        this.blackSmokeReview = blackSmokeReview;
    }

    public String getPlaceBelong() {
        return placeBelong;
    }

    public void setPlaceBelong(String placeBelong) {
        this.placeBelong = placeBelong;
    }

    public String getLocalHphm() {
        return localHphm;
    }

    public void setLocalHphm(String localHphm) {
        this.localHphm = localHphm;
    }


    public SiteInfo getSiteInfo() {
        return siteInfo;
    }

    public void setSiteInfo(SiteInfo siteInfo) {
        this.siteInfo = siteInfo;
    }


    public String getClsdstatus() {
        return clsdstatus;
    }

    public void setClsdstatus(String clsdstatus) {
        this.clsdstatus = clsdstatus;
    }

    public String getJsdstatus() {
        return jsdstatus;
    }

    public void setJsdstatus(String jsdstatus) {
        this.jsdstatus = jsdstatus;
    }

    public String getFsstatus() {
        return fsstatus;
    }

    public void setFsstatus(String fsstatus) {
        this.fsstatus = fsstatus;
    }

    public String getHjwdstatus() {
        return hjwdstatus;
    }

    public void setHjwdstatus(String hjwdstatus) {
        this.hjwdstatus = hjwdstatus;
    }

    public String getSdstatus() {
        return sdstatus;
    }

    public void setSdstatus(String sdstatus) {
        this.sdstatus = sdstatus;
    }

    public String getDqystatus() {
        return dqystatus;
    }

    public void setDqystatus(String dqystatus) {
        this.dqystatus = dqystatus;
    }

    public String getFxstatus() {
        return fxstatus;
    }

    public void setFxstatus(String fxstatus) {
        this.fxstatus = fxstatus;
    }

    public String getVspstatus() {
        return vspstatus;
    }

    public void setVspstatus(String vspstatus) {
        this.vspstatus = vspstatus;
    }

    public String getCojgstatus() {
        return cojgstatus;
    }

    public void setCojgstatus(String cojgstatus) {
        this.cojgstatus = cojgstatus;
    }

    public String getHcjgstatus() {
        return hcjgstatus;
    }

    public void setHcjgstatus(String hcjgstatus) {
        this.hcjgstatus = hcjgstatus;
    }

    public String getNojgstatus() {
        return nojgstatus;
    }

    public void setNojgstatus(String nojgstatus) {
        this.nojgstatus = nojgstatus;
    }

    public String getCo2jgstatus() {
        return co2jgstatus;
    }

    public void setCo2jgstatus(String co2jgstatus) {
        this.co2jgstatus = co2jgstatus;
    }

    public String getJlbh() {
        return jlbh;
    }

    public void setJlbh(String jlbh) {
        this.jlbh = jlbh == null ? null : jlbh.trim();
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

    public String getJcdwrzh() {
        return jcdwrzh;
    }

    public void setJcdwrzh(String jcdwrzh) {
        this.jcdwrzh = jcdwrzh == null ? null : jcdwrzh.trim();
    }

    public String getJcryxm() {
        return jcryxm;
    }

    public void setJcryxm(String jcryxm) {
        this.jcryxm = jcryxm == null ? null : jcryxm.trim();
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
        this.pdjg = pdjg == null ? null : pdjg.trim();
    }

    public String getWxyy() {
        return wxyy;
    }

    public void setWxyy(String wxyy) {
        this.wxyy = wxyy == null ? null : wxyy.trim();
    }

    public String getHphm() {
        return hphm;
    }

    public void setCphm(String hphm) {
        this.hphm = hphm == null ? null : hphm.trim();
    }

    public String getCpys() {
        return cpys;
    }

    public void setCpys(String cpys) {
        this.cpys = cpys == null ? null : cpys.trim();
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl == null ? null : hpzl.trim();
    }

    public String getCsys() {
        return csys;
    }

    public void setCsys(String csys) {
        this.csys = csys == null ? null : csys.trim();
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
        this.rlzl = rlzl == null ? null : rlzl.trim();
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

    public double getBtgxs() {
        return btgxs;
    }

    public void setBtgxs(double btgxs) {
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
        this.fx = fx == null ? null : fx.trim();
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
        this.gjxxbh = gjxxbh == null ? null : gjxxbh.trim();
    }

    public String getTp1() {
        return tp1;
    }

    public void setTp1(String tp1) {
        this.tp1 = tp1 == null ? null : tp1.trim();
    }

    public String getTp2() {
        return tp2;
    }

    public void setTp2(String tp2) {
        this.tp2 = tp2 == null ? null : tp2.trim();
    }

    public String getSp1() {
        return sp1;
    }

    public void setSp1(String sp1) {
        this.sp1 = sp1 == null ? null : sp1.trim();
    }

    public String getTp3() {
        return tp3;
    }

    public void setTp3(String tp3) {
        this.tp3 = tp3 == null ? null : tp3.trim();
    }

    public String getTp4() {
        return tp4;
    }

    public void setTp4(String tp4) {
        this.tp4 = tp4 == null ? null : tp4.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}