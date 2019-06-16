import { host } from "../constants/url"
import { message } from "antd"

function request(api, headers = {}) {
  let url = `${host}${api}`
  const noMessage = headers.noMessage
  if (Object.prototype.toString.call(headers.param) === "[object Object]") {
    url += "?"
    for (let key in headers.param) {
      url += `${key}=${headers.param[key]}&`
    }
    url = url.slice(0, url.length - 1)
  }
  if (headers.method === "POST") {
    if (!headers.headers || !headers.headers["Content-Type"]) {
      headers.headers = headers.headers || {}
      headers.headers["Content-Type"] = "application/json"
    }
  }
  fetch(url, headers)
    .then(res => res.json())
    .then(json => {
      if (json.error !== null) {
        if (typeof headers.onerror === "function") {
          headers.onerror(json)
        }
        !noMessage && message.error(json.msg || json.message || "出现意外情况")
      } else {
        if (typeof headers.onsuccess === "function") {
          headers.onsuccess(json)
        }
        !noMessage && message.success(json.msg || "成功")
      }
    })
    .catch(err => {
      console.log(err)
      message.error("网络错误")
    })
}

export default request
