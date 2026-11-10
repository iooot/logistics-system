package com.web.functionModule.cdInfoAction.domain;


import java.util.List;

public class CdPageListInfo {
    private String currentPage;    //计划进入第几页
    private String pageCount;      //每页数据量
    private String totalCount;     //总数据量
    private String totalPageCount; //总页数
    private List<CdInfo> list;//展示的数据

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(String totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<CdInfo> getList() {
        return list;
    }

    public void setList(List<CdInfo> list) {
        this.list = list;
    }
}
