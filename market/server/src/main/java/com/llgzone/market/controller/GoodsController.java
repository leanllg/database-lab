package com.llgzone.market.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.llgzone.market.controller.inter.Res;
import com.llgzone.market.dto.OrderBody;
import com.llgzone.market.model.Goods;
import com.llgzone.market.model.Labels;
import com.llgzone.market.model.User;
import com.llgzone.market.model.Orders;
import com.llgzone.market.model.Remittence;
import com.llgzone.market.service.GoodsService;
import com.llgzone.market.service.OrdersService;
import com.llgzone.market.service.UserService;
import com.llgzone.market.dto.State;

@RestController
public class GoodsController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired GoodsService goodsService;
  @Autowired UserService userService;
  @Autowired OrdersService ordersService;

  /**
   * 
   * @param name 商品名筛选
   * @param discount 按折扣区间筛选
   * @param pay 按价格筛选
   * @param userName 商品名筛选
   */
  @GetMapping("/goods")
  public Res getGoods(String name, String discount, String pay, String userName, String labelName) {
    logger.info(name + discount + pay + userName);
    HashMap<String, Object> params = new HashMap<>();
    params.put("name", name);
    Labels label = null;
    if (labelName != null) {
      label = goodsService.getLabelByName(labelName);
      if (label != null) {
        params.put("lid", label.getLid());
      } else {
        HashMap<String, Object> data = new HashMap<>();
        data.put("code", 200);
        data.put("goods", new ArrayList<>());
        return new Res("查询成功", data, null);
      }
    }
    if (userName != null) {
      User user = userService.getUserByName(userName);
      if (user != null) {
        params.put("uid", user.getUid());
      } else {
        HashMap<String, Object> data = new HashMap<>();
        data.put("code", 200);
        data.put("goods", new ArrayList<>());
        return new Res("查询成功", data, null);
      }
    }
    String[] discounts = {"0", "1"};
    String[] pays = {"0", null};
    if (discount != null) {
    // 价格折扣
      discounts = discount.split("_");
    }
    if (pay != null) {
      pays = pay.split("_");
    }
    params.put("discount1", Float.parseFloat(discounts[0]));
    params.put("discount2", Float.parseFloat(discounts[1]));
    params.put("pay1", Float.parseFloat(pays[0]));
    params.put("pay2", pays[1] == null ? null : Float.parseFloat(pays[1]));

    List<Goods> goods = goodsService.getGoods(params);
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    data.put("goods", goods);
    return new Res("查询成功", data, null);
  }

  @GetMapping("/labels")
  public Res getLabels() {
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    data.put("labels", goodsService.getLabels());
    return new Res("查询成功", data, null);
  }

  @PostMapping("/order")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public Res buyGoods(@RequestBody List<OrderBody> orderBodys) {
    Date now = new Date();
    for (OrderBody orderBody : orderBodys) {
      Orders orders = orderBody.getOrders();
      orders.setBuytime(now);
      ordersService.buyGoods(orders, orderBody.getoDetail());
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    return new Res("购买成功", data, null);
  }

  @GetMapping("/order/{uid}")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public Res getOrder(@PathVariable String uid) {
    List<OrderBody> orderBodyList = new ArrayList<>(); 
    List<Orders> orders = ordersService.getOrderByUid(uid);
    HashMap<String, Object> uidMap = new HashMap<>();
    for (Orders order : orders) {
      OrderBody orderBody = new OrderBody();
      User user = userService.getUser(order.getMid());
      uidMap.put(user.getUid(), user.getName());
      orderBody.setOrders(order);
      orderBody.setoDetail(ordersService.getOrderDetails(order.getOid()));
      orderBodyList.add(orderBody);
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    data.put("body", orderBodyList);
    data.put("uidMap", uidMap);
    return new Res("成功", data, null);
  }

  @PostMapping("/remittence")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public Res putRemittence(@RequestBody Remittence remittence) {
    ordersService.changeRemmitence(remittence);
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    return new Res("付款成功", data, null);
  }

  @PostMapping("/order/state")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public Res putState(@RequestBody State body) {
    int state = body.getState();
    int oid = body.getOid();
    if (state == 2 || state == 6) {
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 401);
      return new Res("没有权限更改", null, error);
    }
    ordersService.changeState(oid, state);
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    return new Res("更改状态成功", data, null);
  }

  @GetMapping("/remittence/{oid}")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public Res getRemittence(@PathVariable int oid) {
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    data.put("remittence", ordersService.getRemittence(oid));
    return new Res("成功", data, null);
  }
}