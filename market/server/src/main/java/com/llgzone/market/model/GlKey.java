package com.llgzone.market.model;

public class GlKey {
    private Integer gid;

    private Integer lid;

    public GlKey(Integer gid, Integer lid) {
        this.gid = gid;
        this.lid = lid;
    }

    public GlKey() {
        super();
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }
}