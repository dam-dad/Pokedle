package teampoke.app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import teampoke.controller.RootController;

public class App extends Application {

	RootController rootController = new RootController();
	
	public static Stage primaryStage; 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;
		Scene scene = new Scene(rootController.getView());
		
		primaryStage.setTitle("Pokedle");
		primaryStage.setScene(scene);
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
