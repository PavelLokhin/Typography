package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

//import javafx.scene.control.Button;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private Stage primaryStage;
	//Button button = (Button) new Button().lookup("#dirChooseBtn");
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		initRootLayout();
		
	}
	
	public void initRootLayout(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("RootLayout.fxml"));
			//loader.setLocation(("RootLayout.fxml"));
			BorderPane rootLayout = new BorderPane(loader.load());
			Scene scene = new Scene(rootLayout,700,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
