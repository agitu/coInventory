class App extends React.Component {
    constructor(props) {
        super(props)
        this.onItemClick = this.onItemClick.bind(this)
        this.state = {dataset: <Profile />, isAdm: false}
    }

    componentDidMount() {
        fetch("http://localhost:8080/adm")
            .then(res => res.text())
            .then(res => this.setState({isAdm: res, dataset: (res == "true") ? <ManageUsers /> : <Profile />}))
    }

    onItemClick(event) {
        var datasetId = event.currentTarget.dataset.id
        if (datasetId == "manage-users") {
            this.setState({dataset: <ManageUsers />})
        }
        else if (datasetId == "profile") {
            this.setState({dataset: <Profile />})
        }
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
                {isAdm &&
                <a class="nav-link active" data-toggle="pill" data-id="manage-users" onClick={this.props.onItemClick} aria-current="page" href="#">Manage users</a>
                }
                <a className={isAdm ? "nav-link" : "nav-link active"}  data-toggle="pill" data-id="profile" onClick={this.props.onItemClick} href="#">Profile</a>
            </nav>
        )
    }
}

class ManageUsers extends React.Component {
    render() {
        return (<div>
        <div class="row">
            <div class="input-group">
                <input type="search" class="form-control border-right-0 border" placeholder="Surname" />
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary border-left-0 border" type="button">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
                <button class="btn btn-outline-secondary" type="button" data-toggle="tooltip" data-placement="bottom" title="Add user">
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

class Profile extends React.Component {
    render() {
        return (<b>profile</b>)
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('root')
    )