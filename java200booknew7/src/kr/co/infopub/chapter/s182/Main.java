package kr.co.infopub.chapter.s182;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane root;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Human Resouce Management System ver. 0.2");
		this.primaryStage=primaryStage;
        //---------------소스 분리 시킴
		showHR();
	}
	
	public void showHR() {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("MainHrFX.fxml"));
	        root = (BorderPane) loader.load();
	        //MainHrFXController empcon=loader.getController();
	        //------여기에 소스 추가
			
			
	        //------여기에 소스 추가
			Scene scene = new Scene(root,1200,850);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(e -> {
	        	System.out.println("Close primaryStage !!!");
	        });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
