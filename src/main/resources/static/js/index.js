class App extends React.Component {
    constructor(props) {
        super(props)
        this.onItemClick = this.onItemClick.bind(this)
        this.addUserClick = this.addUserClick.bind(this)
        this.saveManageUsers = this.saveManageUsers.bind(this)
        this.cancelClick = this.cancelClick.bind(this)
        this.addUserSuccess = this.addUserSuccess.bind(this)
        this.addUserError = this.addUserError.bind(this)
        this.datasetId = ""
        this.state = {dataset: <Profile />, isAdm: "false"}
    }

    componentDidMount() {
        fetch("http://localhost:8080/adm")
            .then(res => res.text())
            .then(res => {
                this.setState({isAdm: res, dataset: (res == "true") ? <ManageUsers saveState={this.saveManageUsers} searchValue="" addUserClick={this.addUserClick} /> : <Profile />})
                this.datasetId = (res == "true") ? "manage-users" : "profile"
            })
    }

    showMainPage(datasetId) {
        if (datasetId == "manage-users") {
            var searchValue = (this.state.manageUsers && this.state.manageUsers.searchValue) || ""
            this.setState({dataset: <ManageUsers saveState={this.saveManageUsers} searchValue={searchValue} addUserClick={this.addUserClick} />})
        }
        else if (datasetId == "profile") {
            this.setState({dataset: <Profile />})
        }
    }

    onItemClick(event) {
        this.datasetId = event.currentTarget.dataset.id
        this.showMainPage(this.datasetId)
    }

    addUserSuccess() {
        this.setState({dataset: <InfoPage text="New user successfully saved" backClick={this.cancelClick} addMoreClick={this.addUserClick} />})
    }

    addUserError() {
        this.setState({dataset: <InfoPage text="Error user save" backClick={this.cancelClick} />})
    }

    addUserClick(event) {
        this.setState({dataset: <AddUser cancelClick={this.cancelClick} success={this.addUserSuccess} error={this.addUserError} />})
    }

    saveManageUsers() {
        this.setState({manageUsers: {searchValue:document.getElementById("search").value}})
    }

    cancelClick(event) {
        this.showMainPage(this.datasetId)
    }

    render() {
        return (
            <div class="row">
                <div class="col-2">
                    <MainMenu onItemClick={this.onItemClick} isAdm={this.state.isAdm} />
                </div>
                <div class="col-10">
                    {this.state.dataset}
                </div>
            </div>
        )
    }
}

class MainMenu extends React.Component {
    render() {
        const isAdm = this.props.isAdm
        return (
            <nav class="nav flex-column nav-pills">
                {isAdm == "true" &&
                <a class="nav-link active" data-toggle="pill" data-id="manage-users" onClick={this.props.onItemClick} aria-current="page" href="#">Manage users</a>
                }
                <a className={isAdm == "true" ? "nav-link" : "nav-link active"}  data-toggle="pill" data-id="profile" onClick={this.props.onItemClick} href="#">Profile</a>
            </nav>
        )
    }
}

class ManageUsers extends React.Component {
    constructor(props) {
        super(props)
    }

    componentDidMount() {
        document.getElementById("search").value = this.props.searchValue
    }

    componentWillUnmount() {
        this.props.saveState()
    }

    render() {
        return (<div>
        <div class="row">
            <div class="input-group">
                <input id="search" type="text" class="form-control border-right-0 border" placeholder="Surname" />
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary border-left-0 border" type="button">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
                <button class="btn btn-outline-secondary" type="button" onClick={this.props.addUserClick} data-toggle="tooltip" data-placement="bottom" title="Add user">
                    <i class="fa fa-user-plus"></i>
                </button>
            </div>
        </div>
        <div class="row">
            <table class="table">

            </table>
        </div>
        </div>)
    }
}

class AddUser extends React.Component {
    constructor(props) {
        super(props)
        this.state = {enableAccount: false}
        this.changeAccountForm = this.changeAccountForm.bind(this)
        this.saveForm = this.saveForm.bind(this)
    }

    changeAccountForm(event) {
        this.setState({enableAccount: event.target.checked})
    }

    saveForm() {
        var form = document.querySelector("form")
        var values = {uname: form.uname.value, surname: form.surname.value}
        if (form.checkAccount.checked) {
            values['email'] = form.email.value
            values['role'] = form.role.value
            values['password'] = form.password.value
        }

        fetch("/user", {
            method: "POST",
            headers: { 'Content-Type': 'application/json;charset=utf-8' },
            body: JSON.stringify(values) })
            .then(res => { if (res.ok) { this.props.success() } else { this.props.error() }})
    }

    render() {
        return (<div>
                <div class="row col-6">
                    <form class="col-12">
                        <fieldset>
                            <legend>Add user</legend>
                            <div class="form-group">
                                <label for="uname">Name</label>
                                <input type="text" class="form-control" name="uname" id="uname" />
                            </div>
                            <div class="form-group">
                                <label for="surname">Surname</label>
                                <input type="text" class="form-control" name="surname" id="surname" />
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="checkAccount" id="checkAccount" onChange={this.changeAccountForm} />
                                <label class="form-check-label" for="checkAccount">Add account</label>
                            </div>
                        </fieldset>
                        <fieldset disabled={!this.state.enableAccount}>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" id="email" name="email" />
                            </div>
                            <div class="form-group">
                                <label for="role">Role</label>
                                <select class="form-control" name="role" id="role">
                                    <option value="user">User</option>
                                    <option value="administrator">Administrator</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password" />
                            </div>
                        </fieldset>
                        <div class="d-flex justify-content-center">
                            <button type="button" class="btn btn-primary mx-2" onClick={this.props.cancelClick}>Cancel</button>
                            <button type="button" class="btn btn-success" onClick={this.saveForm}>Save</button>
                        </div>
                    </form>
                </div>
                </div>)
    }
}

class InfoPage extends React.Component {
    render() {
        return (
            <div>
                <div class="row">
                    <h2>{this.props.text}</h2>
                </div>
                <div class="row">
                    {this.props.backClick !== undefined &&
                        <button type="button" class="btn btn-primary mx-2" onClick={this.props.backClick}>Back</button>
                    }
                    {this.props.addMoreClick !== undefined &&
                        <button type="button" class="btn btn-success" onClick={this.props.addMoreClick}>Add more</button>
                    }
                </div>
            </div>)

    }
}

class Profile extends React.Component {
    render() {
        return (<b>profile</b>)
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('root')
    )