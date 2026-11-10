package com.domain;

public class DbInfo {

    private String dbtype;
    private String conStr;
    private String dbuid;
    private String dbupwd;
    private String DriverName;

    public String getDbtype() {
        return dbtype;
    }

    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }

    public String getConStr() {
        return conStr;
    }

    public void setConStr(String conStr) {
        this.conStr = conStr;
    }


    public String getDbupwd() {
        return dbupwd;
    }

    public void setDbupwd(String dbupwd) {
        this.dbupwd = dbupwd;
    }

    public String getDbuid() {
        return dbuid;
    }

    public void setDbuid(String dbuid) {
        this.dbuid = dbuid;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }
}
