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

public class App extends Application {

	RootController rootController = new RootController();
	
	public static Stage primaryStage; 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		App.primaryStage = primaryStage;
		Scene scene = new Scene(rootController.getView());
		
		
		primaryStage.setTitle("Pokédle");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/images/pokedle_icon_32px.png"));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		Image image = new Image("/images/cursor.png"); 
		scene.setCursor(new ImageCursor(image));
		primaryStage.show();
		
		
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().toString() == "ENTER") {
					rootController.onEnterPressed();
				}
				
			}
			
		});
		
	}

}
