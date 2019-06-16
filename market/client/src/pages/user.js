import * as React from "react"
import UserHome from "../containers/user"

class User extends React.Component {
  render() {
    return <UserHome {...this.props} />
  }
}

export default User
