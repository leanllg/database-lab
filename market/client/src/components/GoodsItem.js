import * as React from "react"
import { Card, Tag, Icon } from "antd"
import "./goodsItem.scss"

class GoodsItem extends React.PureComponent {
  render() {
    const {
      imgUrl,
      showAction,
      handlePlusClick,
      handleMinusClick,
      num,
      goods
    } = this.props
    const { name, description, pay, discount, labels } = goods
    const { name: userName, uid: mid } = goods.user

    return (
      <div>
        <Card
          style={{ width: 150 }}
          cover={<img alt="goods" src={imgUrl} />}
          className="goods-item"
          actions={
            showAction && [
              <Icon onClick={e => handlePlusClick(mid, goods)} type="plus" />,
              <Icon
                onClick={e => handleMinusClick(mid, goods.name)}
                type="minus"
              />,
              <span>{num || 0}</span>
            ]
          }
        >
          <Card.Meta title={name} description={`商品描述: ${description}`} />
          <p className="card-p">价格: {(pay * (1 - discount)).toFixed(2)}</p>
          <p className="card-p">折扣: {`${(1 - discount) * 100}%`}</p>
          <p className="card-p">商家: {userName}</p>
          <div>
            分类:
            {labels.map(label => (
              <Tag key={label.lid} color="#108ee9">
                {label.lname}
              </Tag>
            ))}
          </div>
        </Card>
      </div>
    )
  }
}

export default GoodsItem
