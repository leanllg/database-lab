package com.llgzone.market.model;

import java.math.BigDecimal;
import java.util.List;

public class Goods {
    private Integer gid;

    private String uid;

    private String name;

    private String description;

    private String imgUrl;

    private BigDecimal pay;

    private Float discount;

    private User user;

    private List<Labels> labels;

    public Goods(Integer gid, String uid, String name, String description, String imgUrl, BigDecimal pay, Float discount) {
        this.gid = gid;
        this.uid = uid;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.pay = pay;
        this.discount = discount;
    }

    public Goods() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Labels> getLabels() {
        return labels;
    }

    public void setLabels(List<Labels> labels) {
        this.labels = labels;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}