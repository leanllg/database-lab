package com.llgzone.market.dto;

import java.util.List;
import com.llgzone.market.model.OrderDetail;
import com.llgzone.market.model.Orders;

public class OrderBody {
  private List<OrderDetail> oDetail;
  private Orders orders;

  /**
   * @return the orders
   */
  public Orders getOrders() {
    return orders;
  }

  /**
   * @param orders the orders to set
   */
  public void setOrders(Orders orders) {
    this.orders = orders;
  }

  /**
   * @return the oDetail
   */
  public List<OrderDetail> getoDetail() {
    return oDetail;
  }

  /**
   * @param oDetail the oDetail to set
   */
  public void setoDetail(List<OrderDetail> oDetail) {
    this.oDetail = oDetail;
  }

  public OrderBody() {}
}