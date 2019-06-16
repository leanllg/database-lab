import * as React from "react"
import { Tabs, Row, Col } from "antd"
import Login from "../containers/login"
import Registration from "../containers/registration"

const { TabPane } = Tabs

class Account extends React.PureComponent {
  render() {
    return (
      <Row type="flex" justify="center" align="middle">
        <Col span={10} xl={8}>
          <Tabs
            animated={false}
            tabBarStyle={{ textAlign: "center", marginTop: "15%" }}
          >
            <TabPane tab="登陆" key="1">
              <Login />
            </TabPane>
            <TabPane tab="注册" key="2">
              <Registration />
            </TabPane>
          </Tabs>
        </Col>
      </Row>
    )
  }
}

export default Account
