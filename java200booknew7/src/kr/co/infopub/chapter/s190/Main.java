package kr.co.infopub.chapter.s190;	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
public class Main extends Application {
 private Stage primaryStage;
 private BorderPane root;
 String systemver="HR Information System ver.1.0";
 @Override
 public void start(Stage primaryStage) {	
	primaryStage.setTitle(systemver);
	this.primaryStage=primaryStage;
    //---------------�ҽ� �и� ��Ŵ
	showHR();
 }
 public void showHR() {
	try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainHrFX.fxml"));
        root = (BorderPane) loader.load();
        MainHrFXController empcon=loader.getController();
        //------���⿡ �ҽ� �߰�
        FXMLLoader loader4 = new FXMLLoader();
        loader4.setLocation(Main.class.getResource("view/DepTabView.fxml"));
        BorderPane departView = (BorderPane) loader4.load();
		
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(Main.class.getResource("view/ManagerFx.fxml"));
        BorderPane emptvView = (BorderPane) loader2.load();
        
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("view/EmployeeSearchFx.fxml"));
        BorderPane empsearchView = (BorderPane) loader1.load();
        
        FXMLLoader loader3 = new FXMLLoader();
        loader3.setLocation(Main.class.getResource("view/EmployUpdateFx.fxml"));
        BorderPane empupdateView = (BorderPane) loader3.load();
        
        FXMLLoader loader5 = new FXMLLoader();
        loader5.setLocation(Main.class.getResource("view/DepChartView.fxml"));
        
        empcon.setView1(departView);
        empcon.setView2(emptvView);
        empcon.setView(empsearchView);
        empcon.setView3(empupdateView);
        empcon.setView4(loader5);
        //------���⿡ �ҽ� �߰�
		Scene scene = new Scene(root,1250,880);
		scene.getStylesheets().add(Main.class.getResource("view/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
        	System.out.println("Close primaryStage !!!");
        });
	} catch(Exception e) {
		System.out.println(" start���� Error : "+e);
	}
 }
 public static void main(String[] args) {
	launch(args);
 }
}
