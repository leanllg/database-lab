package com.llgzone.market.model;

public class OrderDetailKey {
    private Integer oid;

    private Integer gid;

    public OrderDetailKey(Integer oid, Integer gid) {
        this.oid = oid;
        this.gid = gid;
    }

    public OrderDetailKey() {
        super();
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }
}