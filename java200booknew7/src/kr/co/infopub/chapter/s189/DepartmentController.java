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
 // Ʈ���信 �̹��� - ��ħ
 private final Node rootIcon0 = new ImageView(
    new Image(getClass().getResourceAsStream("image/book0.png"))
 );
 // Ʈ���信 �̹��� - ����
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
    TreeItem<String> root = new TreeItem<String>("�μ��� ����",rootIcon0);
    root.setExpanded(true);
	try {
		List<DepartmentDto> blist=employeeDAO.findAllDepartments();
		dlists=DepConvert.toObservProFromDto(blist);
	    makeDepTree(root,dlists);
	    tvEmp.setRoot(root);
	    // Ʈ������ �������� �����ϸ� �μ��� �ִ� �μ����� ������ ���̺�信 ���δ�.
	    tvEmp.getSelectionModel().selectedItemProperty()
	    .addListener((observable, oldValue, newValue) -> {
	        ObservableList<Employee> empData=FXCollections.emptyObservableList();
		  try {
			 String val="";
			 if(newValue !=null && newValue instanceof TreeItem<?>){
				 // Ʈ���信�� ���õ� Ʈ���������� �μ��̸��� �����´�.
				 val=EmpUtil.dep(((TreeItem<String>)newValue).getValue());
			 }
			 System.out.println("-----------------------------"+val);
			 // �μ��̸����� �μ��� �ٹ��ϴ� �μ������� ����Ʈ�� �����´�.
			 List<EmployeeDto> emplists=employeeDAO.findEmployeesByDepartName( val);
			 if(!(emplists==null || emplists.size()==0)){
				 empData = EmpConvert.toObservProFromDto(emplists);
			 }
			 String stsf=val+" �μ�������: ";
			 showLabel(stsf+empData.size()+" ��");
			 // ���̺�信 �ش� �μ��� �μ������� ������  ���δ�.
			 showToTableEmployees(empData);
		  } catch (SQLException e) {	
		  }
	    });
	    // Ʈ������ Ʈ���������� ��ģ��.
	    root.addEventHandler(TreeItem.branchExpandedEvent(),eh->{
	    	System.out.println("expanded");
	    	root.setGraphic(rootIcon0);
	        loadTreeItems();  // �������� �����ۿ� �ش��ϴ� �ڽ� �����۵��� ���δ�.
	    });
	    // Ʈ������ Ʈ���������� �ݴ´�.
	    root.addEventHandler(TreeItem.branchCollapsedEvent(),eh->{
	    	System.out.println("collapsed");
	    	root.setGraphic(rootIcon1);
	    });
	} catch (SQLException e) {
		System.out.println(" emp tv :"+e);
	}
}
 // ��� ����� ������ �����ͼ� �߾� ���̺�信 ���δ�. 
 @FXML
 private void searchEmployees(ActionEvent actionEvent) throws SQLException {
    try {
    	List<EmployeeDto> blist  = employeeDAO.findAllEmployees ();
    	ObservableList<Employee> empData=EmpConvert.toObservProFromDto(blist);
    	showToTableEmployees(empData);
        showLabel("Employees �� :"+empData.size()+"��");
    } catch (SQLException e){
        System.out.println("employeeDAO.findAllEmployees() ���� ���� �߻�.\n" + e);
        //throw e;
    }
 }
 // ���̺�信 ���� ������ ������������ ����� ���δ�.
 @FXML
 private void showToTableEmployees (ObservableList<Employee> empData)  {
    employeeTable.setItems(empData);
 }
}
