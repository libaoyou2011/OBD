package com.baolong.obd.analysis.data.entity;

import java.io.Serializable;
import java.util.List;

public class CarFlowModel implements Serializable {
    private static final long serialVersionUID = -1;

    private List<Integer> dakeche;
    private List<Integer> dahuoche;
    private List<Integer> num;
    private List<Integer> midkeche;
    private List<Integer> miaobaoche;
    private List<Integer> smallhuoche;
    private List<Integer> jiaoche;

    public List<Integer> getDakeche() {
        return dakeche;
    }

    public void setDakeche(List<Integer> dakeche) {
        this.dakeche = dakeche;
    }

    public List<Integer> getDahuoche() {
        return dahuoche;
    }

    public void setDahuoche(List<Integer> dahuoche) {
        this.dahuoche = dahuoche;
    }

    public List<Integer> getNum() {
        return num;
    }

    public void setNum(List<Integer> num) {
        this.num = num;
    }

    public List<Integer> getMidkeche() {
        return midkeche;
    }

    public void setMidkeche(List<Integer> midkeche) {
        this.midkeche = midkeche;
    }

    public List<Integer> getMiaobaoche() {
        return miaobaoche;
    }

    public void setMiaobaoche(List<Integer> miaobaoche) {
        this.miaobaoche = miaobaoche;
    }

    public List<Integer> getSmallhuoche() {
        return smallhuoche;
    }

    public void setSmallhuoche(List<Integer> smallhuoche) {
        this.smallhuoche = smallhuoche;
    }

    public List<Integer> getJiaoche() {
        return jiaoche;
    }

    public void setJiaoche(List<Integer> jiaoche) {
        this.jiaoche = jiaoche;
    }
}
