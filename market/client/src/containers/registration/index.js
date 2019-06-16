import * as React from "react"
import { Form, Icon, Input, Button, Radio } from "antd"
import request from "../../utils/request"
import "./index.scss"

const formItemLayout = {
  labelCol: { span: 3 },
  wrapperCol: { span: 11 }
}

class NormalRegisterForm extends React.Component {
  handleSubmit = e => {
    e.preventDefault()
    this.props.form.validateFields((err, values) => {
      values.type = parseInt(values.type)
      if (!err) {
        request("/user/registration", {
          body: JSON.stringify(values),
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          }
        })
      }
    })
  }

  compareToFirstPassword = (rule, value, callback) => {
    const form = this.props.form
    if (value && value !== form.getFieldValue("password")) {
      callback("两个密码不对！")
    } else {
      callback()
    }
  }

  render() {
    const { getFieldDecorator } = this.props.form
    return (
      <Form onSubmit={this.handleSubmit} className="register-form">
        <Form.Item hasFeedback>
          {getFieldDecorator("name", {
            rules: [{ required: true, message: "Please input your username!" }]
          })(
            <Input
              prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
              placeholder="Username"
            />
          )}
        </Form.Item>
        <Form.Item hasFeedback>
          {getFieldDecorator("phone", {
            rules: [
              { required: true, message: "Please input your phone number!" }
            ]
          })(
            <Input
              prefix={
                <Icon type="phone" style={{ color: "rgba(0,0,0,.25)" }} />
              }
              placeholder="Phone number"
            />
          )}
        </Form.Item>
        <Form.Item hasFeedback>
          {getFieldDecorator("password", {
            rules: [{ required: true, message: "Please input your Password!" }]
          })(
            <Input.Password
              prefix={<Icon type="lock" style={{ color: "rgba(0,0,0,.25)" }} />}
              placeholder="Password"
            />
          )}
        </Form.Item>
        <Form.Item hasFeedback>
          {getFieldDecorator("confirm", {
            rules: [
              {
                required: true,
                message: "Please confirm your password!"
              },
              {
                validator: this.compareToFirstPassword
              }
            ]
          })(
            <Input.Password
              prefix={<Icon type="lock" style={{ color: "rgba(0,0,0,.25)" }} />}
              onBlur={this.handleConfirmBlur}
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
            className="register-form-button"
            type="primary"
            htmlType="submit"
          >
            register
          </Button>
        </Form.Item>
      </Form>
    )
  }
}

export default Form.create({ name: "normal_register" })(NormalRegisterForm)
