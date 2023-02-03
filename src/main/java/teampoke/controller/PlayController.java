package teampoke.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import teampoke.component.PokeInfoComponent;

public class PlayController implements Initializable {

	// model

	private StringProperty pokemonEnviado = new SimpleStringProperty();
	private ListProperty<HBox> pokemonInfoList = new SimpleListProperty<>(FXCollections.observableArrayList());

	// view
	
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
	void onSendPokemon(ActionEvent event) {

		pokeInfo.setVisible(true); // para que se vea el ListView
		PokeInfoComponent pokemonEnviadoInfo = new PokeInfoComponent();
		pokemonEnviadoInfo.setNombrePokemon(pokemonEnviado.get());
		pokemonEnviadoInfo.setTipoPrimPokemon("Eléctrico");
		pokemonEnviadoInfo.setTipoSecPokemon("No tiene");
		pokemonEnviadoInfo.setPesoPokemon("10 kg");
		pokemonEnviadoInfo.setAlturaPokemon("0,4 metros");
		pokemonEnviadoInfo.setNumPokemon("25");
		pokemonEnviadoInfo.setEvoPokemon("Sí");
		pokemonEnviadoInfo.setPreevoPokemon("Sí");
		pokemonEnviadoInfo.setFormaDeEvoPokemon("Piedra");
		
		pokemonTextField.setText(null);
		
		if (pokemonEnviadoInfo.getNombrePokemon().trim().toUpperCase().equals("PIKACHU")) {
			pokemonEnviadoInfo.getPokemonImage().setImage(new Image(getClass().getResource("/images/pikachu120px.png").toString()));
			pokemonEnviadoInfo.getTipoPrimarioLabel().setStyle("-fx-background-color: green;");
			pokemonEnviadoInfo.getTipoSecundarioLabel().setStyle("-fx-background-color: green;");
			pokemonEnviadoInfo.getPesoLabel().setStyle("-fx-background-color: green;");
			pokemonEnviadoInfo.getAlturaLabel().setStyle("-fx-background-color: green;");
			pokemonEnviadoInfo.getNumPokedexLabel().setStyle("-fx-background-color: green;");
			pokemonEnviadoInfo.getEvolucionaLabel().setStyle("-fx-background-color: green;");
			pokemonEnviadoInfo.getPreevolucionLabel().setStyle("-fx-background-color: green;");
			pokemonEnviadoInfo.getManeraEvolucionarLabel().setStyle("-fx-background-color: green;");

		} else {
			pokemonEnviadoInfo.getPokemonImage().setImage(new Image(getClass().getResource("/images/charmander120px.png").toString()));
			pokemonEnviadoInfo.getTipoPrimarioLabel().setStyle("-fx-background-color: red;");
			pokemonEnviadoInfo.getTipoSecundarioLabel().setStyle("-fx-background-color: red;");
			pokemonEnviadoInfo.getPesoLabel().setStyle("-fx-background-color: red;");
			pokemonEnviadoInfo.getAlturaLabel().setStyle("-fx-background-color: red;");
			pokemonEnviadoInfo.getNumPokedexLabel().setStyle("-fx-background-color: red;");
			pokemonEnviadoInfo.getEvolucionaLabel().setStyle("-fx-background-color: red;");
			pokemonEnviadoInfo.getPreevolucionLabel().setStyle("-fx-background-color: red;");
			pokemonEnviadoInfo.getManeraEvolucionarLabel().setStyle("-fx-background-color: red;");
		}
		pokemonInfoList.add(0, pokemonEnviadoInfo.getView());
		
	}

}
