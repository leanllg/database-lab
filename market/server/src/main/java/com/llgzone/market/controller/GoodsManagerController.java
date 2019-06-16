package com.llgzone.market.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.llgzone.market.controller.inter.Res;
import com.llgzone.market.dto.GoodsInfo;
import com.llgzone.market.dto.OrderBody;
import com.llgzone.market.dto.State;
import com.llgzone.market.model.Orders;
import com.llgzone.market.service.GoodsService;
import com.llgzone.market.service.OrdersService;

@RestController
public class GoodsManagerController {
  @Autowired GoodsService goodsService;
  @Autowired OrdersService ordersService;
  @Value("${static.path}") String dir;
  
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

  @PostMapping("/user/manager/upload")
  @PreAuthorize("hasAuthority('ROLE_MANAGER')")
  public Res uploadFile(MultipartFile[] file) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");//设置日期格式

    final String uploadDir = dir + "manager/";
    String filePath = "";
    MultipartFile f = file[0];
    if (!f.isEmpty()) {
      try {
          //判断文件目录是否存在，否则自动生成
          File directory = new File(uploadDir);
          if (!directory.exists()){
              directory.mkdirs();
          }
          filePath = df.format(new Date()) + f.getOriginalFilename();
          // 文件保存路径
          String realPath =  uploadDir + filePath;
          // 转存文件
          f.transferTo(new File(realPath));
      } catch (Exception e) {
          e.printStackTrace();
          HashMap<String, Object> error = new HashMap<>();
          error.put("code", 400);
          return new Res("Upload failed", null, error);
      }
    } else {
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 400);
      return new Res("File empty", null, error);
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    data.put("imgUrl", filePath);
    return new Res("上传成功", data, null);
  }

  @GetMapping("/user/manager/img")
  @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_USER')")
  public void getMapPic1(@RequestParam String url, HttpServletResponse httpResponse) {
    final String uploadDir = dir + "manager/";
    String filePath = uploadDir + url;
    System.out.println("filePath:"+filePath);
    File file = new File(filePath);
    FileInputStream fis = null;
    try {
        OutputStream out = httpResponse.getOutputStream();
        fis = new FileInputStream(file);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        out.write(b);
        out.flush();
    } catch (Exception e) {
         e.printStackTrace();
    } finally {
        if (fis != null) {
            try {
               fis.close();
            } catch (IOException e) {
            e.printStackTrace();
            }   
         }
    }
  }

  @PostMapping("/user/manager/labels")
  @PreAuthorize("hasAuthority('ROLE_MANAGER')")
  public Res addLabels(@RequestBody List<String> labels) {
    try {
      goodsService.addLabel(labels);
    } catch(Exception e) {
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 400);
      return new Res(e.getMessage(), null, error);
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);;
    return new Res("添加成功", data, null);
  }

  @GetMapping("/user/manager/order/{mid}")
  @PreAuthorize("hasAuthority('ROLE_MANAGER')")
  public Res getManagerOrder(@PathVariable String mid) {
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

  @PostMapping("/user/manager/order/state")
  @PreAuthorize("hasAuthority('ROLE_MANAGER')")
  public Res putState(@RequestBody State body) {
    int state = body.getState();
    int oid = body.getOid();
    if (state != 2 && state != 6) {
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 401);
      return new Res("没有权限更改", null, error);
    }
    ordersService.changeState(oid, state);
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    return new Res("更改状态成功", data, null);
  }
}