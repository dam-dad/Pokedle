package teampoke.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import teampoke.app.App;
import teampoke.component.PokeInfoComponent;

public class PlayController implements Initializable {

	// model

	private StringProperty pokemonEnviado = new SimpleStringProperty();
	private ListProperty<HBox> pokemonInfoList = new SimpleListProperty<>(FXCollections.observableArrayList());

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
	private StackPane view;

	public PlayController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// para el video de fondo
		Media media = new Media(getClass().getResource("/media/fondo.mp4").toString());
		MediaPlayer player = new MediaPlayer(media);
		mediaView.setMediaPlayer(player);
		mediaView.toBack(); // para que el video esté en el fondo
		player.setVolume(0);
		player.setAutoPlay(true);
		player.setCycleCount(MediaPlayer.INDEFINITE); // para que el video esté en bucle

		// para que el video sea responsive bindeamos el ancho y la altura del video a
		// los de su contenedor
		mediaView.fitWidthProperty().bind(view.widthProperty());
		mediaView.fitHeightProperty().bind(view.heightProperty());
		mediaView.setPreserveRatio(false); // para que el video pueda deformarse

		// archivo de audio
		Media audioFile = new Media(getClass().getResource("/media/Opening.mp3").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(audioFile);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setVolume(1.5);
		mediaPlayer.play();

		// para que la información del Pokémon no se muestre al principio
		pokeInfo.setVisible(false);

		// bindings
		pokemonEnviado.bind(pokemonTextField.textProperty());
		sendPokemonButton.disableProperty().bind(pokemonTextField.textProperty().isEmpty());

		pokemonListView.itemsProperty().bind(pokemonInfoList);

	}

	public StackPane getView() {
		return view;
	}

	@FXML
	void onClose(MouseEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void onMax(MouseEvent event) {
		Stage stage = (Stage) maxButton.getScene().getWindow();
		if (stage.isMaximized()) {
			stage.setMaximized(false);
		} else {
			stage.setMaximized(true);
		}
	}

	@FXML
	void onMin(MouseEvent event) {
		Stage stage = (Stage) minButton.getScene().getWindow();
		stage.setIconified(true);
	}
	
	boolean adivinado;

	@FXML
	void onSendPokemon(ActionEvent event) {
		
		pokeInfo.setVisible(true); // para que se vea el ListView
		PokeInfoComponent pokemonEnviadoInfo = new PokeInfoComponent();
		pokemonEnviadoInfo.setNombrePokemon(pokemonEnviado.get());
		pokemonEnviadoInfo.setTipoPrimPokemon("Eléctrico");
		pokemonEnviadoInfo.setTipoSecPokemon("No tiene");
		pokemonEnviadoInfo.setPesoPokemon("10 kg");
		pokemonEnviadoInfo.setAlturaPokemon("0,4 m");
		pokemonEnviadoInfo.setNumPokemon("25");
		pokemonEnviadoInfo.setEvoPokemon("Sí");
		pokemonEnviadoInfo.setPreevoPokemon("Sí");
		pokemonEnviadoInfo.setFormaDeEvoPokemon("Piedra");

		pokemonTextField.setText(null);

		if (pokemonEnviadoInfo.getNombrePokemon().trim().toUpperCase().equals("PIKACHU")) {
			pokemonEnviadoInfo.getPokemonImage()
					.setImage(new Image(getClass().getResource("/images/pikachu_sprite.png").toString()));
			pokemonEnviadoInfo.getTipoPrimarioLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getTipoSecundarioLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getPesoLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getAlturaLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getNumPokedexLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getEvolucionaLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getPreevolucionLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getManeraEvolucionarLabel().getStyleClass().add("bien");
			adivinado=true;

		} else {
			pokemonEnviadoInfo.getPokemonImage()
					.setImage(new Image(getClass().getResource("/images/charmander_sprite.png").toString()));
			pokemonEnviadoInfo.getTipoPrimarioLabel().getStyleClass().add("mal");
			pokemonEnviadoInfo.getTipoSecundarioLabel().getStyleClass().add("mal");
			pokemonEnviadoInfo.getPesoLabel().getStyleClass().add("mal-arriba");
			pokemonEnviadoInfo.getAlturaLabel().getStyleClass().add("mal-arriba");
			;
			pokemonEnviadoInfo.getNumPokedexLabel().getStyleClass().add("mal-abajo");
			pokemonEnviadoInfo.getEvolucionaLabel().getStyleClass().add("mal");
			pokemonEnviadoInfo.getPreevolucionLabel().getStyleClass().add("mal");
			pokemonEnviadoInfo.getManeraEvolucionarLabel().getStyleClass().add("mal");
		}
		pokemonInfoList.add(0, pokemonEnviadoInfo.getView());
		
		if (adivinado) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("¡Enhorabuena!");
			alert.setHeaderText("¡Has ganado!");
			alert.setContentText("¿Quieres volver a jugar?");
			ButtonType buttonPlayAgain = new ButtonType("Jugar de nuevo");
			ButtonType buttonQuit = new ButtonType("Salir");
			alert.getButtonTypes().setAll(buttonPlayAgain, buttonQuit);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonPlayAgain) {
				reiniciarJuego();
			} else {
				Stage stage = (Stage) closeButton.getScene().getWindow();
				stage.close();
			} 
		}

	}
	
	
	
	
	public void reiniciarJuego() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayView.fxml"));

		loader.setController(this);
		Scene scene = new Scene(this.getView());
		App.primaryStage.setTitle("Pokédle");
		App.primaryStage.setScene(scene);
		App.primaryStage.getIcons().add(new Image("/images/pokedle_icon_32px.png"));
		Image image = new Image("/images/cursor.png");
		scene.setCursor(new ImageCursor(image));

		App.primaryStage.setScene(scene);

	}

}


