package com.llgzone.market.dao;

import com.llgzone.market.model.Orders;
import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> selectByUid(String uid);

    List<Orders> selectByMid(String mid);
}