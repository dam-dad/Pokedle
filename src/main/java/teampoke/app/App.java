package teampoke.app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import teampoke.controller.RootController;
/**
 * 
 * Clase que se encarga de la configuración inical
 *
 */
public class App extends Application {

	RootController rootController = new RootController();
	
	public static Stage primaryStage; 
	public static Scene scene;
	
	/**
	 * Método que realiza la ejecución principal de la aplicación
	 * @throws IOException si se produce un error al cargar la vista
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		App.primaryStage = primaryStage;
		App.scene = new Scene(rootController.getView());

		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/images/pokedle_icon_32px.png"));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		Image image = new Image("/images/cursor.png"); 
		scene.setCursor(new ImageCursor(image));
		primaryStage.show();
		
		
	}

}
