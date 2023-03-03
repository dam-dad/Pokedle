package teampoke.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding.ISuggestionRequest;
import org.controlsfx.control.textfield.TextFields;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Callback;
import teampoke.component.PokeInfoComponent;
import teampoke.globalstats.GlobalApi;
import teampoke.model.Pokemon;
import teampoke.model.Puntuacion;
import teampoke.pokeapi.PokeApi;

/**
 * 
 * Clase gestora de la ventana de la partida
 *
 */

public class PlayController implements Initializable {

	// model
	private PokeApi pokeapi = new PokeApi();
	private Pokemon pokemonOculto = new Pokemon();
	private Pokemon pokemonEnviado = new Pokemon();
	private ListProperty<HBox> pokemonInfoList = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<String> pokemonList = new SimpleListProperty<>(FXCollections.observableArrayList());
	private int numeroIntentos;
	private Puntuacion punt = new Puntuacion();

	// view
	
	@FXML
	private Button closeButton;

	@FXML
	private Button maxButton;

	@FXML
	private Button minButton;

	@FXML
	private ButtonBar toolBar;

	@FXML
	private MediaView mediaView;

	@FXML
	private Button sendPokemonButton;

	@FXML
	private TextField pokemonTextField;

	@FXML
	private VBox pokeInfo;

	@FXML
	private ListView<HBox> pokemonListView;

	@FXML
    private BorderPane playBorderPane;
	
	@FXML
	private StackPane view;

	/**
	 * Constructor principal de la clase.
	 * 
	 */
	
	public PlayController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Carga el video de fondo y se le asigna a la vista.
	 * Además se configura el video para que se reproduzca indefinidamente.
	 * También bindea la altura y anchura del video a las del view para que sea responsive.
	 * Además contiene los bindings necesarios para el funcionamiento de la partida
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			cargarListaPokemon();
			elegirPokemon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// para el video de fondo
		Media media = new Media(getClass().getResource("/media/fondo.mp4").toString());
		MediaPlayer player = new MediaPlayer(media);
		mediaView.setMediaPlayer(player);
		mediaView.toBack(); // para que el video esté en el fondo
		player.setAutoPlay(true);
		player.setCycleCount(MediaPlayer.INDEFINITE); // para que el video esté en bucle

		// para que el video sea responsive bindeamos el ancho y la altura del video a
		// los de su contenedor
		mediaView.fitWidthProperty().bind(view.widthProperty());
		mediaView.fitHeightProperty().bind(view.heightProperty());
		mediaView.setPreserveRatio(false); // para que el video pueda deformarse

//		// archivo de audio
//		Media audioFile = new Media(getClass().getResource("/media/opening.mp3").toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(audioFile);
//		mediaPlayer.setAutoPlay(true);
//		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//		mediaPlayer.setVolume(1);

		
		
		// para que la información del Pokémon no se muestre al principio
		pokeInfo.setVisible(false);

		// bindings
		pokemonEnviado.nombrePokemonProperty().bind(pokemonTextField.textProperty());
		sendPokemonButton.disableProperty().bind(pokemonTextField.textProperty().isEmpty());
		sendPokemonButton.disableProperty().bind(Bindings.createBooleanBinding(() -> {
			return (!pokemonList.contains(pokemonTextField.textProperty().get()));
		}, pokemonTextField.textProperty()));

		pokemonListView.itemsProperty().bind(pokemonInfoList);

