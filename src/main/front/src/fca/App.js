import './App.css';
import React from 'react';

class HeaderApp extends React.Component {
    openFdaPage() {
        this.props.onPageLoad(true);
    }
    savedEntriesPage() {
        this.props.onPageLoad(false);
    }
    render() {
        return (
            <header>
              <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
                <a className="navbar-brand">Demo app</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                  <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarCollapse">
                  <ul className="navbar-nav mr-auto">
                    <li className={`nav-item ${this.props.isOpenFdaPage ? "active" : ""}`}>
                      <a className="nav-link" onClick={() => this.openFdaPage()}>Fetch data from openFDA</a>
                    </li>
                    <li className={`nav-item ${this.props.isOpenFdaPage ? "" : "active"}`}>
                      <a className="nav-link" onClick={() => this.savedEntriesPage()}>View saved entries</a>
                    </li>
                  </ul>
                </div>
              </nav>
            </header>
        );
    }
}

class Saved extends React.Component {
    render() {
        return (
            <div>SAVED</div>
        );
    }
}

class OpenFda extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            results: [],
            total: 0,
            skip: 0,
            limit: 10,
            searchQuery: ""
        }
    }
    handleInputChanged(event) {
      this.setState({
            results: this.state.result,
            total: this.state.total,
            skip: this.state.skip,
            limit: this.state.limit,
            searchQuery: event.target.value
      });
    }
    handleButtonClicked() {
/*      fetch("http://localhost:8080/v1/open-fda?manufacturerName=" + this.state.searchQuery +
                "&skip=" + this.state.skip + "&limit=" + this.state.limit)*/
        fetch("http://localhost:8080/v1/drugApplication")
          .then(res => {
            res.json();
          })
          .then((data) => {
                debugger;
              this.setState({
                results: data.drugApplication,
                total: data.total,
                skip: data.skip,
                limit: data.limit,
                searchQuery: this.state.searchQuery
              })
              debugger;
          })
          .catch(console.log)
    }
    render() {
        let listItems = (<><tr><td colSpan="5">No data</td></tr></>);
        if (this.state.results && this.state.results.length) {
            listItems = this.state.results.map((row) => {
                return (
                    <tr>
                        <td>1</td>
                        <td>2</td>
                        <td>3</td>
                        <td>4</td>
                        <td>5</td>
                    </tr>
                );
            });
        }
        return (
            <main role="main" className="flex-shrink-0">
              <div className="container">
                <div>
                    <form className="form-inline mt-2 mt-md-0">
                      <input className="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"
                                value={this.state.searchQuery} onChange={this.handleInputChanged.bind(this)} />
                      <button className="btn btn-outline-success my-2 my-sm-0" type="submit"
                                    onClick={this.handleButtonClicked.bind(this)}>Search</button>
                    </form>
                </div>
                <div className="results">
                    <table>
                        <thead>
                            <tr>
                                <th>select</th>
                                <th>App id</th>
                                <th>sad</th>
                                <th>dsfa</th>
                                <th>fa</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listItems}
                        </tbody>
                    </table>
                </div>
              </div>
            </main>
        );
    }
}

export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpenFdaPage: true,
        }
    }
    pageLoad(isOpenFdaPageNew) {
        console.log("isOpenFdaPage: " + isOpenFdaPageNew)
        if (this.state.isOpenFdaPage === isOpenFdaPageNew) {
            return;
        }
        this.setState({
            isOpenFdaPage: isOpenFdaPageNew,
        });
    }
    render() {
        let viewComponent;
        if (this.state.isOpenFdaPage) {
            viewComponent = <OpenFda />
        } else {
            viewComponent = <Saved />
        }
        return (
            <>
                <HeaderApp
                    isOpenFdaPage={this.state.isOpenFdaPage}
                    onPageLoad={(isOpenFdaPage)=> {this.pageLoad(isOpenFdaPage)}} />
                {viewComponent}
            </>
        )
    }
}