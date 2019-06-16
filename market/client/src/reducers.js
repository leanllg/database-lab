import { combineReducers } from "redux"
import manager from "./containers/manager/reducer"
import user from "./containers/user/reducer"

export default combineReducers({
  manager,
  user
})
