package com.baolong.obd.analysis.data.entity;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class AnalysisLayoutModel implements Serializable{

    /**
     * news : {"pageNum":3,"pageSize":3}
     * products : [{"title":"阜南县农业统计","color":"#ff0000","rows":1,"cols":4,"items":[{"title":"基本情况","code":"basicInfo","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductCommonWebActivity","params":{"url":"html"},"updateCode":1},{"title":"自然资源","code":"resource","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"人口经济","code":"economic","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"作物生产","code":"crops","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1}]},{"title":"农气实况","color":"#00ff00","rows":2,"cols":4,"items":[{"title":"降水","code":"rain","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"气温","code":"temperature","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"环境","code":"air","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"土壤水分","code":"water","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"雷达","code":"radar","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"云图","code":"satellite","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"作物发育","code":"growth","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"预警信号","code":"warningSignal","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1}]}]
     */
    private NewsBean news;
    private List<ProductsBean> products;

    public NewsBean getNews() {
        return news;
    }

    public void setNews(NewsBean news) {
        this.news = news;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }


    public static class NewsBean {
        /**
         * pageNum : 3
         * pageSize : 3
         */

        private int pageNum;
        private int pageSize;
        private String backIcon;
        private String[] tags;

        public String[] getTags() {
            return tags;
        }

        public void setTags(String[] tags) {
            this.tags = tags;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getBackIcon() {
            return backIcon;
        }

        public void setBackIcon(String backIcon) {
            this.backIcon = backIcon;
        }
    }

    public static class ProductsBean {
        /**
         * title : 阜南县农业统计
         * color : #ff0000
         * backIcon ： assets://icons/back_monitor_color.png
         * rows : 1
         * cols : 4
         * items : [{"title":"基本情况","code":"basicInfo","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductCommonWebActivity","params":{"url":"html"},"updateCode":1},{"title":"自然资源","code":"resource","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"人口经济","code":"economic","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1},{"title":"作物生产","code":"crops","icon":"assets://icons/rain_color.png","activity":"cn.nmc.weatherapp.activity.product.ProductMonitorRainMapActivity","params":{"url":""},"updateCode":1}]
         */

        private String title;
        private String color;
        private String backIcon;
        private int rows;
        private int cols;
        private List<ItemsBean> items;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getCols() {
            return cols;
        }

        public void setCols(int cols) {
            this.cols = cols;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public String getBackIcon() {
            return backIcon;
        }

        public void setBackIcon(String backIcon) {
            this.backIcon = backIcon;
        }


        public static class ItemsBean {
            /**
             * title : 基本情况
             * code : basicInfo
             * icon : assets://icons/rain_color.png
             * activity : cn.nmc.weatherapp.activity.product.ProductCommonWebActivity
             * params : {"url":"html"}
             * updateCode : 1
             */

            private String title;
            private String code;
            private String icon;
            private int iconSize;
            private String webUrl;
            private String activity;
            private JSONObject params;
            private int updateCode;
            //新添加属性  描述
            private String desc;

            public int getIconSize() {
                return iconSize;
            }

            public void setIconSize(int iconSize) {
                this.iconSize = iconSize;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getActivity() {
                return activity;
            }

            public void setActivity(String activity) {
                this.activity = activity;
            }

            public JSONObject getParams() {
                return params;
            }

            public void setParams(JSONObject params) {
                this.params = params;
            }

            public int getUpdateCode() {
                return updateCode;
            }

            public void setUpdateCode(int updateCode) {
                this.updateCode = updateCode;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getWebUrl() {
                return webUrl;
            }

            public void setWebUrl(String webUrl) {
                this.webUrl = webUrl;
            }
        }
    }
}
