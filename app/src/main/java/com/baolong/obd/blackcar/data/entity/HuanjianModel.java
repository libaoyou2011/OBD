package com.baolong.obd.blackcar.data.entity;

import java.io.Serializable;

public class HuanjianModel implements Serializable {
    private static final long serialVersionUID = -8934323707008090415L;

    /*{
            "testNo":"3205002170420181031161655",
            "monitorOrgId":"394431585803305058",
            "monitorOrgName":"合肥市冠联机动车检测公司",
            "testlineNo":null,
            "hphm":"桂ALK643",
            "hpys":"0",
            "monitorType":null,
            "testType":"5",
            "testTimes":"3",
            "monitorPerson":"",
            "testDate":"2018-10-31 17:45:45",
            "testDateEnd":"2018-10-31 18:15:50",
            "result":"0",
            "testDateKS":null,
            "testDateJS":null
    }*/

    private String testNo;
    private String monitorOrgId;
    private String monitorOrgName;
    private Object testlineNo;
    private String hphm;
    private String hpys;
    private Object monitorType;
    private String testType;
    private String testTimes;
    private String monitorPerson;
    private String testDate;
    private String testDateEnd;
    private String result;
    private Object testDateKS;
    private Object testDateJS;

    public String getTestNo() {
        return testNo;
    }

    public void setTestNo(String testNo) {
        this.testNo = testNo;
    }

    public String getMonitorOrgId() {
        return monitorOrgId;
    }

    public void setMonitorOrgId(String monitorOrgId) {
        this.monitorOrgId = monitorOrgId;
    }

    public String getMonitorOrgName() {
        return monitorOrgName;
    }

    public void setMonitorOrgName(String monitorOrgName) {
        this.monitorOrgName = monitorOrgName;
    }

    public Object getTestlineNo() {
        return testlineNo;
    }

    public void setTestlineNo(Object testlineNo) {
        this.testlineNo = testlineNo;
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

    public Object getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(Object monitorType) {
        this.monitorType = monitorType;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestTimes() {
        return testTimes;
    }

    public void setTestTimes(String testTimes) {
        this.testTimes = testTimes;
    }

    public String getMonitorPerson() {
        return monitorPerson;
    }

    public void setMonitorPerson(String monitorPerson) {
        this.monitorPerson = monitorPerson;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getTestDateEnd() {
        return testDateEnd;
    }

    public void setTestDateEnd(String testDateEnd) {
        this.testDateEnd = testDateEnd;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getTestDateKS() {
        return testDateKS;
    }

    public void setTestDateKS(Object testDateKS) {
        this.testDateKS = testDateKS;
    }

    public Object getTestDateJS() {
        return testDateJS;
    }

    public void setTestDateJS(Object testDateJS) {
        this.testDateJS = testDateJS;
    }
}
