import * as React from "react"
import { connect } from "react-redux"
import request from "../../../utils/request"
import GoodsItem from "../../../components/GoodsItem"
import "./all.scss"

class AllGoods extends React.Component {
  state = {
    goodsList: []
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

  render() {
    const token = localStorage.getItem("token")

    return (
      <div className="all-goods-container">
        {this.state.goodsList.map(goods => {
          const { imgUrl } = goods
          const imgSrc = `/user/manager/img?url=${imgUrl}&token=${token}`
          return <GoodsItem key={goods.gid} goods={goods} imgUrl={imgSrc} />
        })}
      </div>
    )
  }
}

function mapStateToProps(state) {
  const profile = state.manager.profile
  return {
    uid: profile.uid,
    userName: profile.name
  }
}

export default connect(mapStateToProps)(AllGoods)
