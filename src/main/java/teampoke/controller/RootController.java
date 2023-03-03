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
import javafx.stage.Stage;
import javafx.util.Duration;
import teampoke.app.App;

/**
 * 
 * Clase gestora inicial del proyecto
 *
 */
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

	/**
	 * Constructor principal de la clase
	 */
	public RootController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StartView.fxml"));
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
	 * Por último se le da una animación al texto que aparece al ejecutar la aplicación
	 */
	
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
//		Media audioFile = new Media(getClass().getResource("/media/music_victory.mp3").toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(audioFile);
//		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//		mediaPlayer.setVolume(1);
//		mediaPlayer.play();

		// animación del texto
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), startLabel);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setCycleCount(Animation.INDEFINITE);
		fadeTransition.play();

	}

	/**
	 * Devuelve la vista principal
	 * @return StackPane instancia del StackPane asociado
	 */
	public StackPane getView() {
		return view;
	}

	/**
	 * Al clicar sobre la escena cambia a la vista de la partida
	 * @param event
	 */
	@FXML
	void onMousePressed(MouseEvent event) {
		cambiarEscena();
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
	 * Cambia la escena de inicio por la de la partida.
	 * Además le asigna una imagen al cursor
	 */
	public void cambiarEscena() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayView.fxml"));

		loader.setController(playController);
		Scene scene = new Scene(playController.getView());
		Image image = new Image("/images/cursor.png");
		scene.setCursor(new ImageCursor(image));

		App.primaryStage.setScene(scene);
	}

}
