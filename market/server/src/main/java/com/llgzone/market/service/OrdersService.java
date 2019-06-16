package com.llgzone.market.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import com.llgzone.market.dao.OrdersMapper;
import com.llgzone.market.dao.RemittenceMapper;
import com.llgzone.market.dao.OrderDetailMapper;
import com.llgzone.market.model.Orders;
import com.llgzone.market.model.Remittence;
import com.llgzone.market.model.OrderDetail;

@Service
public class OrdersService {
  @Autowired OrdersMapper ordersMapper;
  @Autowired OrderDetailMapper orderDetailMapper;
  @Autowired RemittenceMapper remittenceMapper;

  @Transactional
  public void buyGoods(Orders order, List<OrderDetail> orderDetails) {
    ordersMapper.insertSelective(order);
    for (OrderDetail oDetail : orderDetails) {
      oDetail.setOid(order.getOid());
      orderDetailMapper.insertSelective(oDetail);
    }
  }

  public List<Orders> getOrderByUid(String uid) {
    return ordersMapper.selectByUid(uid);
  }


  public List<Orders> getOrderByMid(String mid) {
    return ordersMapper.selectByMid(mid);
  }

  public void changeState(int oid, int state) {
    Orders order = new Orders();
    order.setOid(oid);
    order.setState(state);
    ordersMapper.updateByPrimaryKeySelective(order);
  }

 public List<OrderDetail> getOrderDetails(int oid){
    return orderDetailMapper.selectByOid(oid);
  }

  public void changeRemmitence(Remittence remittence) {
    remittenceMapper.insert(remittence);
  }

  public Remittence getRemittence(int oid) {
    return remittenceMapper.selectByOid(oid);
  }
}