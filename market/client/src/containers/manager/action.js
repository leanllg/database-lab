import actionCreator from "../../utils/actionCreator"

export const MANAGER_PROFILE = "MANAGER_PROFILE"
export const MODIFY_PROFILE = "MODIFY_PROFILE"

export const addProfile = actionCreator(MANAGER_PROFILE, "profile")
export const updateProfile = actionCreator(MODIFY_PROFILE, "modified")
