import * as React from "react"
import { Form, Icon, Input, Button, Radio } from "antd"
import "./index.scss"
import request from "../../utils/request"
import { navigate } from "@reach/router"

const formItemLayout = {
  labelCol: { span: 3 },
  wrapperCol: { span: 11 }
}

class NormalLoginForm extends React.Component {
  handleSubmit = e => {
    e.preventDefault()
    this.props.form.validateFields((err, values) => {
      if (!err) {
        const api = values.type === "0" ? "/user" : "/user/manager"
        request(api, {
          method: "POST",
          body: JSON.stringify(values),
          onsuccess: json => {
            localStorage.setItem("token", json.data.token)
            if (values.type === "0") {
              navigate("/user/")
            } else {
              navigate("/manager/")
            }
          }
        })
      }
    })
  }

  handleRegisterClick(e) {
    e.preventDefault()
  }

  render() {
    const { getFieldDecorator } = this.props.form
    return (
      <Form onSubmit={this.handleSubmit} className="login-form">
        <Form.Item>
          {getFieldDecorator("name", {
            rules: [{ required: true, message: "Please input your username!" }]
          })(
            <Input
              prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
              placeholder="Username"
            />
          )}
        </Form.Item>
        <Form.Item>
          {getFieldDecorator("password", {
            rules: [{ required: true, message: "Please input your Password!" }]
          })(
            <Input
              prefix={<Icon type="lock" style={{ color: "rgba(0,0,0,.25)" }} />}
              type="password"
              placeholder="Password"
            />
          )}
        </Form.Item>
        <Form.Item required {...formItemLayout} label="角色">
          {getFieldDecorator("type", {
            rules: [
              {
                required: true,
                message: "Please select your role"
              }
            ]
          })(
            <Radio.Group>
              <Radio value="0">顾客</Radio>
              <Radio value="1">商家</Radio>
            </Radio.Group>
          )}
        </Form.Item>
        <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            className="login-form-button"
          >
            Log in
          </Button>
        </Form.Item>
      </Form>
    )
  }
}

export default Form.create({ name: "normal_login" })(NormalLoginForm)
