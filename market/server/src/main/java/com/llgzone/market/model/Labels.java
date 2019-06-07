package com.llgzone.market.model;

public class Labels {
    private Integer lid;

    private String lname;

    public Labels(Integer lid, String lname) {
        this.lid = lid;
        this.lname = lname;
    }

    public Labels() {
        super();
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname == null ? null : lname.trim();
    }
}