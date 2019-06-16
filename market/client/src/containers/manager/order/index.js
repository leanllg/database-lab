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
    request(`/user/manager/order/${uid}`, {
      param: { token },
      onsuccess: json => {
        this.setState({
          orderBody: json.data.body,
          uidMap: json.data.uidMap
        })
      },
      noMessage: true
    })
  }

  sendGoods = oid => {
    request("/user/manager/order/state", {
      method: "POST",
      param: { token: this.token },
      body: JSON.stringify({
        state: 2,
        oid
      })
    })
  }

  agreeRefund = oid => {
    request("/user/manager/order/state", {
      method: "POST",
      param: { token: this.token },
      body: JSON.stringify({
        state: 6,
        oid
      })
    })
  }

  render() {
    const { orderBody } = this.state
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
                  description={<span>购买时间: {getTime(orders.buytime)}</span>}
                />
                {oDetail.map(od => (
                  <div key={od.gid}>
                    <p>商品: {od.gname}</p>
                    <p>价格： {(od.pay * (1 - od.discount)).toFixed(2)}</p>
                  </div>
                ))}
                <p>当前状态: {OrderState[orders.state]}</p>
                {orders.state === 1 && (
                  <Button
                    onClick={() => this.sendGoods(orders.oid)}
                    type="primary"
                  >
                    发货
                  </Button>
                )}
                {orders.state === 5 && (
                  <Button
                    onClick={() => this.agreeRefund(orders.oid)}
                    type="primary"
                  >
                    同意退款
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
  const profile = state.manager.profile
  return {
    uid: profile.uid
  }
}

export default connect(mapStateToProps)(Order)
