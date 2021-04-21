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
    constructor(props) {
        super(props);
        this.downloadData();
    }
    downloadData() {
      fetch("/v1/drug-application")
          .then(response => response.json())
          .then((data) => {
              this.setState({
                results: data,
                total: data.length,
                skip: 0,
                limit: data.length,
              });

          })
          .catch(console.log)
    }
    render() {
        let listItems;
        if (this.state && this.state.results && this.state.results.length) {
            listItems = this.state.results.map((r) => {
                return (
                    <SingleRow key={r.applicationNumber} readOnly={true} onSave={this.saveRow} row={r} />
                );
            });
        }
        return (
<main role="main" className="flex-shrink-0">
              <div className="container">
                <div className="results">
                    <table>
                        <thead>
                            <tr>
                                <th>action</th>
                                <th>Application Id</th>
                                <th>Manufacturer name</th>
                                <th>Substance name</th>
                                <th>Product numbers</th>
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

class SingleRow extends React.Component {
    save(event) {
        event.preventDefault();
        this.props.onSave(this.props.row);
    }
    render() {
        let saveButton = (<><p></p></>);
        if(!this.props.readOnly) {
            saveButton = (<><button onClick={this.save.bind(this)}>save</button></>);
        }
        return (
            <tr>
                <td>{saveButton}</td>
                <td>{this.props.row.applicationNumber}</td>
                <td>{this.props.row.manufacturerName.join(", ")}</td>
                <td>{this.props.row.substanceName.join(", ")}</td>
                <td>{this.props.row.productNumbers.join(", ")}</td>
            </tr>
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
    handleButtonClicked(event) {
        event.preventDefault();
      fetch("/v1/open-fda?manufacturerName=" + this.state.searchQuery +
                "&skip=" + this.state.skip + "&limit=" + this.state.limit)
          .then(response => response.json())
          .then((data) => {
              this.setState({
                results: data.drugApplication,
                total: data.total,
                skip: data.skip,
                limit: data.limit,
                searchQuery: this.state.searchQuery
              })
          })
          .catch(console.log)
    }
    saveRow(row) {
        const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(row)
            };
        fetch("/v1/drug-application", requestOptions)
                .then(response => response.json())
                .then(data => {
                    alert("saved");
                });
    }
    render() {
        let listItems = (<><tr><td colSpan="5">No data</td></tr></>);
        if (this.state.results && this.state.results.length) {
            listItems = this.state.results.map((r) => {
                return (
                    <SingleRow key={r.applicationNumber} readOnly={false} onSave={this.saveRow} row={r} />
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
                                <th>action</th>
                                <th>Application Id</th>
                                <th>Manufacturer name</th>
                                <th>Substance name</th>
                                <th>Product numbers</th>
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