package com.llgzone.market.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import com.llgzone.market.controller.inter.Res;
import com.llgzone.market.model.Goods;
import com.llgzone.market.model.User;
import com.llgzone.market.service.GoodsService;
import com.llgzone.market.service.UserService;

@RestController
public class GoodsController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired GoodsService goodsService;
  @Autowired UserService userService;

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
    params.put("lid", goodsService.getLabelByName(labelName).getLid());
    User user = userService.getUserByName(userName);
    if (user != null) {
      params.put("uid", user.getUid());
    }
    // 价格折扣
    String[] discounts = discount.split("_");
    params.put("discount1", Float.parseFloat(discounts[0]));
    params.put("discount2", Float.parseFloat(discounts[1]));
    String[] pays = pay.split("_");
    params.put("pay1", Float.parseFloat(pays[0]));
    params.put("pay2", Float.parseFloat(pays[1]));

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
    data.put("goods", goodsService.getLabels());
    return new Res("查询成功", data, null);
  }
}