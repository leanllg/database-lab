import * as React from "react"
import request from "../../utils/request"
import { Router } from "@reach/router"
import { Layout, Menu, Icon } from "antd"
import { addProfile } from "./action"
import { connect } from "react-redux"
import Profile from "./profile"
import AllGoods from "./goods/all.js"
import Order from "./order"

const { Content, Footer, Sider } = Layout
const { SubMenu } = Menu

class User extends React.Component {
  state = {
    collapsed: false
  }

  onCollapse = collapsed => {
    console.log(collapsed)
    this.setState({ collapsed })
  }
  fetchUserProfile() {
    const { navigate } = this.props
    const token = localStorage.getItem("token") || ""
    const { addProfile } = this.props

    request("/user/profile", {
      param: { token },
      onsuccess: json => {
        addProfile(json.data.user)
      },
      onerror: json => {
        navigate("/account")
      }
    })
  }
  componentDidMount() {
    this.fetchUserProfile()
  }
  handleMenuClick = ({ key }) => {
    const { navigate } = this.props
    navigate(key)
  }
  render() {
    const { isLogin } = this.props
    return (
      <>
        {!isLogin && ""}
        {isLogin && (
          <Layout style={{ minHeight: "100vh" }}>
            <Sider
              collapsible
              collapsed={this.state.collapsed}
              onCollapse={this.onCollapse}
            >
              <Menu
                theme="dark"
                defaultSelectedKeys={["profile"]}
                mode="inline"
              >
                <Menu.Item onClick={this.handleMenuClick} key="profile">
                  <Icon type="pie-chart" />
                  <span>Profile</span>
                </Menu.Item>
                <SubMenu
                  key="sub1"
                  title={
                    <span>
                      <Icon type="user" />
                      <span>Goods</span>
                    </span>
                  }
                >
                  <Menu.Item onClick={this.handleMenuClick} key="goods/all">
                    All
                  </Menu.Item>
                </SubMenu>
                <Menu.Item onClick={this.handleMenuClick} key="orders">
                  <Icon type="file" />
                  <span>Order</span>
                </Menu.Item>
              </Menu>
            </Sider>
            <Layout>
              <Content style={{ margin: "30px" }}>
                <Router>
                  <Profile path="profile" />
                  <AllGoods path="goods/all" goods={[1]} />
                  <Order path="orders" />
                </Router>
              </Content>
              <Footer style={{ textAlign: "center" }}>
                @copyright 2019 LLGZONE
              </Footer>
            </Layout>
          </Layout>
        )}
      </>
    )
  }
}

function mapStateToProps(state) {
  return {
    isLogin: state.user.isLogin
  }
}

export default connect(
  mapStateToProps,
  { addProfile }
)(User)
