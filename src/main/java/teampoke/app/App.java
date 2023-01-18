package teampoke.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import teampoke.controller.RootController;

public class App extends Application {

	RootController rootController = new RootController();
	
	public static Stage primaryStage; 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;
		
		primaryStage.setTitle("Pokedle");
		primaryStage.setScene(new Scene(rootController.getView()));
		primaryStage.show();
		
	}

}
