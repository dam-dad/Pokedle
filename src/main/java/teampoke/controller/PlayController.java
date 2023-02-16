package teampoke.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding.ISuggestionRequest;

import org.controlsfx.control.textfield.TextFields;

import javafx.beans.binding.Bindings;
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
import javafx.util.Callback;
import teampoke.component.PokeInfoComponent;

public class PlayController implements Initializable {

	// model

	private StringProperty pokemonEnviado = new SimpleStringProperty();
	private ListProperty<HBox> pokemonInfoList = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<String> pokemonList = new SimpleListProperty<>(FXCollections.observableArrayList());

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

		pokemonList.add("Pikachu");
		pokemonList.add("Charmander");
		
		// bindings
		pokemonEnviado.bind(pokemonTextField.textProperty());
		sendPokemonButton.disableProperty().bind(pokemonTextField.textProperty().isEmpty());
		sendPokemonButton.disableProperty().bind(
				Bindings.createBooleanBinding(() -> {
					return (!pokemonList.contains(pokemonTextField.textProperty().get()));
				},pokemonTextField.textProperty())
				);

		pokemonListView.itemsProperty().bind(pokemonInfoList);
		
		TextFields.bindAutoCompletion(pokemonTextField, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection<String>>(){

			@Override
			public Collection<String> call(ISuggestionRequest param) {
				return pokemonList.stream()
					.filter(n->n.toLowerCase().startsWith(param.getUserText().toLowerCase()))
					.sorted()
					.collect(Collectors.toList());			
			}
		});
		
		
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

	}

}
