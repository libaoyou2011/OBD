package com.baolong.obd.blackcar.data.entity;

import java.io.Serializable;
import java.util.List;

@Deprecated //V2超标返回列表
public class ResponseExhaustListModel implements Serializable {
    private static final long serialVersionUID = -6062624703943438700L;
    private  int total;
    private List<Exhaust> rows;
    private int pageNum;
    private  int code;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Exhaust> getRows() {
        return rows;
    }

    public void setRows(List<Exhaust> rows) {
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
