package teampoke.controller;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonBar;
//import javafx.scene.control.Label;
//import javafx.scene.control.Slider;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.stage.Stage;
//
public class VolumenController implements Initializable {
//
//	@FXML
//	private Button closeButton;
//
//	@FXML
//	private Slider sliderVolumen;
//
//	@FXML
//	private ButtonBar toolBar;
//	@FXML
//	private Label labelVolumen;
//
//	private double volumenActual = 0.5;
//	
//
//	public VolumenController() {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VolumenView.fxml"));
//		loader.setController(this);
//		try {
//			loader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@FXML
//	void onClose(MouseEvent event) {
//		Stage stage = (Stage) closeButton.getScene().getWindow();
//		stage.close();
//	}
//	
//	// Obtener el MediaPlayer actual.
//			RootController rootController = new RootController();
//			MediaPlayer mediaPlayer = rootController.getMediaPlayer();
//
//
//	@FXML
//	void silenciar(ActionEvent event) {
//		
//		// Silenciar o restaurar el volumen.
//		if (mediaPlayer.isMute()) {
//			mediaPlayer.setMute(false);
//			sliderVolumen.setValue(volumenActual); // Restaurar el valor del volumen actual.
//			actualizarEtiquetaVolumen(); // Actualizar la etiqueta "Volumen:".
//		} else {
//			volumenActual = sliderVolumen.getValue();
//			mediaPlayer.setMute(true);
//			sliderVolumen.setValue(0.0); // Establecer el valor del volumen en 0.
//			labelVolumen.setText("Volumen: MUTE"); // Actualizar la etiqueta "Volumen:".
//		}
//	}
//
//	@FXML
//	void subirVolumen(ActionEvent event) {
//		volumenActual = Math.min(1.0, volumenActual + 0.1);
//		sliderVolumen.setValue(volumenActual);
//		actualizarEtiquetaVolumen();
//        mediaPlayer.setVolume(volumenActual);
//
//	}
//
//	@FXML
//	void bajarVolumen(ActionEvent event) {
//		volumenActual = Math.max(0.0, volumenActual - 0.1);
//		sliderVolumen.setValue(volumenActual);
//		actualizarEtiquetaVolumen();
//        mediaPlayer.setVolume(volumenActual);
//	}
//
//	private void actualizarEtiquetaVolumen() {
//		labelVolumen.setText(String.format("Volumen: %.2f", volumenActual));
//	}
//
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
//		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//		mediaPlayer.play();

	}
//
}
