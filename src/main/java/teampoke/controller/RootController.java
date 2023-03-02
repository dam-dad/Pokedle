package teampoke.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import teampoke.app.App;

public class RootController implements Initializable {

	// controller

	PlayController playController = new PlayController();

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
	private StackPane view;

	@FXML
	private MediaView mediaView;

	@FXML
	private ImageView logo;

	@FXML
	private Label startLabel;

	@FXML
	private BorderPane borderPane;
	
	@FXML
    private Button volumenButton;


	public RootController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StartView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Media audioFile = new Media(getClass().getResource("/media/Opening.mp3").toString());
	MediaPlayer mediaPlayer = new MediaPlayer(audioFile);
	
	public MediaPlayer getMediaPlayer() {
	    return mediaPlayer;
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

		// animación del texto
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), startLabel);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setCycleCount(Animation.INDEFINITE);
		fadeTransition.play();

		// archivo de audio
//		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//		mediaPlayer.play();
//		


	}

	public StackPane getView() {
		return view;
	}

	@FXML
	void onMousePressed(MouseEvent event) {
		cambiarEscena();
	}

	public void onEnterPressed() {
		cambiarEscena();
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
	
	@FXML
	void onVolumenButton(MouseEvent event) {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VolumenView.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.initStyle(StageStyle.UNDECORATED);
	        stage.initModality(Modality.APPLICATION_MODAL); // establecer modalidad
	        stage.setResizable(false); // no redimensionable
	        stage.setScene(scene);
	        stage.showAndWait(); // esperar hasta que se cierre la ventana

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void cambiarEscena() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayView.fxml"));

		loader.setController(playController);
		Scene scene = new Scene(playController.getView());
		App.primaryStage.setTitle("Pokédle");
		App.primaryStage.setScene(scene);
		App.primaryStage.getIcons().add(new Image("/images/pokedle_icon_32px.png"));
		Image image = new Image("/images/cursor.png");
		scene.setCursor(new ImageCursor(image));

		App.primaryStage.setScene(scene);

	}

}
