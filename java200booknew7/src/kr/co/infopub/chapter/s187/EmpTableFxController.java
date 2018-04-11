package kr.co.infopub.chapter.s187;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import kr.co.infopub.chapter.s185.dto.EmpConvert;
import kr.co.infopub.chapter.s185.dto.Employee;
import kr.co.infopub.chapter.s185.dto.EmployeeDto;
import kr.co.infopub.chapter.s185.model.EmployeeDAO;
public class EmpTableFxController {
 @FXML
 private TableView<Employee> employeeTable;
 @FXML
 private TableColumn<Employee, Integer>  empIdColumn;
 @FXML
 private TableColumn<Employee, String>  empNameColumn;
 @FXML
 private TableColumn<Employee, String> empLastNameColumn;
 @FXML
 private TableColumn<Employee, String> empEmailColumn;
 @FXML
 private TableColumn<Employee, String> empPhoneNumberColumn;
 @FXML
 private TableColumn<Employee, Date> empHireDateColumn;
 @FXML
 private Button searchEmpsBtn;
 @FXML
 private Label lbhello;

 EmployeeDAO employeeDAO=new EmployeeDAO();
 
 void showLabel(String msg){
	lbhello.setText(msg);
	String value=
	    	   "-fx-font-size: 12px;           "
	    	   +"-fx-font-family: 'Arial Black';    "
	    	   +"-fx-fill: #818181;                 "
	    	   +"-fx-effect: innershadow( three-pass-box ,"
	    	   +" rgba(0,50,255,0.7) , 6, 0.0 , 0 , 2 );";
	    	lbhello.setStyle("\t"+value);
 }
 private void showToTableEmployees (ObservableList<Employee> empData)  {
    employeeTable.setItems(empData);
 }
 @FXML
 void initialize() {
	// 칼럼과 매핑, Callback -> call을 구현해야함
/*	empIdColumn.setCellValueFactory(
		      new Callback<TableColumn.CellDataFeatures<Employee,Integer>,
			                                 ObservableValue<Integer>>() {
		@Override
		public ObservableValue<Integer> call(
				           CellDataFeatures<Employee, Integer> cellData) {
			return cellData.getValue().employeeIdProperty().asObject();
		}
	});*/
	empIdColumn.setCellValueFactory(
	cellData -> cellData.getValue().employeeIdProperty().asObject());
	empNameColumn.setCellValueFactory(
			cellData -> cellData.getValue().firstNameProperty());
	empLastNameColumn.setCellValueFactory(
			cellData -> cellData.getValue().lastNameProperty());
	empEmailColumn.setCellValueFactory(
			cellData -> cellData.getValue().emailProperty());
	empPhoneNumberColumn.setCellValueFactory(
			cellData -> cellData.getValue().phoneNumberProperty());
	empHireDateColumn.setCellValueFactory(
			cellData -> cellData.getValue().hireDateProperty());
	// 데이블의 행을 선택하면 한 줄 이벤트가 발생한다.
	employeeTable.setOnMouseClicked(e ->{
	 if(employeeTable.getSelectionModel().getSelectedItem()!=null ){
	  Employee user =
		(Employee)employeeTable.getSelectionModel().getSelectedItem();
      showLabel(
        user.getEmployeeId()+" "+user.getFirstName()+" "+user.getLastName());
	 }
    });
 }
 @FXML
 private void searchEmployees(ActionEvent actionEvent) throws SQLException {
    try {
		// 모든 사원 가져오기
		List<EmployeeDto> emplists=employeeDAO.findAllEmployees();
		// JavaFX 테이블용 리스트로 변환
		ObservableList<Employee> 
		                  empData = EmpConvert.toObservProFromDto(emplists);
		// 테이블에 보여주기
		showToTableEmployees(empData);
        showLabel("Employees 총 :"+empData.size()+"명");
    } catch (SQLException e){
        System.out.println("employeeDAO.findAllEmployees() 에서 예외 발생.\n" + e);
    }
 }
}
