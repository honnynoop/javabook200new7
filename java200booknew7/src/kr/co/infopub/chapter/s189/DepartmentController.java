package kr.co.infopub.chapter.s189;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import kr.co.infopub.chapter.s189.dto.DepConvert;
import kr.co.infopub.chapter.s189.dto.Department;
import kr.co.infopub.chapter.s189.dto.DepartmentDto;
import kr.co.infopub.chapter.s189.dto.EmpConvert;
import kr.co.infopub.chapter.s189.dto.Employee;
import kr.co.infopub.chapter.s189.dto.EmployeeDto;
import kr.co.infopub.chapter.s189.model.EmployeeDAO;
import kr.co.infopub.chapter.s189.util.EmpUtil;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
public class DepartmentController {
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
 @FXML
 private TreeView tvEmp;
 @FXML
 private BorderPane SearchTabBorder;
 @FXML
 private BorderPane emptvBorder;
 @FXML
 private BorderPane UpdateTabBorder;
 EmployeeDAO employeeDAO=new EmployeeDAO();  
 // 트리뷰에 이미지 - 펼침
 private final Node rootIcon0 = new ImageView(
    new Image(getClass().getResourceAsStream("image/book0.png"))
 );
 // 트리뷰에 이미지 - 닫힘
 private final Node rootIcon1 = new ImageView(
	new Image(getClass().getResourceAsStream("image/book1.png"))
 );
 @FXML
 public void initialize () {
	loadTreeItems();
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
	employeeTable.setOnMouseClicked(e ->{
	  if(employeeTable.getSelectionModel().getSelectedItem()!=null ){
		Employee user = 
				(Employee)employeeTable.getSelectionModel().getSelectedItem();
		showLabel(
		   user.getEmployeeId()+"  "+user.getFirstName()+" "+user.getLastName());
	  }
	});
 }
 void showLabel(String msg){
	lbhello.setText(msg);
	//lbhello.setStyle(null);
	String value=
	   "-fx-font-size: 12px;           "
	   +"-fx-font-family: 'Arial Black';    "
	   +"-fx-fill: #818181;                 "
	   +"-fx-effect: innershadow( three-pass-box ,"
	   +" rgba(0,50,255,0.7) , 6, 0.0 , 0 , 2 );";
	lbhello.setStyle("\t"+value);
 }
 void showViewError(String msg){
	lbhello.setText(msg);
	String value=
	   "-fx-font-size: 12px;           "
	   +"-fx-font-family: 'Arial Black';    "
	   +"-fx-fill: #818181;                 "
	   +"-fx-effect: innershadow( three-pass-box ,"
	   +" rgba(255,0,0,0.7) , 6, 0.0 , 0 , 2 );";
	lbhello.setStyle("\t"+value);
 }
 public  void makeDepTree(TreeItem<String> front,List<Department> dlists){
	for (Department dep: dlists) {
          TreeItem<String> troots=new TreeItem<String>(EmpUtil.tname(dep));
          front.getChildren().add(troots);
	}
 }
 @SuppressWarnings("unchecked")
 public void loadTreeItems() {
    ObservableList<Department> dlists=null;
    TreeItem<String> root = new TreeItem<String>("부서별 직원",rootIcon0);
    root.setExpanded(true);
	try {
		List<DepartmentDto> blist=employeeDAO.findAllDepartments();
		dlists=DepConvert.toObservProFromDto(blist);
	    makeDepTree(root,dlists);
	    tvEmp.setRoot(root);
	    // 트리뷰의 아이템을 선택하면 부서에 있는 부서원을 정보를 테이블뷰에 보인다.
	    tvEmp.getSelectionModel().selectedItemProperty()
	    .addListener((observable, oldValue, newValue) -> {
	        ObservableList<Employee> empData=FXCollections.emptyObservableList();
		  try {
			 String val="";
			 if(newValue !=null && newValue instanceof TreeItem<?>){
				 // 트리뷰에서 선택된 트리아이템의 부서이름을 가져온다.
				 val=EmpUtil.dep(((TreeItem<String>)newValue).getValue());
			 }
			 System.out.println("-----------------------------"+val);
			 // 부서이름으로 부서에 근무하는 부서원들을 리스트로 가져온다.
			 List<EmployeeDto> emplists=employeeDAO.findEmployeesByDepartName( val);
			 if(!(emplists==null || emplists.size()==0)){
				 empData = EmpConvert.toObservProFromDto(emplists);
			 }
			 String stsf=val+" 부서직원수: ";
			 showLabel(stsf+empData.size()+" 명");
			 // 테이블뷰에 해당 부서의 부서원들의 정보를  보인다.
			 showToTableEmployees(empData);
		  } catch (SQLException e) {	
		  }
	    });
	    // 트리뷰의 트리아이템을 펼친다.
	    root.addEventHandler(TreeItem.branchExpandedEvent(),eh->{
	    	System.out.println("expanded");
	    	root.setGraphic(rootIcon0);
	        loadTreeItems();  // 펼쳐질때 아이템에 해당하는 자식 아이템들을 보인다.
	    });
	    // 트리뷰의 트리아이템을 닫는다.
	    root.addEventHandler(TreeItem.branchCollapsedEvent(),eh->{
	    	System.out.println("collapsed");
	    	root.setGraphic(rootIcon1);
	    });
	} catch (SQLException e) {
		System.out.println(" emp tv :"+e);
	}
}
 // 모든 사원의 정보를 가져와서 중앙 테이블뷰에 붙인다. 
 @FXML
 private void searchEmployees(ActionEvent actionEvent) throws SQLException {
    try {
    	List<EmployeeDto> blist  = employeeDAO.findAllEmployees ();
    	ObservableList<Employee> empData=EmpConvert.toObservProFromDto(blist);
    	showToTableEmployees(empData);
        showLabel("Employees 총 :"+empData.size()+"명");
    } catch (SQLException e){
        System.out.println("employeeDAO.findAllEmployees() 에서 예외 발생.\n" + e);
        //throw e;
    }
 }
 // 테이블뷰에 고객의 정보를 아이템즈으로 만들어 붙인다.
 @FXML
 private void showToTableEmployees (ObservableList<Employee> empData)  {
    employeeTable.setItems(empData);
 }
}
