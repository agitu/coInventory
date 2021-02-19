class App extends React.Component {
    constructor(props) {
        super(props)
        this.onItemClick = this.onItemClick.bind(this)
        this.state = {dataset: "manage-users", isAdm: false}
    }

    componentDidMount() {
        fetch("http://localhost:8080/adm")
            .then(res => res.text())
            .then(res => this.setState({isAdm:res}))
    }

    onItemClick(event) {
        this.setState({dataset: event.currentTarget.dataset.id})
    }

    render() {
        return (
            <div class="row">
                <div class="col-2">
                    <MainMenu onItemClick={this.onItemClick} isAdm={this.state.isAdm} />
                </div>
                <div class="col-10">
                    <b>{this.state.isAdm}</b>
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
        return (<b>1</b>)
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('root')
    )