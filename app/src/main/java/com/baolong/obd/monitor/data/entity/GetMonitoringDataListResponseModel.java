package com.baolong.obd.monitor.data.entity;

import java.util.ArrayList;
import java.util.List;

public class GetMonitoringDataListResponseModel
{
    private int count;
    private List<MonitoringDataDetail> list = new ArrayList();
    private int pageNo;
    private int pageSize;

    public int getCount()
    {
        return this.count;
    }

    public List<MonitoringDataDetail> getList()
    {
        return this.list;
    }

    public int getPageNo()
    {
        return this.pageNo;
    }

    public int getPageSize()
    {
        return this.pageSize;
    }

    public void setCount(int paramInt)
    {
        this.count = paramInt;
    }

    public void setList(List<MonitoringDataDetail> paramList)
    {
        this.list = paramList;
    }

    public void setPageNo(int paramInt)
    {
        this.pageNo = paramInt;
    }

    public void setPageSize(int paramInt)
    {
        this.pageSize = paramInt;
    }

    public class MonitoringDataDetail
    {
        private String hphm;
        private String hpys;
        private String hpysname;
        private String id;
        private boolean isNewRecord;
        private String pdjg;
        private String testdate;
        private String testno;
        private String tp1;

        public MonitoringDataDetail() {}

        public String getHphm()
        {
            return this.hphm;
        }

        public String getHpys()
        {
            return this.hpys;
        }

        public String getHpysname()
        {
            return this.hpysname;
        }

        public String getId()
        {
            return this.id;
        }

        public String getPdjg()
        {
            return this.pdjg;
        }

        public String getTestdate()
        {
            return this.testdate;
        }

        public String getTestno()
        {
            return this.testno;
        }

        public String getTp1()
        {
            return this.tp1;
        }

        public boolean isNewRecord()
        {
            return this.isNewRecord;
        }

        public void setHphm(String paramString)
        {
            this.hphm = paramString;
        }

        public void setHpys(String paramString)
        {
            this.hpys = paramString;
        }

        public void setHpysname(String paramString)
        {
            this.hpysname = paramString;
        }

        public void setId(String paramString)
        {
            this.id = paramString;
        }

        public void setNewRecord(boolean paramBoolean)
        {
            this.isNewRecord = paramBoolean;
        }

        public void setPdjg(String paramString)
        {
            this.pdjg = paramString;
        }

        public void setTestdate(String paramString)
        {
            this.testdate = paramString;
        }

        public void setTestno(String paramString)
        {
            this.testno = paramString;
        }

        public void setTp1(String paramString)
        {
            this.tp1 = paramString;
        }
    }
}
