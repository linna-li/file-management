import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddFile from "./components/add-file.component";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            File Management
          </Link>
          <div className="navbar-nav mr-auto">
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path="/" component={AddFile} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
