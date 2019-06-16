import * as React from "react"
import ManagerHome from "../containers/manager"

class Manager extends React.Component {
  render() {
    return <ManagerHome {...this.props} />
  }
}

export default Manager
