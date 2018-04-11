package kr.co.infopub.chapter.s182;
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
 @FXML
 void onStartAction(ActionEvent event) {
	  Alert alert = new Alert (Alert.AlertType.INFORMATION);
      alert.setTitle(systemver);
      alert.setHeaderText("인사관리 시스템 "+PTS.toDate3(new Date()));
      alert.setContentText(
    		  "인사관리시스템은 부서관리, 관리자관리, 인사에 관련된 입력/수정을 하는 시스템입니다.");
      alert.show();
      mainTabPane.setVisible(true);    
 }
 @FXML
 void onExitAction(ActionEvent event) {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle(systemver);
    alert.setHeaderText("인사관리 시스템("+PTS.toDate3(new Date())+")을 끝내시겠습니까?");
	alert.setContentText("정말 끝내시겠습니까?");
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
      alert.setHeaderText("인사관리 시스템 "+PTS.toDate3(new Date()));
      alert.setContentText("안녕하세요 "+systemver+"입니다. "
       + "\n인사관리시스템은 부서관리, 관리자관리, 인사에 관련된 입력/수정을 하는 시스템입니다."
       + "\n 시작은 시작메뉴를 선택하십시오.");
      alert.show();
 }
 @FXML
 void onMenuction(ActionEvent event) {
	// 메뉴를 선택하면 해당 탭을 보입니다.
 }
}
