package com.llgzone.market.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.llgzone.market.controller.inter.Res;
import com.llgzone.market.dto.GoodsInfo;
import com.llgzone.market.dto.OrderBody;
import com.llgzone.market.model.Labels;
import com.llgzone.market.model.Orders;
import com.llgzone.market.service.GoodsService;
import com.llgzone.market.service.OrdersService;

@RestController
public class GoodsManagerController {
  @Autowired GoodsService goodsService;
  @Autowired OrdersService ordersService;
  
  @PostMapping("/user/manager/goods")
  @PreAuthorize("hasAuthority('ROLE_MANAGER')")
  public Res addGoods(@RequestBody GoodsInfo gi) {
    try {
      goodsService.addGoods(gi);
    } catch(Exception e) {
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 400);
      return new Res(e.getMessage(), null, error);
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);;
    return new Res("添加成功", data, null);
  }

  @PostMapping("/user/manager/label")
  @PreAuthorize("hasAuthority('ROLE_MANAGER')")
  public Res addLabels(@RequestBody Labels label) {
    try {
      goodsService.addLabel(label.getLname());
    } catch(Exception e) {
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 400);
      return new Res(e.getMessage(), null, error);
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);;
    return new Res("添加成功", data, null);
  }

  @GetMapping("/user/manager/order/{uid}")
  @PreAuthorize("hasAuthority('ROLE_MANAGER')")
  public Res getManagerOrder(String mid) {
    List<OrderBody> orderBodyList = new ArrayList<>(); 
    List<Orders> orders = ordersService.getOrderByMid(mid);
    for (Orders order : orders) {
      OrderBody orderBody = new OrderBody();
      orderBody.setOrders(order);
      orderBody.setoDetail(ordersService.getOrderDetails(order.getOid()));
      orderBodyList.add(orderBody);
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    data.put("body", orderBodyList);
    return new Res("成功", data, null);
  }

  @GetMapping("/user/manager/remittence/{oid}")
  @PreAuthorize("hasAuthority('ROLE_MANAGER')")
  public Res getManagerRemittence(int oid) {
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    data.put("remittence", ordersService.getRemittence(oid));
    return new Res("成功", data, null);
  }
}