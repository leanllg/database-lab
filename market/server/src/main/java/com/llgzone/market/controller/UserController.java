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

import com.llgzone.market.service.UserService;
import com.llgzone.market.model.User;
import com.llgzone.market.dto.Usera;
import com.llgzone.market.controller.inter.Res;
import com.llgzone.market.utils.JWTUtil;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired UserService userService;

  @PostMapping("")
  public Res login(@RequestBody User user) {
    String userName = user.getName();
    User userEntity = userService.getUserByName(userName);
    String encode = userEntity.getPassword();
    String password = user.getPassword();
  
    if (userEntity == null || !userService.isRightPassword(password, encode)) {
      logger.info("password: " + password + " encode: " + encode);
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 400);
      return new Res("用户名或密码错误", null, error);
    }
    String token = JWTUtil.sign(userEntity.getUid(), userEntity.getType());
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    data.put("token", token);
    return new Res("登陆成功", data, null);
  }

  @GetMapping("/profile")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public Res getProfile(String token) {
    User user = userService.getUser(JWTUtil.getUid(token));
    Usera u = new Usera();
    BeanUtils.copyProperties(user, u);
    if (user == null) {
      logger.warn("获取profile失败");
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 500);
      return new Res("服务端错误", null, error);
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    data.put("user", u);
    return new Res("获取信息成功", data, null);
  }

  @PostMapping("/profile")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public Res updateProfile(@RequestBody User user, String token) {
    user.setUid(JWTUtil.getUid(token));
    try {
      userService.updateUser(user);
    }
    catch(Exception e) {
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 400);
      return new Res(e.getMessage(), null, error);
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    return new Res("修改信息成功", data, null);
  }

  @PostMapping("/registration")
  public Res register(@RequestBody User user) {
    try {
      userService.register(user);
    } catch(Exception e) {
      String errMsg = e.getMessage();
      logger.info(errMsg);
      e.printStackTrace();
      HashMap<String, Object> error = new HashMap<>();
      error.put("code", 400);
      return new Res(errMsg, null, error);
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("code", 200);
    return new Res("注册成功", data, null);
  }
}