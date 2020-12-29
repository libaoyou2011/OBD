package com.baolong.obd.analysis.data.entity;

import java.io.Serializable;

public class CarNumModel implements Serializable{
    private static final long serialVersionUID = -8182765717400338957L;
    /**
     * total : 201135
     * lx : 黄牌车
     * cbl : 6.07
     * cb : 12204
     */

    private String total;
    private String lx;
    private String cbl;
    private String cb;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getCbl() {
        return cbl;
    }

    public void setCbl(String cbl) {
        this.cbl = cbl;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }
}
