package com.llgzone.market.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;

import com.llgzone.market.controller.inter.Res;
import com.llgzone.market.dto.GoodsInfo;
import com.llgzone.market.model.Labels;
import com.llgzone.market.service.GoodsService;

@RestController
public class GoodsManagerController {
  @Autowired GoodsService goodsService;
  
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

  @PostMapping("/label")
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
}