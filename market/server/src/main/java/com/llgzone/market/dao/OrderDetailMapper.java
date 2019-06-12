package com.llgzone.market.dao;

import java.util.List;

import com.llgzone.market.model.OrderDetail;
import com.llgzone.market.model.OrderDetailKey;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(OrderDetailKey key);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(OrderDetailKey key);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKeyWithBLOBs(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    List<OrderDetail> selectByOid(int oid);
}