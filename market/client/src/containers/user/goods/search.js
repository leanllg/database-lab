import * as React from "react"
import { Form, Input, Button } from "antd"

const formItemLayout = {
  labelCol: { span: 3 },
  wrapperCol: { span: 11 }
}

class SearchForm extends React.Component {
  handleSubmit = e => {
    e.preventDefault()
    this.props.form.validateFields((err, values) => {
      for (let key in values) {
        if (values[key] === undefined || values[key] === "") {
          delete values[key]
        }
      }
      console.log(values)
      if (!err) {
        this.props.onClick(values)
      }
    })
  }

  render() {
    const { getFieldDecorator } = this.props.form
    return (
      <Form onSubmit={this.handleSubmit} className="search-form">
        <Form.Item {...formItemLayout} label={"商家名"}>
          {getFieldDecorator("userName", {})(<Input placeholder="name" />)}
        </Form.Item>
        <Form.Item {...formItemLayout} label={"分类"}>
          {getFieldDecorator("labelName", {})(<Input placeholder="Label" />)}
        </Form.Item>
        <Form.Item {...formItemLayout} label={"商品名"}>
          {getFieldDecorator("name", {})(<Input placeholder="name" />)}
        </Form.Item>
        <Form.Item wrapperCol={{ offset: 3 }}>
          <Button type="primary" htmlType="submit">
            搜索
          </Button>
        </Form.Item>
      </Form>
    )
  }
}

export default Form.create({ name: "normal_search" })(SearchForm)
