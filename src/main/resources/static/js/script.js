// Script para realizar la consulta de la pelicula.
class Script extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            movie: "",
            jsonRes: {},
            interval: "",
        };
        this.handleChangeInput = this.handleChangeInput.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    //Funcion para cambiar el valor del input
    handleChangeInput(event) {
        this.setState({ movie: event.target.value });
    }

    //Funcion para controlar redireccionamiento
    handleSubmit(event) {
        this.loadPostMsg();
        event.preventDefault();
    }

    //Funcion para realizar la consulta de la pelicula. Mapea el json y lo muestra en el html
    loadPostMsg() {
        let url =
            "/movie?movie=" + this.state.movie;
        fetch(url, { method: "GET" })
            .then((x) => x.json())
            .then((y) => {
                //Create a new visualizer object
                var _visualizer = new visualizer($("#resMSG"));
                //Visualize the demo json object
                _visualizer.visualize(y);
            });
    }

    //Funcion que muestra el html
    render() {
        return (
            <div>
                <h1>Movies Info</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>Pelicula: </label>
                    <input
                        required
                        type="text"
                        placeholder="Ingrese nombre pelicula"
                        value={this.state.movie}
                        onChange={this.handleChangeInput}
                    ></input>
                    <input type="submit" value="Consultar" />
                </form>
            </div>
        );
    }
}

ReactDOM.render(<Script />, document.getElementById("root"));
