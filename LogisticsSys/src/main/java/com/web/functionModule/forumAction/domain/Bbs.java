package com.web.functionModule.forumAction.domain;

public class Bbs {
    private String id;
    private String bbsname;
    private String bbstype;
    private String bbscontent;
    private String bbscreator;
    private String bbscreatetime;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBbsname() {
        return bbsname;
    }

    public void setBbsname(String bbsname) {
        this.bbsname = bbsname;
    }

    public String getBbstype() {
        return bbstype;
    }

    public void setBbstype(String bbstype) {
        this.bbstype = bbstype;
    }

    public String getBbscontent() {
        return bbscontent;
    }

    public void setBbscontent(String bbscontent) {
        this.bbscontent = bbscontent;
    }

    public String getBbscreator() {
        return bbscreator;
    }

    public void setBbscreator(String bbscreator) {
        this.bbscreator = bbscreator;
    }

    public String getBbscreatetime() {
        return bbscreatetime;
    }

    public void setBbscreatetime(String bbscreatetime) {
        this.bbscreatetime = bbscreatetime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
