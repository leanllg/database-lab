package com.llgzone.market.model;

import java.math.BigDecimal;
import java.util.Date;

public class Remittence {
    private Integer rid;

    private Integer oid;

    private BigDecimal paymoney;

    private Date paytime;

    private String payid;

    public Remittence(Integer rid, Integer oid, BigDecimal paymoney, Date paytime) {
        this.rid = rid;
        this.oid = oid;
        this.paymoney = paymoney;
        this.paytime = paytime;
    }

    public Remittence(Integer rid, Integer oid, BigDecimal paymoney, Date paytime, String payid) {
        this.rid = rid;
        this.oid = oid;
        this.paymoney = paymoney;
        this.paytime = paytime;
        this.payid = payid;
    }

    public Remittence() {
        super();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid == null ? null : payid.trim();
    }
}