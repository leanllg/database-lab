package com.llgzone.market.controller.inter;

public class Res {
  public String msg;
  public Object data;
  public Object error;

  public Res(String msg, Object data, Object error) {
    this.msg = msg;
    this.data = data;
    this.error = error;
  }
}
