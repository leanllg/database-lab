package com.llgzone.market.dao;

import com.llgzone.market.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    User selectByName(String name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}