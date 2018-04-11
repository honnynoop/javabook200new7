package kr.co.infopub.chapter.s189;
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
	primaryStage.setTitle("Human Resouce Management System ver. 0.6");
	this.primaryStage=primaryStage;
    //---------------소스 분리 시킴
	showHR();
 }
 public void showHR() {
   try {
	FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("view/MainHrFX.fxml"));
    root = (BorderPane) loader.load();
    //------>>여기에 소스 추가
    // 기본 뼈대 화면 s182
    MainHrFXController empcon=loader.getController();
    // 부서화면 s185
    FXMLLoader loader4 = new FXMLLoader();
    loader4.setLocation(Main.class.getResource("view/DepTabView.fxml"));
    BorderPane departView = (BorderPane) loader4.load();
    // 기본 뼈대에 부서화면을 붙임
    empcon.setView1(departView);
    //<<------여기 사이에  소스 추가
	Scene scene = new Scene(root,1200,880);
	scene.getStylesheets().add(
	   Main.class.getResource("view/application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
	primaryStage.setResizable(false);
	// 화면 끝날때 close 표시
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
