package com.baolong.obd.analysis.data.entity;

import java.io.Serializable;

public class DataModel implements Serializable{
    private static final long serialVersionUID = -1;

    /**
     * proportion : 0.595956
     * datavolume : 1699357
     * dataname : 有效数据
     */

    private double proportion;
    private int datavolume;
    private String dataname;

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public int getDatavolume() {
        return datavolume;
    }

    public void setDatavolume(int datavolume) {
        this.datavolume = datavolume;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }
}
