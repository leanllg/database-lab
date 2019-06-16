import React from "react"
import { Router } from "@reach/router"
import { Provider } from "react-redux"
import { createStore, compose, applyMiddleware } from "redux"
import reduxThunk from "redux-thunk"
import rootReducer from "./reducers"
import "./App.css"
import Account from "./pages/account"
import Manager from "./pages/manager"
import User from "./pages/user"

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose

function App() {
  return (
    <Provider
      store={createStore(
        rootReducer,
        composeEnhancers(applyMiddleware(reduxThunk))
      )}
    >
      <Router>
        <Account path="/account" />
        <Manager path="/manager/*" />
        <User path="/user/*" />
      </Router>
    </Provider>
  )
}

export default App
