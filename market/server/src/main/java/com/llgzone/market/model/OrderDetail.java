package com.llgzone.market.model;

import java.math.BigDecimal;

public class OrderDetail extends OrderDetailKey {
    private String gname;

    private Float discount;

    private Integer goodsNum;

    private BigDecimal pay;

    private String imgUrl;

    public OrderDetail(Integer oid, Integer gid, String gname, Float discount, Integer goodsNum, BigDecimal pay) {
        super(oid, gid);
        this.gname = gname;
        this.discount = discount;
        this.goodsNum = goodsNum;
        this.pay = pay;
    }

    public OrderDetail(Integer oid, Integer gid, String gname, Float discount, Integer goodsNum, BigDecimal pay, String imgUrl) {
        super(oid, gid);
        this.gname = gname;
        this.discount = discount;
        this.goodsNum = goodsNum;
        this.pay = pay;
        this.imgUrl = imgUrl;
    }

    public OrderDetail() {
        super();
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}