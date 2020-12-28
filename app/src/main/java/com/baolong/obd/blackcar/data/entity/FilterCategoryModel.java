package com.baolong.obd.blackcar.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilterCategoryModel implements Serializable {
    private static final long serialVersionUID = -6764305631003216821L;
    private boolean canInput;
    private String inputValue;
    private boolean canInputTime;
    private String beginTime;
    private String endTime;
    private String categoryCode;
    private String categoryName;
    private boolean selectedCurrentCategory;
    private List<FilterSubCategoryModel> subList = new ArrayList<>();

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getInputValue() {
        return this.inputValue;
    }

    public List<FilterSubCategoryModel> getSubList() {
        return this.subList;
    }

    public boolean isCanInput() {
        return this.canInput;
    }

    public boolean isSelectedCurrentCategory() {
        return this.selectedCurrentCategory;
    }

    public void setCanInput(boolean paramBoolean) {
        this.canInput = paramBoolean;
    }

    public void setCategoryCode(String paramString) {
        this.categoryCode = paramString;
    }

    public void setCategoryName(String paramString) {
        this.categoryName = paramString;
    }

    public void setSelectedCurrentCategory(boolean paramBoolean) {
        this.selectedCurrentCategory = paramBoolean;
    }

    public void setInputValue(String paramString) {
        this.inputValue = paramString;
    }

    public void setSubList(List<FilterSubCategoryModel> paramList) {
        this.subList = paramList;
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

    public boolean isCanInputTime() {
        return canInputTime;
    }

    public void setCanInputTime(boolean canInputTime) {
        this.canInputTime = canInputTime;
    }

    @Override
    public String toString() {
        return "FilterCategoryModel{" +
                "canInput=" + canInput +
                ", inputValue='" + inputValue + '\'' +
                ", canInputTime=" + canInputTime +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", selectedCurrentCategory=" + selectedCurrentCategory +
                ", subList=" + subList +
                '}';
    }

    public static class FilterSubCategoryModel implements Serializable {
        private static final long serialVersionUID = -4762019515776706290L;
        private boolean selected;
        private String subCategoryCode;
        private String subCategoryName;

        public String getSubCategoryCode() {
            return this.subCategoryCode;
        }

        public String getSubCategoryName() {
            return this.subCategoryName;
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean paramBoolean) {
            this.selected = paramBoolean;
        }

        public void setSubCategoryCode(String paramString) {
            this.subCategoryCode = paramString;
        }

        public void setSubCategoryName(String paramString) {
            this.subCategoryName = paramString;
        }

        @Override
        public String toString() {
            return "FilterSubCategoryModel{" +
                    "selected=" + selected +
                    ", subCategoryCode='" + subCategoryCode + '\'' +
                    ", subCategoryName='" + subCategoryName + '\'' +
                    '}';
        }
    }
}
