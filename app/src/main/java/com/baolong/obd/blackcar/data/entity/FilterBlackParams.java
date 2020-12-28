package com.baolong.obd.blackcar.data.entity;


//import java.math.BigDecimal;
import android.text.TextUtils;

import java.util.List;

// 黑烟车筛选条件类
public class FilterBlackParams {
    private final List<FilterCategoryModel> filterCategoryModelList;
    private String dwbh;   //1.点位编号
    private String cpys;   //2.车牌颜色
    private String hphm;   //3.号牌号码
    private String clgs;   //4.车辆归属
    private String rlzl;   //5.燃料种类
    private String pdjg;   //6.判定结果  (实时有、待审核No 、已审核No、已移交No)
    private String shzt;   //7.审核状态

    private String lgmhdysf;   //8--.林格曼黑度
    private Integer lgmhd;

    private String sbzxdysf;   //9--.识别置信度
    private Float sbzxd;

    private String cbcsysf;   //10--.超标次数 (实时_有、待审核_有、已审核_NO、已移交_NO)
    private Integer cbcs;

    private String beginTime;   //11--.监测时间
    private String endTime;

    private String sjd;   //12.时间段  白天6;17  晚上18;23 早高峰7;8  晚高峰18;19

    public FilterBlackParams(final List<FilterCategoryModel> filterCategoryModelList) {
        this.filterCategoryModelList = filterCategoryModelList;
    }

    public FilterBlackParams init() {
        for (int i = 0; i < this.filterCategoryModelList.size(); ++i) {

            switch (this.filterCategoryModelList.get(i).getCategoryCode()){
                case "1001":
                    //1.点位编号
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.dwbh = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            break;
                        }
                    }
                    break;
                case "1002":
                    //2.车牌颜色
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.cpys = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            break;
                        }
                    }
                    break;
                case "1003":
                    //3.号牌号码
                    if (!TextUtils.isEmpty(this.filterCategoryModelList.get(i).getInputValue())){
                        this.hphm = this.filterCategoryModelList.get(i).getInputValue();
                    }
                    break;
                case "1004":
                    //4.车辆归属
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.clgs = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            break;
                        }
                    }
                    break;
                case "1005":
                    //5.燃料种类
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.rlzl = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            break;
                        }
                    }
                    break;
                case "1006":
                    //6.判定结果  (实时有、待审核No 、已审核No、已移交No)
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.pdjg = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            break;
                        }
                    }
                    break;
                case "1007":
                    //7.审核状态
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.shzt = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            break;
                        }
                    }
                    break;
                case "1008":
                    //8--.林格曼黑度
                    try {
                        for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                            if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                                this.lgmhdysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                                this.lgmhd = Integer.valueOf(this.filterCategoryModelList.get(i).getInputValue());
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace(); // NumberFormatException
                    }
                    break;
                case "1009":
                    //9--.识别置信度
                    try {
                        for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                            if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                                this.sbzxdysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                                this.sbzxd = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace(); // NumberFormatException
                    }
                    break;
                case "1010":
                    //10--.超标次数 (实时有、待审核有 、已审核NO、已移交NO)
                    try {
                        for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                            if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                                this.cbcsysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                                this.cbcs = Integer.valueOf(this.filterCategoryModelList.get(i).getInputValue());
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace(); // NumberFormatException
                    }
                    break;
                case "1011":
                    //11--.监测时间
                    if (!TextUtils.isEmpty(this.filterCategoryModelList.get(i).getBeginTime())){
                        this.beginTime = this.filterCategoryModelList.get(i).getBeginTime();
                    }
                    if (!TextUtils.isEmpty(this.filterCategoryModelList.get(i).getEndTime())){
                        this.endTime = this.filterCategoryModelList.get(i).getEndTime();
                    }
                    break;
                case "1012":
                    //12.时间段  白天6;17  晚上18;23 早高峰7;8  晚高峰18;19
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.sjd = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return this;
    }


    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getCpys() {
        return cpys;
    }

    public void setCpys(String cpys) {
        this.cpys = cpys;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getClgs() {
        return clgs;
    }

    public void setClgs(String clgs) {
        this.clgs = clgs;
    }

    public String getRlzl() {
        return rlzl;
    }

    public void setRlzl(String rlzl) {
        this.rlzl = rlzl;
    }

    public String getPdjg() {
        return pdjg;
    }

    public void setPdjg(String pdjg) {
        this.pdjg = pdjg;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getLgmhdysf() {
        return lgmhdysf;
    }

    public void setLgmhdysf(String lgmhdysf) {
        this.lgmhdysf = lgmhdysf;
    }

    public Integer getLgmhd() {
        return lgmhd;
    }

    public void setLgmhd(Integer lgmhd) {
        this.lgmhd = lgmhd;
    }

    public String getSbzxdysf() {
        return sbzxdysf;
    }

    public void setSbzxdysf(String sbzxdysf) {
        this.sbzxdysf = sbzxdysf;
    }

    public Float getSbzxd() {
        return sbzxd;
    }

    public void setSbzxd(Float sbzxd) {
        this.sbzxd = sbzxd;
    }

    public String getCbcsysf() {
        return cbcsysf;
    }

    public void setCbcsysf(String cbcsysf) {
        this.cbcsysf = cbcsysf;
    }

    public Integer getCbcs() {
        return cbcs;
    }

    public void setCbcs(Integer cbcs) {
        this.cbcs = cbcs;
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

    public String getSjd() {
        return sjd;
    }

    public void setSjd(String sjd) {
        this.sjd = sjd;
    }
}
