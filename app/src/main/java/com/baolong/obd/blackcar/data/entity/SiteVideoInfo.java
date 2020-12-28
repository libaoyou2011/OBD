package com.baolong.obd.blackcar.data.entity;

public class SiteVideoInfo {

    /** 老版本
     * id : 23
     * lineId : null
     * siteId : A130900006
     * siteInfo : {"dwbh":null,"dwmc":null,"dwlx":null,"yxrq":null,"dwzt":null,"dwdz":null,"ddjd":null,"ddwd":null,"clfx":null,"cdsl":null,"cdpd":null,"ycxs":null,"hphm":null,"clxh":null,"isonline":null,"ip":"221.195.144.71","port":"48080","factorynumber":null,"lowout":null,"sfyhyc":null,"city":null,"county":null,"province":null,"monitoringLineList":[]}
     * province : 130000
     * city : 130900
     * county : 130902
     * alias : 胜达重工监控
     * cameraName : 胜达重工监控
     * userNameC : admin
     * passwordC : baolong12345
     * ip : 221.195.144.71
     * port : 48080
     * fwip : 221.195.144.71
     * fwport : 48080
     * status : 1
     * createBy : null
     * createDate : 2019-08-14 13:01:49
     * updateBy : null
     * updateDate : 2020-01-07 09:09:38
     * delFlag : 0
     * remarks : null
     * */


    /**
     * id : 7
     * siteInfo : {"dwbh":"B340104002","dwmc":"仰桥路水平式设备","dwlx":null,"yxrq":null,"dwzt":null,"dwdz":null,"ddjd":null,"ddwd":null,"sfysp":null,"clfx":null,"cdsl":null,"cdpd":null,"ycxs":null,"hphm":null,"clxh":null,"lwzt":null,"ip":"171.211.125.62","port":"90","ccbh":null,"dpqy":null,"sfyhyc":null,"city":"340100","county":"340104","province":"340000","del_flag":null,"address":null}
     * dwbh : B340104002
     * dwbhs : null
     * ycxbh : 01
     * qjmc : 广汉
     * username : admin
     * password : baolong12345
     * ip : 171.211.125.62
     * port : 90
     * fwip : null
     * fwport : null
     * status : 0
     * del_flag : null
     */

    private int id;
    private SiteInfoBean siteInfo;
    private String dwbh;
    private Object dwbhs;
    private String ycxbh;
    private String qjmc;
    private String username;
    private String password;
    private String ip;
    private String port;
    private String fwip;
    private String fwport;
    private String status;
    private Object del_flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getQjmc() {
        return qjmc;
    }

    public void setQjmc(String qjmc) {
        this.qjmc = qjmc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getFwip() {
        return fwip;
    }

    public void setFwip(String fwip) {
        this.fwip = fwip;
    }

    public String getFwport() {
        return fwport;
    }

    public void setFwport(String fwport) {
        this.fwport = fwport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Object del_flag) {
        this.del_flag = del_flag;
    }

    public class SiteInfoBean {
        /**
         * dwbh : B340104002
         * dwmc : 仰桥路水平式设备
         * dwlx : null
         * yxrq : null
         * dwzt : null
         * dwdz : null
         * ddjd : null
         * ddwd : null
         * sfysp : null
         * clfx : null
         * cdsl : null
         * cdpd : null
         * ycxs : null
         * hphm : null
         * clxh : null
         * lwzt : null
         * ip : 171.211.125.62
         * port : 90
         * ccbh : null
         * dpqy : null
         * sfyhyc : null
         * city : 340100
         * county : 340104
         * province : 340000
         * del_flag : null
         * address : null
         */

        private String dwbh;
        private String dwmc;
        private Object dwlx;
        private Object yxrq;
        private Object dwzt;
        private Object dwdz;
        private Object ddjd;
        private Object ddwd;
        private Object sfysp;
        private Object clfx;
        private Object cdsl;
        private Object cdpd;
        private Object ycxs;
        private Object hphm;
        private Object clxh;
        private Object lwzt;
        private String ip;
        private String port;
        private Object ccbh;
        private Object dpqy;
        private Object sfyhyc;
        private String city;
        private String county;
        private String province;
        private Object del_flag;
        private Object address;

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

        public Object getDwdz() {
            return dwdz;
        }

        public void setDwdz(Object dwdz) {
            this.dwdz = dwdz;
        }

        public Object getDdjd() {
            return ddjd;
        }

        public void setDdjd(Object ddjd) {
            this.ddjd = ddjd;
        }

        public Object getDdwd() {
            return ddwd;
        }

        public void setDdwd(Object ddwd) {
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

        public Object getCdpd() {
            return cdpd;
        }

        public void setCdpd(Object cdpd) {
            this.cdpd = cdpd;
        }

        public Object getYcxs() {
            return ycxs;
        }

        public void setYcxs(Object ycxs) {
            this.ycxs = ycxs;
        }

        public Object getHphm() {
            return hphm;
        }

        public void setHphm(Object hphm) {
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
    /**
     * id : 23
     * lineId : null
     * siteId : A130900006
     * siteInfo : {"dwbh":null,"dwmc":null,"dwlx":null,"yxrq":null,"dwzt":null,"dwdz":null,"ddjd":null,"ddwd":null,"clfx":null,"cdsl":null,"cdpd":null,"ycxs":null,"hphm":null,"clxh":null,"isonline":null,"ip":"221.195.144.71","port":"48080","factorynumber":null,"lowout":null,"sfyhyc":null,"city":null,"county":null,"province":null,"monitoringLineList":[]}
     * province : 130000
     * city : 130900
     * county : 130902
     * alias : 胜达重工监控
     * cameraName : 胜达重工监控
     * userNameC : admin
     * passwordC : baolong12345
     * ip : 221.195.144.71
     * port : 48080
     * fwip : 221.195.144.71
     * fwport : 48080
     * status : 1
     * createBy : null
     * createDate : 2019-08-14 13:01:49
     * updateBy : null
     * updateDate : 2020-01-07 09:09:38
     * delFlag : 0
     * remarks : null
     */


}
