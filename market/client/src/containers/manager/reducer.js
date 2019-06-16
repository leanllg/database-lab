import { MANAGER_PROFILE, MODIFY_PROFILE } from "./action"

function manager(state = {}, action) {
  switch (action.type) {
    case MANAGER_PROFILE:
      return {
        ...state,
        profile: action.profile,
        isLogin: true
      }
    case MODIFY_PROFILE:
      return {
        ...state,
        profile: { ...state.profile, ...action.modified }
      }
    default:
      return state
  }
}

export default manager
