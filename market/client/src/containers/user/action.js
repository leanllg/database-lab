import actionCreator from "../../utils/actionCreator"

export const USRE_PROFILE = "USRE_PROFILE"
export const MODIFY_PROFILE = "MODIFY_PROFILE"

export const addProfile = actionCreator(USRE_PROFILE, "profile")
export const updateProfile = actionCreator(MODIFY_PROFILE, "modified")
