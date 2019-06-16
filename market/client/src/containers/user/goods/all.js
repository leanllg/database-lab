import * as React from "react"
import { connect } from "react-redux"
import request from "../../../utils/request"
import GoodsItem from "../../../components/GoodsItem"
import "./all.scss"
import { Button } from "antd"
import Search from "./search"

// orderBody: [{orders: {oid, mid, uid, ?buytime}, oDetail: [{gid, gname, discount, goodsNum, pay, imgUrl}]}]
class AllGoods extends React.Component {
  state = {
    goodsList: [],
    orderBody: [],
    orderMap: {}
  }

  componentDidMount() {
    const token = localStorage.getItem("token")
    const { userName } = this.props
    request("/goods", {
      param: { token, userName },
      onsuccess: json => {
        this.setState({ goodsList: json.data.goods })
      }
    })
  }

  handlePlusClick = (mid, goods) => {
    const { uid } = this.props
    const { name, pay, discount, imgUrl, gid } = goods
    const { orderBody, orderMap } = this.state
    if (orderMap[mid] === undefined) {
      this.setState({
        orderBody: orderBody.concat({
          orders: { mid, uid },
          oDetail: [{ gname: name, discount, pay, imgUrl, goodsNum: 1, gid }]
        }),
        orderMap: { ...orderMap, [mid]: orderBody.length }
      })
    } else {
      const index = orderMap[mid]
      const tempArr = orderBody.concat()
      let goodsNameIndex = -1
      tempArr[index].oDetail.forEach((od, index) => {
        if (od.gname === name) {
          goodsNameIndex = index
        }
      })
      if (goodsNameIndex < 0) {
        tempArr[index].oDetail.push({
          gname: name,
          discount,
          pay,
          imgUrl,
          goodsNum: 1,
          gid
        })
      } else {
        tempArr[index].oDetail[goodsNameIndex].goodsNum += 1
      }
      this.setState({
        orderBody: tempArr
      })
    }
  }

  handleMinusClick = (mid, name) => {
    const { orderMap, orderBody } = this.state
    if (this.state.orderMap[mid] !== undefined) {
      const index = orderMap[mid]
      const tempArr = orderBody.concat()
      let goodsNameIndex = -1
      tempArr[index].oDetail.forEach((od, index) => {
        if (od.gname === name) {
          goodsNameIndex = index
        }
      })
      if (goodsNameIndex >= 0) {
        if (tempArr[index].oDetail[goodsNameIndex].goodsNum >= 1) {
          tempArr[index].oDetail[goodsNameIndex].goodsNum -= 1
        } else if (tempArr[index].oDetail[goodsNameIndex].goodsNum === 0) {
          tempArr[index].oDetail.splice(goodsNameIndex, 1)
        }
        this.setState({
          orderBody: tempArr
        })
      }
    }
  }

  handleBuyClick = () => {
    let { orderBody } = this.state
    orderBody = orderBody.filter(od => {
      return od.oDetail.length > 0
    })

    const token = localStorage.getItem("token")
    if (orderBody.length > 0) {
      request("/order", {
        method: "POST",
        body: JSON.stringify(this.state.orderBody),
        param: { token }
      })
    }
  }

  handleSearchClick = values => {
    console.log(values)
    const token = localStorage.getItem("token")
    request("/goods", {
      param: {
        token,
        ...values
      },
      onsuccess: json => {
        console.log(json)
        this.setState({ goodsList: json.data.goods })
      }
    })
  }

  render() {
    const token = localStorage.getItem("token")

    return (
      <div>
        <Search onClick={this.handleSearchClick} />
        <div className="all-goods-container">
          {this.state.goodsList.map(goods => {
            const { imgUrl, uid, name } = goods
            const imgSrc = `/user/manager/img?url=${imgUrl}&token=${token}`
            let num = 0
            if (this.state.orderMap[uid] !== undefined) {
              const oDetail = this.state.orderBody[this.state.orderMap[uid]]
                .oDetail
              let goodsNameIndex = -1
              oDetail.forEach((od, index) => {
                if (od.gname === name) {
                  goodsNameIndex = index
                }
              })
              if (goodsNameIndex >= 0) {
                num = oDetail[goodsNameIndex].goodsNum
              }
            }
            return (
              <GoodsItem
                goods={goods}
                key={goods.gid}
                imgUrl={imgSrc}
                showAction={true}
                handleMinusClick={this.handleMinusClick}
                handlePlusClick={this.handlePlusClick}
                num={num}
              />
            )
          })}
        </div>
        <Button type="primary" onClick={this.handleBuyClick}>
          Buy now!
        </Button>
      </div>
    )
  }
}

function mapStateToProps(state) {
  const profile = state.user.profile
  return {
    uid: profile.uid
  }
}

export default connect(mapStateToProps)(AllGoods)
