package kr.co.infopub.chapter.s189;
import java.util.Date;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import kr.co.infopub.chapter.s189.util.PTS;
public class MainHrFXController {
 @FXML
 private MenuItem menuDepart;
 @FXML
 private MenuItem menuManage;
 @FXML
 private MenuItem menuSearch;
 @FXML
 private MenuItem menuUpdate;
 @FXML
 private MenuItem menuChart;
 @FXML
 private TabPane mainTabPane;
 @FXML
 private Tab tab3;
 @FXML
 private Tab tab1;
 @FXML
 private Tab tab2;
 @FXML
 private Tab tab4;
 @FXML
 private Tab tab5;
 @FXML
 private BorderPane searchTabBorder;
 @FXML
 private BorderPane empTabBorder;
 @FXML
 private BorderPane depChartBorder;
 @FXML
 private BorderPane debTabBorder;
 @FXML
 private BorderPane updateTabBorder;
 String systemver="HR Information Syste ver.0.2";
 // s182�� �߰�
 @FXML
 public void initialize () {
    tab1.setOnSelectionChanged(eee->{
	if(tab1.isSelected()){
		System.out.println("tab1------>"+((Tab)eee.getSource()).getId());
		debTabBorder.setCenter(departView);  
	}});
 }
 @FXML
 void onStartAction(ActionEvent event) {
  Alert alert = new Alert (Alert.AlertType.INFORMATION);
  alert.setTitle(systemver);
  alert.setHeaderText("�λ���� �ý��� "+PTS.toDate3(new Date()));
  alert.setContentText(
		  "�λ�����ý����� �μ�����, �����ڰ���, �λ翡 ���õ� �Է�/������ �ϴ� �ý����Դϴ�.");
  alert.show();
  mainTabPane.setVisible(true);    
 }
 @FXML
 void onExitAction(ActionEvent event) {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle(systemver);
    alert.setHeaderText(
    		   "�λ���� �ý���("+PTS.toDate3(new Date())+")�� �����ðڽ��ϱ�?");
	alert.setContentText("���� �����ðڽ��ϱ�?");
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){
		Platform.exit();
		//System.exit(0);
	} else return;
 }
 @FXML
 void onHelpAction(ActionEvent event) {
  Alert alert = new Alert (Alert.AlertType.INFORMATION);
  alert.setTitle(systemver);
  alert.setHeaderText("�λ���� �ý��� "+PTS.toDate3(new Date()));
  alert.setContentText("�ȳ��ϼ��� "+systemver+"�Դϴ�. "
	+ "\n�λ�����ý����� �μ�����, �����ڰ���, �λ翡 ���õ� �Է�/������ �ϴ� �ý����Դϴ�."
	+ "\n ������ ���۸޴��� �����Ͻʽÿ�.");
  alert.show();
 }
 // s182�� �߰�, �μ� �޴��������� �����ϸ� �μ�ȭ���� ���δ�.
 @FXML
 void onMenuction(ActionEvent event) {
	if(event.getSource()==menuDepart){
		System.out.println("tab1------------------------->");
		mainTabPane.getSelectionModel().select(tab1);
		debTabBorder.setCenter(departView);  
	}
 }
 // �������ο� �� �並 ���δ�.
 BorderPane departView;
 public void setView1(BorderPane departView) {
	this.departView=departView;
	//ù ȭ���� �ݿ��Ѵ�.
	debTabBorder.setCenter(departView);  
 }
}
