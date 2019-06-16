import * as React from "react"
import {
  Form,
  Input,
  Button,
  Select,
  InputNumber,
  Upload,
  Icon,
  Modal
} from "antd"
import request from "../../../utils/request"
import { host } from "../../../constants/url"
import { connect } from "react-redux"
import "./index.scss"

const { Option } = Select

const formItemLayout = {
  labelCol: { span: 3 },
  wrapperCol: { span: 11 }
}

function getBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}

class AddGoods extends React.Component {
  state = {
    previewVisible: false,
    previewImage: "",
    fileList: [],
    labels: []
  }

  componentDidMount() {
    request("/labels", {
      onsuccess: json => {
        this.setState({ labels: json.data.labels })
      },
      noMessage: true
    })
  }

  handleCancel = () => this.setState({ previewVisible: false })

  handlePreview = async file => {
    if (!file.url && !file.preview) {
      file.preview = await getBase64(file.originFileObj)
    }

    this.setState({
      previewImage: file.url || file.preview,
      previewVisible: true
    })
  }

  handleChange = ({ fileList }) => {
    this.setState({ fileList })
    if (fileList[0].status === "done") {
      this.props.form.setFieldsValue({
        imgUrl: fileList[0].response.data.imgUrl
      })
    }
  }

  handleSubmit = e => {
    e.preventDefault()
    const token = localStorage.getItem("token")
    this.props.form.validateFields((err, values) => {
      if (!err) {
        values.discount /= 100
        values.uid = this.props.uid
        console.log(values)
        request("/user/manager/goods", {
          method: "POST",
          param: { token },
          body: JSON.stringify(values)
        })
      }
    })
  }

  render() {
    const { getFieldDecorator } = this.props.form
    const { previewVisible, previewImage, fileList, labels } = this.state
    const token = localStorage.getItem("token")
    const uploadButton = (
      <div>
        <Icon type="plus" />
        <div className="ant-upload-text">Upload</div>
      </div>
    )

    return (
      <Form onSubmit={this.handleSubmit} className="goods-add-form">
        <Form.Item {...formItemLayout} label="商品名">
          {getFieldDecorator("name", {
            rules: [
              { required: true, message: "Please input your goods name!" }
            ]
          })(<Input />)}
        </Form.Item>
        <Form.Item {...formItemLayout} label="商品描述">
          {getFieldDecorator("description", {
            rules: [
              {
                required: true,
                message: "Please input your goods description!"
              }
            ]
          })(<Input />)}
        </Form.Item>
        <Form.Item {...formItemLayout} label="价格">
          {getFieldDecorator("pay", {
            rules: [
              {
                required: true,
                message: "Please input your goods pay!"
              }
            ],
            initialValue: 0
          })(
            <InputNumber
              formatter={value =>
                `￥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ",")
              }
              parser={value => value.replace(/￥\s?|(,*)/g, "")}
              step={0.01}
              min={0}
            />
          )}
        </Form.Item>
        <Form.Item {...formItemLayout} label="折扣">
          {getFieldDecorator("discount", {
            initialValue: 0
          })(
            <InputNumber
              min={0}
              step={1}
              max={100}
              formatter={value => `${value}%`}
              parser={value => +value.replace("%", "")}
            />
          )}
        </Form.Item>
        <Form.Item {...formItemLayout} label="分类">
          {getFieldDecorator("lid", {
            rules: [
              {
                required: true,
                message: "Please input your goods label!"
              }
            ],
            initialValue: []
          })(
            <Select
              mode="multiple"
              style={{ width: "100%" }}
              placeholder="Please select"
            >
              {labels.map(label => (
                <Option key={label.lid} value={label.lid}>
                  {label.lname}
                </Option>
              ))}
            </Select>
          )}
        </Form.Item>
        <Form.Item {...formItemLayout} label="商品图片">
          {getFieldDecorator("imgUrl", {
            rules: [
              {
                required: true,
                message: "Please upload image"
              }
            ]
          })(
            <div className="clearfix upload-virtual-box">
              <Upload
                action={`${host}/user/manager/upload?token=${token}`}
                listType="picture-card"
                fileList={fileList}
                onPreview={this.handlePreview}
                onChange={this.handleChange}
                accept="image/*"
              >
                {fileList.length >= 1 ? null : uploadButton}
              </Upload>
              <Modal
                visible={previewVisible}
                footer={null}
                onCancel={this.handleCancel}
              >
                <img
                  alt="example"
                  style={{ width: "100%" }}
                  src={previewImage}
                />
              </Modal>
            </div>
          )}
        </Form.Item>
        <Form.Item wrapperCol={{ offset: 3 }}>
          <Button type="primary" htmlType="submit">
            上传
          </Button>
        </Form.Item>
      </Form>
    )
  }
}

function mapStateToProps(state) {
  const profile = state.manager.profile
  return {
    uid: profile.uid
  }
}

export default connect(mapStateToProps)(
  Form.create({ name: "add_goods" })(AddGoods)
)
