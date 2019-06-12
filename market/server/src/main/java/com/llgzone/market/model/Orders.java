package com.llgzone.market.model;

import java.util.Date;

public class Orders {
    private Integer oid;

    private String uid;

    private String mid;

    private Date buytime;

    private Integer state;

    public Orders(Integer oid, String uid, String mid, Date buytime, Integer state) {
        this.oid = oid;
        this.uid = uid;
        this.mid = mid;
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

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
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