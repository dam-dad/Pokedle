package teampoke.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PlayController implements Initializable {

	// model
	
	private StringProperty pokemonProperty = new SimpleStringProperty();
	
	// view
	@FXML
	private MediaView mediaView;
	
	@FXML
    private Button sendPokemonButton;

	@FXML
    private TextField pokemonTextField;
	
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
		Media media = new Media(Paths.get("media/fondo.mp4").toUri().toString());
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
		
		// bindings
		pokemonProperty.bind(pokemonTextField.textProperty());

	}
	
	public StackPane getView() {
		return view;
	}
	
	@FXML
    void onSendPokemon(ActionEvent event) {
		System.out.println(pokemonProperty.get());
		pokemonTextField.setText(null);
    }
	
	public Button getSendPokemonButton() {
		return sendPokemonButton;
	}

}
