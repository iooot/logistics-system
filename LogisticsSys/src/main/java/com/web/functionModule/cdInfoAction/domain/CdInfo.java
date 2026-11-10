package com.web.functionModule.cdInfoAction.domain;

import java.util.ArrayList;
import java.util.List;

public class CdInfo {
    private String id;
    private String cname;
    private String cpath;
    private String fnid;
    private String indx;
    private String level;
    private String nrole;
    private String bem;
    private List<CdInfo> list = new ArrayList();

    public String getBem() {
        return bem;
    }

    public void setBem(String bem) {
        this.bem = bem;
    }

    public List<CdInfo> getList() {
        return list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCpath() {
        return cpath;
    }

    public void setCpath(String cpath) {
        this.cpath = cpath;
    }

    public String getFnid() {
        return fnid;
    }

    public void setFnid(String fnid) {
        this.fnid = fnid;
    }

    public String getIndx() {
        return indx;
    }

    public void setIndx(String indx) {
        this.indx = indx;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNrole() {
        return nrole;
    }

    public void setNrole(String nrole) {
        this.nrole = nrole;
    }
}
