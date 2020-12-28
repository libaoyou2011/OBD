package com.baolong.obd.monitor.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StationInfoFactory {

    private List<StationInfo> stationInfoList = new ArrayList<StationInfo>();

    public StationInfoFactory() {
    }

    public StationInfoFactory(List<StationInfo> stationInfoList) {
        this.stationInfoList = stationInfoList;
    }

    public List<StationInfo> createData(){
        stationInfoList.add(createOne());
        stationInfoList.add(createTwo());
        return stationInfoList;
    }

    public StationInfo createOne() {
        StationInfo item = new StationInfo();
        item.setId(100001);
        item.setJzbh("A410506001");
        item.setJzmc("文明大道");
        item.setJzlx("水平固定式");
        item.setJzrq(new Date());
        item.setJzzt("正常");
        item.setProvince("河北省");
        item.setCity("安阳市");
        item.setCounty("龙安区");
        item.setJzdz("彰德路与光明路路口");
        item.setDdjd(114.31);
        item.setDdwd(36.09);
        item.setFxlx("上行");
        item.setCdsl(2);
        item.setCdpd(0.01);
        item.setJzjcx(6);
        item.setHphm("jz-sb8888");
        item.setClxh("装载车类型");
        item.setFactory_number("设备出厂编号");
        item.setIp("网络IP");
        item.setPort("端口");
        item.setIs_shutdown(false);
        item.setCreate_by("创建者");
        item.setCreate_date(new Date());
        item.setUpdate_by("修改者");
        item.setUpdate_date(new Date());
        item.setDel_flag(false);
        item.setRemarks("备注");

        return item;
    }

    public StationInfo createTwo() {
        StationInfo item = new StationInfo();
        item.setId(100002);
        item.setJzbh("B410502001");
        item.setJzmc("彰德路");
        item.setJzlx("垂直固定式");
        item.setJzrq(new Date());
        item.setJzzt("正常");
        item.setProvince("河北省");
        item.setCity("安阳市");
        item.setCounty("龙安区");
        item.setJzdz("彰德路与光明路路口");
        item.setDdjd(114.25);
        item.setDdwd(36.09);
        item.setFxlx("下行");
        item.setCdsl(3);
        item.setCdpd(0.01);
        item.setJzjcx(6);
        item.setHphm("jz-sb8888");
        item.setClxh("装载车类型");
        item.setFactory_number("设备出厂编号");
        item.setIp("网络IP");
        item.setPort("端口");
        item.setIs_shutdown(false);
        item.setCreate_by("创建者");
        item.setCreate_date(new Date());
        item.setUpdate_by("修改者");
        item.setUpdate_date(new Date());
        item.setDel_flag(false);
        item.setRemarks("备注");

        return item;
    }
}
