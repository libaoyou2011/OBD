package com.baolong.obd.blackcar.data.entity;

import java.io.Serializable;
import java.util.List;

public class ResponseVehicleInfoListModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private  int total;
    private List<VehicleInfo> rows;
    private int pageNum;
    private  int code;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<VehicleInfo> getRows() {
        return rows;
    }

    public void setRows(List<VehicleInfo> rows) {
        this.rows = rows;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
