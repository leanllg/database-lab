import * as React from "react"
import { Form, Input, Button, Radio } from "antd"
import request from "../../../utils/request"
import { connect } from "react-redux"
import { updateProfile } from "../action"

const formItemLayout = {
  labelCol: { span: 3 },
  wrapperCol: { span: 11 }
}

class Profile extends React.PureComponent {
  handleSubmit = e => {
    e.preventDefault()
    this.props.form.validateFields((err, values) => {
      if (!err) {
        let modified = {}
        const token = localStorage.getItem("token")
        if (values.sex) {
          values.sex = parseInt(values.sex)
        }
        for (let key in values) {
          if (values[key] !== this.props.profile[key]) {
            modified[key] = values[key]
          }
        }
        if (Object.keys(modified).length > 0) {
          request("/user/manager/profile", {
            method: "POST",
            param: { token },
            body: JSON.stringify(modified),
            onsuccess: () => this.props.updateProfile(modified)
          })
        }
      }
    })
  }

  render() {
    const { getFieldDecorator } = this.props.form
    const { name, phone, description, sex } = this.props.profile
    return (
      <div>
        <Form onSubmit={this.handleSubmit} className="profile-form">
          <Form.Item {...formItemLayout} label="账号名">
            {getFieldDecorator("name", {
              rules: [
                { required: true, message: "Please input your username!" }
              ],
              initialValue: name
            })(<Input />)}
          </Form.Item>
          <Form.Item {...formItemLayout} label="电话">
            {getFieldDecorator("phone", {
              rules: [{ required: true, message: "Please input your phone!" }],
              initialValue: phone
            })(<Input />)}
          </Form.Item>
          <Form.Item {...formItemLayout} label="商家简介">
            {getFieldDecorator("description", {
              rules: [
                { required: true, message: "Please input your description!" }
              ],
              initialValue: description
            })(<Input />)}
          </Form.Item>
          <Form.Item required {...formItemLayout} label="性别">
            {getFieldDecorator("sex", {
              rules: [
                {
                  required: true,
                  message: "Please select your role"
                }
              ],
              initialValue: `${sex}`
            })(
              <Radio.Group>
                <Radio value="0">男</Radio>
                <Radio value="1">女</Radio>
              </Radio.Group>
            )}
          </Form.Item>
          <Form.Item wrapperCol={{ offset: 3 }}>
            <Button type="primary" htmlType="submit">
              提交
            </Button>
          </Form.Item>
        </Form>
      </div>
    )
  }
}

function mapStateToProps(state) {
  return {
    profile: state.manager.profile
  }
}

export default connect(
  mapStateToProps,
  { updateProfile }
)(Form.create({ name: "profile" })(Profile))
