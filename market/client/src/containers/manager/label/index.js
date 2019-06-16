import * as React from "react"
import { Tag, Input, Tooltip, Icon, Button } from "antd"
import request from "../../../utils/request"

class Label extends React.Component {
  state = {
    tags: [],
    inputVisible: false,
    inputValue: ""
  }

  handleSubmitClick = () => {
    console.log(this.state)
    const token = localStorage.getItem("token")
    request("/user/manager/labels", {
      method: "POST",
      param: { token },
      body: JSON.stringify(this.state.tags)
    })
  }

  handleClose = removedTag => {
    const tags = this.state.tags.filter(tag => tag !== removedTag)
    console.log(tags)
    this.setState({ tags })
  }

  showInput = () => {
    this.setState({ inputVisible: true }, () => this.input.focus())
  }

  handleInputChange = e => {
    this.setState({ inputValue: e.target.value })
  }

  handleInputConfirm = () => {
    const { inputValue } = this.state
    let { tags } = this.state
    if (inputValue && tags.indexOf(inputValue) === -1) {
      tags = [...tags, inputValue]
    }
    console.log(tags)
    this.setState({
      tags,
      inputVisible: false,
      inputValue: ""
    })
  }

  saveInputRef = input => (this.input = input)

  render() {
    const { tags, inputVisible, inputValue } = this.state
    return (
      <div>
        <div>
          {tags.map((tag, index) => {
            const isLongTag = tag.length > 20
            const tagElem = (
              <Tag
                key={tag}
                closable={index !== 0}
                onClose={() => this.handleClose(tag)}
              >
                {isLongTag ? `${tag.slice(0, 20)}...` : tag}
              </Tag>
            )
            return isLongTag ? (
              <Tooltip title={tag} key={tag}>
                {tagElem}
              </Tooltip>
            ) : (
              tagElem
            )
          })}
          {inputVisible && (
            <Input
              ref={this.saveInputRef}
              type="text"
              size="small"
              style={{ width: 78 }}
              value={inputValue}
              onChange={this.handleInputChange}
              onBlur={this.handleInputConfirm}
              onPressEnter={this.handleInputConfirm}
            />
          )}
          {!inputVisible && (
            <Tag
              onClick={this.showInput}
              style={{ background: "#fff", borderStyle: "dashed" }}
            >
              <Icon type="plus" /> New Label
            </Tag>
          )}
        </div>
        <Button
          onClick={this.handleSubmitClick}
          style={{ marginTop: "30px" }}
          type="primary"
          htmlType="submit"
        >
          Submit
        </Button>
      </div>
    )
  }
}

export default Label
