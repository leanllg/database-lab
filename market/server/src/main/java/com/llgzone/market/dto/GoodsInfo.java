package com.llgzone.market.dto;

import java.math.BigDecimal;
import java.util.List;

public class GoodsInfo {
  private Integer gid;

  private String uid;

  private String name;

  private String description;

  private String imgUrl;

  private BigDecimal pay;

  private Float discount;

  private List<Integer> lid;

  public GoodsInfo(
    Integer gid, String uid, String name, String description, 
    String imgUrl, BigDecimal pay, Float discount, List<Integer> lid
    ) {
      this.gid = gid;
      this.uid = uid;
      this.name = name;
      this.description = description;
      this.imgUrl = imgUrl;
      this.pay = pay;
      this.discount = discount;
      this.lid = lid;
  }

  public GoodsInfo() {
      super();
  }

  public List<Integer> getLid() {
      return lid;
  }

  public void setLid(List<Integer> lid) {
    this.lid = lid;
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