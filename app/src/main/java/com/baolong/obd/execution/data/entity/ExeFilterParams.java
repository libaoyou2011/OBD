package com.baolong.obd.execution.data.entity;


//import java.math.BigDecimal;

import android.text.TextUtils;

import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;

import java.util.List;

// 黑烟车筛选条件类
public class ExeFilterParams {
    private final List<FilterCategoryModel> filterCategoryModelList;
    private String dwbh;   //1.点位编号
    private String cpys;   //2.车牌颜色
    private String hphm;   //3.号牌号码
    private String clgs;   //4.车辆归属
    private String rlzl;   //5.燃料种类
    private String pdjg;   //6.判定结果  (实时有、 未处罚No 、已处罚No)

    private String cojgysf;   //7--.CO结果
    private Float cojg;

    private String nojgysf;   //8--.NO结果
    private Float nojg;

    private String hcjgysf;   //9--.HC结果
    private Float hcjg;

    private String btgdjgysf;   //10--.不透光度结果
    private Float btgdjg;

    private String co2sczysf;   //11--.CO2实测值
    private Float co2scz;

    private String fsysf;   //12--.风速
    private Float fs;

    private String vspysf;   //13--.VSP
    private Float vsp;

    private String hjwdysf;   //14--.环境温度
    private Float hjwd;

    private String sdysf;   //15--.湿度
    private Float sd;

    private String dqyysf;   //16--.大气压
    private Float dqy;

    private String cbcsysf;   //17--.超标次数 (实时_有、未处罚_有 、已处罚_NO)
    private Integer cbcs;

    private String beginTime;   //18--.监测时间
    private String endTime;

    public ExeFilterParams(final List<FilterCategoryModel> filterCategoryModelList) {
        this.filterCategoryModelList = filterCategoryModelList;
    }

    public ExeFilterParams init() {
        for (int i = 0; i < this.filterCategoryModelList.size(); ++i) {
            //1.点位编号
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1001")) {
                for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                    if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                        this.dwbh = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                        break;
                    }
                }
            }
            //2.车牌颜色
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1002")) {
                for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                    if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                        this.cpys = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                        break;
                    }
                }
            }
            //3.号牌号码
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1003")) {
                if (!TextUtils.isEmpty(this.filterCategoryModelList.get(i).getInputValue())){
                    this.hphm = this.filterCategoryModelList.get(i).getInputValue();
                }
            }
            //4.车辆归属
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1004")) {
                for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                    if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                        this.clgs = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                        break;
                    }
                }
            }
            //5.燃料种类
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1005")) {
                for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                    if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                        this.rlzl = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                        break;
                    }
                }
            }
            //6.判定结果  (实时有、 未处罚No 、已处罚No)
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1006")) {
                for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                    if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                        this.pdjg = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                        break;
                    }
                }
            }
            //7.CO结果
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1007")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.cojgysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.cojg = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //8--.NO结果
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1008")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.nojgysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.nojg = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //9--.HC结果
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1009")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.hcjgysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.hcjg = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //10--.不透光度结果
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1010")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.btgdjgysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.btgdjg = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //11--.CO2实测值
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1011")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.co2sczysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.co2scz = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //12--.风速
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1012")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.fsysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.fs = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //13--.VSP
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1013")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.vspysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.vsp = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //14--.环境温度
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1014")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.hjwdysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.hjwd = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //15--.湿度
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1015")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.sdysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.sd = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //16--.大气压
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1016")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.dqyysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            this.dqy = Float.parseFloat(this.filterCategoryModelList.get(i).getInputValue());
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //17--.超标次数 (实时有、未处罚有 、已处罚NO)
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1017")) {
                try {
                    for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); ++j) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).isSelected()) {
                            this.cbcsysf = ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).getSubCategoryCode();
                            if (this.filterCategoryModelList.get(i).getInputValue().length() > 0) {
                                this.cbcs = Integer.parseInt(this.filterCategoryModelList.get(i).getInputValue());
                            }
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // NumberFormatException
                }
            }
            //18--.监测时间
            if (this.filterCategoryModelList.get(i).getCategoryCode().equals("1018")) {
                if (!TextUtils.isEmpty(this.filterCategoryModelList.get(i).getBeginTime())) {
                    this.beginTime = this.filterCategoryModelList.get(i).getBeginTime();
                }
                if (!TextUtils.isEmpty(this.filterCategoryModelList.get(i).getEndTime()) ) {
                    this.endTime = this.filterCategoryModelList.get(i).getEndTime();
                }
            }
        }
        return this;
    }


    public String getDwbh() {
        return dwbh;
    }

    public String getCpys() {
        return cpys;
    }

    public String getHphm() {
        return hphm;
    }

    public String getClgs() {
        return clgs;
    }

    public String getRlzl() {
        return rlzl;
    }

    public String getPdjg() {
        return pdjg;
    }

    public String getCojgysf() {
        return cojgysf;
    }

    public Float getCojg() {
        return cojg;
    }

    public String getNojgysf() {
        return nojgysf;
    }

    public Float getNojg() {
        return nojg;
    }

    public String getHcjgysf() {
        return hcjgysf;
    }

    public Float getHcjg() {
        return hcjg;
    }

    public String getBtgdjgysf() {
        return btgdjgysf;
    }

    public Float getBtgdjg() {
        return btgdjg;
    }

    public String getCo2sczysf() {
        return co2sczysf;
    }

    public Float getCo2scz() {
        return co2scz;
    }

    public String getFsysf() {
        return fsysf;
    }

    public Float getFs() {
        return fs;
    }

    public String getVspysf() {
        return vspysf;
    }

    public Float getVsp() {
        return vsp;
    }

    public String getHjwdysf() {
        return hjwdysf;
    }

    public Float getHjwd() {
        return hjwd;
    }

    public String getSdysf() {
        return sdysf;
    }

    public Float getSd() {
        return sd;
    }

    public String getDqyysf() {
        return dqyysf;
    }

    public Float getDqy() {
        return dqy;
    }

    public String getCbcsysf() {
        return cbcsysf;
    }

    public Integer getCbcs() {
        return cbcs;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
