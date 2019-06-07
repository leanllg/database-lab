package com.llgzone.market.model;

import java.util.Date;

public class Orders {
    private Integer oid;

    private String uid;

    private Integer gid;

    private Date buytime;

    private Integer state;

    public Orders(Integer oid, String uid, Integer gid, Date buytime, Integer state) {
        this.oid = oid;
        this.uid = uid;
        this.gid = gid;
        this.buytime = buytime;
        this.state = state;
    }

    public Orders() {
        super();
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}