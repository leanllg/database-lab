import * as React from "react"
import { connect } from "react-redux"
import { List, Button } from "antd"
import request from "../../../utils/request"
import getTime from "../../../utils/getTime"
import OrderState from "../../../constants/orderState"

class Order extends React.Component {
  state = {
    orderBody: [],
    uidMap: {}
  }
  token = localStorage.getItem("token")

  componentDidMount() {
    const token = localStorage.getItem("token")
    const { uid } = this.props
    request(`/order/${uid}`, {
      param: { token },
      onsuccess: json => {
        console.log(json)
        this.setState({
          orderBody: json.data.body,
          uidMap: json.data.uidMap
        })
      }
    })
  }

  cancelOrder = oid => {
    request("/order/state", {
      method: "POST",
      param: { token: this.token },
      body: JSON.stringify({
        state: 7,
        oid
      })
    })
  }

  payOrder = oid => {
    request("/order/state", {
      method: "POST",
      param: { token: this.token },
      body: JSON.stringify({
        state: 1,
        oid
      })
    })
  }

  getGoods = oid => {
    request("/order/state", {
      method: "POST",
      param: { token: this.token },
      body: JSON.stringify({
        state: 3,
        oid
      })
    })
  }

  finishOrder = oid => {
    request("/order/state", {
      method: "POST",
      param: { token: this.token },
      body: JSON.stringify({
        state: 4,
        oid
      })
    })
  }

  refund = oid => {
    request("/order/state", {
      method: "POST",
      param: { token: this.token },
      body: JSON.stringify({
        state: 5,
        oid
      })
    })
  }

  render() {
    const { orderBody, uidMap } = this.state
    return (
      <List
        dataSource={orderBody}
        itemLayout="vertical"
        size="large"
        renderItem={({ orders, oDetail }) => {
          return (
            <>
              <List.Item key={orders.oid}>
                <List.Item.Meta
                  title={<span>商家: {uidMap[orders.mid]}</span>}
                  description={<span>购买时间: {getTime(orders.buytime)}</span>}
                />
                {oDetail.map(od => (
                  <div key={od.gid}>
                    <p>商品: {od.gname}</p>
                    <p>价格： {(od.pay * (1 - od.discount)).toFixed(2)}</p>
                  </div>
                ))}
                <p>当前状态: {OrderState[orders.state]}</p>
                {orders.state === 0 && (
                  <Button
                    onClick={() => this.cancelOrder(orders.oid)}
                    type="danger"
                  >
                    取消
                  </Button>
                )}
                {orders.state === 0 && (
                  <Button
                    onClick={() => this.payOrder(orders.oid)}
                    type="primary"
                  >
                    付款
                  </Button>
                )}
                {orders.state === 2 && (
                  <Button
                    onClick={() => this.getGoods(orders.oid)}
                    type="primary"
                  >
                    确认收货
                  </Button>
                )}
                {orders.state === 3 && (
                  <Button
                    onClick={() => this.finishOrder(orders.oid)}
                    type="primary"
                  >
                    完成订单
                  </Button>
                )}
                {orders.state === 4 && (
                  <Button
                    onClick={() => this.refund(orders.oid)}
                    type="primary"
                  >
                    退款
                  </Button>
                )}
              </List.Item>
            </>
          )
        }}
      />
    )
  }
}

function mapStateToProps(state) {
  const profile = state.user.profile
  return {
    uid: profile.uid
  }
}

export default connect(mapStateToProps)(Order)
