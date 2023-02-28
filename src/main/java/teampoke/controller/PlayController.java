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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Callback;
import teampoke.component.PokeInfoComponent;
import teampoke.model.Pokemon;
import teampoke.pokeapi.PokeApi;

public class PlayController implements Initializable {

	// model
	private PokeApi pokeapi = new PokeApi();
	private Pokemon pokemonOculto = new Pokemon();
	private Pokemon pokemonEnviado = new Pokemon();
	private ListProperty<HBox> pokemonInfoList = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<String> pokemonList = new SimpleListProperty<>(FXCollections.observableArrayList());
	private int numeroDePokemonAdivinados;

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

		try {
			cargarListaPokemon();
			elegirPokemon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public StackPane getView() {
		return view;
	}

	public void elegirPokemon() throws Exception {

		pokemonOculto = pokeapi.getPokemon(pokemonList.get((int) Math.floor(Math.random() * pokemonList.getSize())));
		System.out.println(pokemonOculto);

	}

	public void cargarListaPokemon() throws IOException {
		pokemonList.addAll(pokeapi.getListPokemons());
	}

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
		pokemonEnviadoInfo.setEvoPokemon(pokemon.isEvoPokemon());
		pokemonEnviadoInfo.setPreevoPokemon(pokemon.isPreevoPokemon());
		pokemonEnviadoInfo.setFormaDeEvoPokemon(pokemon.getFormaDeEvoPokemon());

		pokemonList.remove(pokemon.getNombrePokemon());

		pokemonTextField.setText(null);

		pokemonInfoList.add(0, pokemonEnviadoInfo.getView());

		if(pokemonAdivinado(pokemon, pokemonEnviadoInfo)) {
			nuevaPartida();
		}
		
	}

	private void nuevaPartida() {
		try {
			elegirPokemon();
			cargarListaPokemon();
			pokemonInfoList.removeAll(pokemonInfoList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public boolean pokemonAdivinado(Pokemon pokemon, PokeInfoComponent pokemonEnviadoInfo) {
		
		boolean pokemonAdivinado;

		if (pokemonOculto.getNumPokemon() == pokemon.getNumPokemon()) {
			pokemonEnviadoInfo.getTipoPrimarioLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getTipoSecundarioLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getPesoLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getAlturaLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getNumPokedexLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getEvolucionaLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getPreevolucionLabel().getStyleClass().add("bien");
			pokemonEnviadoInfo.getManeraEvolucionarLabel().getStyleClass().add("bien");
			numeroDePokemonAdivinados++;
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
			
//			if (pokemonEnviado.getFormaDeEvoPokemon().equals(pokemonOculto.getFormaDeEvoPokemon())) {
//				pokemonEnviadoInfo.getManeraEvolucionarLabel().getStyleClass().add("bien");
//			} else {
//				pokemonEnviadoInfo.getManeraEvolucionarLabel().getStyleClass().add("mal");
//			}
			
		}

		return pokemonAdivinado;
		
	}

}