		TextFields.bindAutoCompletion(pokemonTextField,
				new Callback<AutoCompletionBinding.ISuggestionRequest, Collection<String>>() {

					@Override
					public Collection<String> call(ISuggestionRequest param) {
						return pokemonList.stream()
								.filter(n -> n.toLowerCase().startsWith(param.getUserText().toLowerCase())).sorted()
								.collect(Collectors.toList());
					}
				});

	}

	/**
	 * Devuelve la vista principal
	 * @return StackPane instancia del StackPane asociado
	 */
	public StackPane getView() {
		return view;
	}

	/**
	 * Método que se encarga de elegir al Pokémon que el jugador tiene que adivinar
	 * @throws Exception
	 */
	private void elegirPokemon() throws Exception {

		pokemonOculto = pokeapi.getPokemon(pokemonList.get((int) Math.floor(Math.random() * pokemonList.getSize())));
		System.out.println(pokemonOculto.getNombrePokemon());

	}

	/**
	 * Añade los Pokémon conseguidos a través de PokeApi a la lista
	 * @throws IOException
	 */
	private void cargarListaPokemon() throws IOException {
		pokemonList.addAll(pokeapi.getListPokemons());
	}
	
	/**
	 * Cierra la ventana de la aplicación cuando se hace clic en el botón "cerrar".
	 * @param event el MouseEvent que desencadenó este controlador de eventos
	 */
	@FXML
	void onClose(MouseEvent event) {
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}

	/**
	 * Maximiza o restaura la ventana de la aplicación cuando se hace clic en el botón "maximizar".
	 * @param event el MouseEvent que desencadenó este controlador de eventos
	 */
	@FXML
	void onMax(MouseEvent event) {
	    Stage stage = (Stage) maxButton.getScene().getWindow();
	    if (stage.isMaximized()) {
	        stage.setMaximized(false);
	    } else {
	        stage.setMaximized(true);
	    }
	}

	/**
	 * Minimiza la ventana de la aplicación cuando se hace clic en el botón "minimizar".
	 * @param event el MouseEvent que desencadenó este controlador de eventos
	 */
	@FXML
	void onMin(MouseEvent event) {
	    Stage stage = (Stage) minButton.getScene().getWindow();
	    stage.setIconified(true);
	}

	/**
	 * Método asociado al botón de enviar un Pokémon
	 * En función de si el Pokémon ha sido adivinado o no cambia el texto de los label
	 * del componente del Pokémon
	 * @param event 
	 * @throws Exception
	 */
	@FXML
	void onSendPokemon(ActionEvent event) throws Exception {

		PokeInfoComponent pokemonEnviadoInfo = new PokeInfoComponent();

		Pokemon pokemon = pokeapi.getPokemon(pokemonEnviado.getNombrePokemon());

		pokeInfo.setVisible(true); // para que se vea el ListView

		pokemonEnviadoInfo.getPokemonImage().setImage(pokemon.getImagenPokemon());
		pokemonEnviadoInfo.setNombrePokemon(pokemon.getNombrePokemon());
		pokemonEnviadoInfo.setTipoPrimPokemon(pokemon.getTipoPrimPokemon());
		pokemonEnviadoInfo.setTipoSecPokemon(pokemon.getTipoSecPokemon());
		pokemonEnviadoInfo.setPesoPokemon(Math.round(pokemon.getPesoPokemon() * 0.1 * 100d) / 100d + " kg");
		pokemonEnviadoInfo.setAlturaPokemon(Math.round(pokemon.getAlturaPokemon() * 0.1 * 100d) / 100d + " m");
		pokemonEnviadoInfo.setNumPokemon(pokemon.getNumPokemon());
		if(pokemon.isEvoPokemon()) {
			pokemonEnviadoInfo.setEvoPokemon("Evoluciona");
		} else {
			pokemonEnviadoInfo.setEvoPokemon("No evoluciona");
		}
		System.out.println(pokemon.isEvoPokemon());
		if(pokemon.isPreevoPokemon()) {
			pokemonEnviadoInfo.setPreevoPokemon("Tiene preevolución");
		} else {
			pokemonEnviadoInfo.setPreevoPokemon("No tiene preevolución");
		}
		
		pokemonEnviadoInfo.setVidaBasePokemon(pokemon.getVidaBasePokemon());

		pokemonList.remove(pokemon.getNombrePokemon());

		pokemonTextField.setText(null);

		pokemonInfoList.add(0, pokemonEnviadoInfo.getView());

		if(pokemonAdivinado(pokemon, pokemonEnviadoInfo)) {
			punt.setPuntuacion(punt.sumarPuntos(numeroIntentos));
			ventanaPokemonAdivinado();
		}
		
		numeroIntentos++;
		
	}

	/**
	 * Se resetea la lista para elegir a un nuevo Pokémon
	 */
	private void nuevaPartida() {
		try {
			
			pokemonList.removeAll(pokemonList);
			cargarListaPokemon();
			elegirPokemon();
			pokemonInfoList.removeAll(pokemonInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	/**
	 * 
	 * Método encargado de devolver si el Pokémon ha sido adivinado y cambiar 
	 * el css del componente de ese Pokémon
	 * @param pokemon Pokémon enviado por el jugador
	 * @param pokemonEnviadoInfo componente del Pokémon ha modificar
	 * @return boolean si el jugador ha adivinado el Pokémon o no
	 */
	private boolean pokemonAdivinado(Pokemon pokemon, PokeInfoComponent pokemonEnviadoInfo) {
		
		boolean pokemonAdivinado;

		if (pokemonOculto.getNumPokemon() == pokemon.getNumPokemon()) {
			pokemonEnviadoInfo.getTipoPrimarioLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getTipoSecundarioLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getPesoLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getAlturaLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getNumPokedexLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getEvolucionaLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getPreevolucionLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getVidaBaseLabel().getStyleClass().add("bien");
			pokemonAdivinado = true;

			
		} else {
			
			pokemonAdivinado = false;

			if (pokemon.getTipoPrimPokemon().equals(pokemonOculto.getTipoPrimPokemon())) {

				pokemonEnviadoInfo.getTipoPrimarioLabel().getStyleClass().add("bien");
			} else {
				pokemonEnviadoInfo.getTipoPrimarioLabel().getStyleClass().add("mal");
			}

			if (pokemon.getTipoSecPokemon().equals(pokemonOculto.getTipoSecPokemon())) {
				pokemonEnviadoInfo.getTipoSecundarioLabel().getStyleClass().add("bien");
			} else {
				pokemonEnviadoInfo.getTipoSecundarioLabel().getStyleClass().add("mal");
			}

			if (pokemon.getPesoPokemon() < pokemonOculto.getPesoPokemon()) {
				pokemonEnviadoInfo.getPesoLabel().getStyleClass().add("mal-arriba");
			} else if (pokemon.getPesoPokemon() > pokemonOculto.getPesoPokemon()) {
				pokemonEnviadoInfo.getPesoLabel().getStyleClass().add("mal-abajo");
			} else {
				pokemonEnviadoInfo.getPesoLabel().getStyleClass().add("bien");
			}

			if (pokemon.getAlturaPokemon() < pokemonOculto.getAlturaPokemon()) {
				pokemonEnviadoInfo.getAlturaLabel().getStyleClass().add("mal-arriba");
			} else if (pokemon.getAlturaPokemon() > pokemonOculto.getAlturaPokemon()) {
				pokemonEnviadoInfo.getAlturaLabel().getStyleClass().add("mal-abajo");
			} else {
				pokemonEnviadoInfo.getAlturaLabel().getStyleClass().add("bien");
			}

			if (pokemon.getNumPokemon() < pokemonOculto.getNumPokemon()) {
				pokemonEnviadoInfo.getNumPokedexLabel().getStyleClass().add("mal-arriba");
			} else {
				pokemonEnviadoInfo.getNumPokedexLabel().getStyleClass().add("mal-abajo");
			}

			if (pokemon.isEvoPokemon() == pokemonOculto.isEvoPokemon()) {
				pokemonEnviadoInfo.getEvolucionaLabel().getStyleClass().add("bien");
			} else {
				pokemonEnviadoInfo.getEvolucionaLabel().getStyleClass().add("mal");
			}

			if (pokemon.isPreevoPokemon() == pokemonOculto.isPreevoPokemon()) {
				pokemonEnviadoInfo.getPreevolucionLabel().getStyleClass().add("bien");
			} else {
				pokemonEnviadoInfo.getPreevolucionLabel().getStyleClass().add("mal");
			}
			
			if (pokemonEnviado.getVidaBasePokemon() < (pokemonOculto.getVidaBasePokemon())) {
				pokemonEnviadoInfo.getVidaBaseLabel().getStyleClass().add("mal-arriba");
			} else {
				pokemonEnviadoInfo.getVidaBaseLabel().getStyleClass().add("mal-abajo");
			}
			
		}

		return pokemonAdivinado;
		
		
	}
	
	/**
	 * Cuando el jugador adivina el Pokémon
	 * se abre la ventana que le da la opción de seguir jugando o terminar la partida
	 */
	private void ventanaPokemonAdivinado() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("¡Enhorabuena!");
		alert.setHeaderText("¡Has ganado!");
		alert.setContentText("¿Quieres volver a jugar?");
		ButtonType buttonPlayAgain = new ButtonType("Jugar de nuevo");
		ButtonType buttonQuit = new ButtonType("Salir");
		alert.getButtonTypes().setAll(buttonPlayAgain, buttonQuit);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonPlayAgain) {
			nuevaPartida();
		} else {
		    TextInputDialog dialog = new TextInputDialog("");
		    dialog.setTitle("Salir del juego");
		    dialog.setHeaderText("Introduce tu nombre de usuario:");
		    dialog.setContentText("Nombre de usuario:");
		    Optional<String> userName = dialog.showAndWait();
		    if (userName.isPresent()) {
		        String nombreUsuario = userName.get();
		        GlobalApi.addPuntuacion(nombreUsuario, punt.getPuntuacion());
		        Stage stage = (Stage) closeButton.getScene().getWindow();
		        stage.close();
		    }
		}
	}

}
