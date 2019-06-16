import { USRE_PROFILE, MODIFY_PROFILE } from "./action"

function user(state = {}, action) {
  switch (action.type) {
    case USRE_PROFILE:
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

export default user
