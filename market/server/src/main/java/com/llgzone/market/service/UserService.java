package com.llgzone.market.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

import com.llgzone.market.dao.UserMapper;
import com.llgzone.market.model.User;

@Service
public class UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired UserMapper userMapper;

  public User getUser(String uid) {
    return userMapper.selectByPrimaryKey(uid);
  }

  public User getUserByName(String userName) {
    return userMapper.selectByName(userName);
  }

  public Boolean isRightPassword(String password, String encode) {
    return passwordEncoder.matches(password, encode);
  }

  private User setUser(String userName, String password, String phone) {
    User user = new User();
    user.setName(userName);
    user.setPassword(password);
    user.setPhone(phone);
    user.setSex((byte) 0);
    user.setType((byte) 0);
    user.setDescription("这个人很懒，没有留下简介..");
    user.setUid(UUID.randomUUID().toString());
    return user;
  }

  public void register(User user) throws Exception {
    String userName = user.getName();
    String password = user.getPassword();
    String phone = user.getPhone();
    // logger.info("username: " + userName + "password: " + password);
    if (userName == null || password == null || phone == null) {
      throw new Exception("缺少字段");
    }
    if (phone.length() < 11 || userName.length() < 4 || password.trim().length() < 6) {
      throw new Exception("不合规范");
    }
    String encodePassword = passwordEncoder.encode(password.trim());
    User insertUser = setUser(userName, encodePassword, phone);
    if (user.getType() != null && user.getType() == 1) {
      insertUser.setType(user.getType());
    }

    try {
      logger.info(encodePassword + " phone: " +  phone);
      userMapper.insertSelective(insertUser);
    }
    catch (Exception e) {
      throw new Exception("用户名或电话已经被注册");
    }
  }

  // 不能改变user type
  public void updateUser(User user) throws Exception {
    if (user.getPassword()!=null) {
      String encodePassword = passwordEncoder.encode(user.getPassword().trim());
      user.setPassword(encodePassword);
    }
    String name = user.getName();
    if (name!=null && getUserByName(name) != null) {
      throw new Exception("用户名已存在");
    }
    logger.info(user.getDescription());
    user.setType(null);
    userMapper.updateByPrimaryKeySelective(user);
  }
}