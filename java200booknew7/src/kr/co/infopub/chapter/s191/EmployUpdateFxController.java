package kr.co.infopub.chapter.s191;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import kr.co.infopub.chapter.s191.dto.Department;
import kr.co.infopub.chapter.s191.dto.EmpConvert;
import kr.co.infopub.chapter.s191.dto.Employee;
import kr.co.infopub.chapter.s191.dto.EmployeeDto;
import kr.co.infopub.chapter.s191.model.EmployeeService;
import kr.co.infopub.chapter.s191.util.PTS;

public class EmployUpdateFxController extends BorderPane{
	    @FXML
	    private TextField tfphone;

	    @FXML
	    private TextField tfdepartid;

	   
	    @FXML
	    private AnchorPane bottomsplit;

	    @FXML
	    private Label lbhiredate;

	    @FXML
	    private TextField tffirstname;

	    @FXML
	    private Label lbjobid;

	    @FXML
	    private TextField tflastname;

	    @FXML
	    private TextField tfemail;

	    @FXML
	    private TextField tfjobid;

	    @FXML
	    private Label lbcommis;

	    @FXML
	    private Label lbmanagerid;

	    @FXML
	    private Label lbphone;



	    @FXML
	    private TextField tfempid;

	    @FXML
	    private AnchorPane topslplit;

	    @FXML
	    private Label lbfirstname;

	    @FXML
	    private SplitPane leftsplit;

	    @FXML
	    private Label lbdepartid;

	    @FXML
	    private Label lbsalary;

	    @FXML
	    private Label lblastname;

	    @FXML
	    private TextField tfcommis;

	    @FXML
	    private DatePicker tfhiredate;

	    @FXML
	    private TextField tfmanagerid;

	    @FXML
	    private Label lbempid;

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
	    private TextField tfsalary;

	    @FXML
	    private Button btnAllEmployee;

	    @FXML
	    private Label lbemail;
	    
	    @FXML
	    private Button btnAddEmployee;
	     
	    //����Ϸ� ���̱�
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy. MM. dd");
	    
	    @FXML
	    private Label lbjobid2;
	    @FXML
	    private Label lbdepartid2;
	    
	    @FXML
	    private TextField tfupdatemanager;
	    
	    @FXML
	    private ComboBox<String> cbJobid;

	    @FXML
	    private ComboBox<String> cbdepartid;
	    
	    
	    @FXML
	    private Button btnAddAfEmployee;
	    
	    @FXML
	    private TableView<Employee> tvSearchManager;
	    @FXML
	    private TableColumn<Employee, Integer>  empIdColumn3;
	    @FXML
	    private TableColumn<Employee, String>  empNameColumn3;
	    @FXML
	    private TableColumn<Employee, String> empLastNameColumn3;
	    @FXML
	    private TableColumn<Employee, String> empEmailColumn3;
	    
	    @FXML
	    private Button btnSearchManager  ;
	    
	    @FXML
	    private TextField tfsearchmanager  ;
	    
	    @FXML
	    private Button btndelEmployee  ;
	    
	    EmployeeService service;
	    
		public EmployUpdateFxController(EmployeeService service) {
			this.service=service;
			
			FXMLLoader loader4 = new FXMLLoader();
	        loader4.setLocation(Main.class.getResource("view/EmployUpdateFx.fxml"));
	        loader4.setRoot(this);
	        loader4.setController(this);
	        try {
	        	loader4.load();
	        } catch (Exception exception) {
	            throw new RuntimeException(exception);
	        }
		}
		private void clear(){
//	    	tfempid.setText("");
//			tffirstname.setText("");
//			tflastname.setText("");
//			tfemail.setText("");
//			tfhiredate.getEditor().setText("");
//			tfphone.setText("");
//			tfcommis.setText("");
//			tfsalary.setText("");
//			tfdepartid.setText("");
//			tfjobid.setText("");
//			tfmanagerid.setText("");
//			tfsearchmanager.setText("");
	    }
	    private void edit(boolean b){
	    	tfempid.setEditable(false);
			tffirstname.setEditable(b);
			tflastname.setEditable(b);
			tfemail.setEditable(b);
			tfhiredate.setEditable(b);
			tfphone.setEditable(b);
			tfcommis.setEditable(b);
			tfsalary.setEditable(b);
			tfdepartid.setEditable(b);
			tfjobid.setEditable(b);
			tfmanagerid.setEditable(b);
			tfsearchmanager.setEditable(b);
	    }
	    private void edit2(boolean b){
	    	
	    	//tfempid.setEditable(b);
			tffirstname.setEditable(b);
			tflastname.setEditable(b);
			tfemail.setEditable(b);
			//tfhiredate.setEditable(b);
			tfphone.setEditable(b);
			tfcommis.setEditable(b);
			tfsalary.setEditable(b);
			
			tfsearchmanager.setEditable(b);
			//tfdepartid.setEditable(b);
			//tfjobid.setEditable(b);
			//tfmanagerid.setEditable(b);
			if(b){
				tffirstname.setStyle("-fx-background-color: #0000ff");
				tflastname.setStyle("-fx-background-color: #0000ff");
				tfemail.setStyle("-fx-background-color: #0000ff");
				tfhiredate.setStyle("-fx-background-color: #0000ff");
				tfphone.setStyle("-fx-background-color: #0000ff");
				tfcommis.setStyle("-fx-background-color: #cccccc");
				tfsalary.setStyle("-fx-background-color: #cccccc");
				tfdepartid.setStyle("-fx-background-color: #cccccc");
				tfjobid.setStyle("-fx-background-color: #cccccc");
				tfmanagerid.setStyle("-fx-background-color: #cccccc");
				tfsearchmanager.setStyle("-fx-background-color: #cccccc");
			}else{
				tffirstname.setStyle("-fx-background-color: #ffffff");
				tflastname.setStyle("-fx-background-color: #ffffff");
				tfemail.setStyle("-fx-background-color: #ffffff");
				tfhiredate.setStyle("-fx-background-color: #ffffff");
				tfphone.setStyle("-fx-background-color: #ffffff");
				tfcommis.setStyle("-fx-background-color: #ffffff");
				tfsalary.setStyle("-fx-background-color: #ffffff");
				tfdepartid.setStyle("-fx-background-color: #ffffff");
				tfjobid.setStyle("-fx-background-color: #ffffff");
				tfmanagerid.setStyle("-fx-background-color: #ffffff");
				tfsearchmanager.setStyle("-fx-background-color: #ffffff");
			}
			
	    }

	    @FXML
	    void initialize() {
	         empIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
	         empNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	         empLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	         empEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
	         empPhoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
	         empHireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());

	         empIdColumn3.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
	         empNameColumn3.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	         empLastNameColumn3.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	         empEmailColumn3.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
	         
	         
	         //tfhiredate.setPromptText("������ �����ϼ���.");
	         //tfhiredate.setMaxWidth(381.0);
	         //tfhiredate.setStyle("-fx-background-color: #0000ff");
	         //
	         employeeTable.setOnMouseClicked(e ->{
	         	if(employeeTable.getSelectionModel().getSelectedItem()!=null ){
	         		 edit(false);
	         		 edit2(true);
			    	 clear();
			    	 //-----
			    	 btnAddAfEmployee.setVisible(true);
			    	 btndelEmployee.setVisible(true);
			    	 
	         		Employee user = (Employee)employeeTable.getSelectionModel().getSelectedItem();
	         		try {
	         			//DB���� ã�Ƽ�
						Employee emp=service.findEmployeeById(user.getEmployeeId()+"");
						//���� ������ ���δ�.
						tfempid.setText(emp.getEmployeeId()+"");
						tffirstname.setText(emp.getFirstName());
						tflastname.setText(emp.getLastName());
						tfemail.setText(emp.getEmail());
						//�޷����� �ٲ�
						tfhiredate.getEditor().setText(sdf.format((java.util.Date)(emp.getHireDate())));
						tfphone.setText(emp.getPhoneNumber());
						tfcommis.setText(emp.getCommissionPct()+"");
						tfsalary.setText(emp.getSalary()+"");
						tfdepartid.setText(emp.getDepartmanId()+"");
						tfjobid.setText(emp.getJobId()+"");
						tfmanagerid.setText(emp.getManagerId()+"");
						System.out.println("------->"+emp.getDepartmanId()+"");
					} catch (SQLException e1) {
						System.out.println("DB���� �������� �������� ���� �߻��߽��ϴ�.");
					}
	             	//System.out.println(user.getEmployeeId()+"  "+user.getFirstName()+" "+user.getLastName());
	             	//lbhello.setText(user.getEmployeeId()+"  "+user.getFirstName()+" "+user.getLastName());
	         	}
	           }
	         );
	         btnAddAfEmployee.setVisible(false);
	         btndelEmployee.setVisible(false);
	         tvSearchManager.setOnMouseClicked(e ->{
		         	if(tvSearchManager.getSelectionModel().getSelectedItem()!=null ){
		         		Employee user = (Employee)tvSearchManager.getSelectionModel().getSelectedItem();
		         		tfmanagerid.setText(user.getFirstName()+" "+user.getLastName()+"("+user.getEmployeeId()+")");
		         	}
		           }
		         );
	    }
	    
	    @FXML
	    void actionAllEmployee(ActionEvent event) {
	    	 try {
	    		 
	    		 String updatemanager=tfupdatemanager.getText();
	 	    	 ObservableList<Employee> updatemanagerList= service.findManagersByName(updatemanager);
	 	    	 employeeTable.setItems(updatemanagerList);
	 	    	 tfupdatemanager.setText("");
	    		 		    	 
	         } catch (SQLException e){
	             System.out.println("Error occurred " + e);
	         }
	    	 btnAddAfEmployee.setVisible(false);
	    	 btndelEmployee.setVisible(false);
	    	 
	    }
	    @FXML
	    private void showEmpoyeeTable (ObservableList<Employee> empData)  {
	    	employeeTable.setItems(empData);
	    }
	    //��ư�� Ŭ���Ͽ�  �����߰� �غ� 
	    @FXML
	    void actionReadyAddEmployee(ActionEvent event) {
	    	clear();
	    	edit(false);
	    	edit2(true);
	    	cbJobid.setItems(null);
	    	cbdepartid.setItems(null);
	    	leftsplit.setDividerPositions(0.6);
	    	employeeTable.setItems(null);
	    	
	    	 try {
	    		    //��� ���� ������ ���޺��� �ֱ�----------------
			   		ObservableList<String> jobs=service.findAllJobs();
			        shoeJobsTable(jobs);
			        //-----------------------------------
			        //��� �μ��� ������ �޺��� �ֱ�-----------------
			        ObservableList<String> dpnames = FXCollections.observableArrayList();
			        ObservableList<Department> dlists =service.findAllDepartments2 ();
			        for (Department dd:dlists) {
			        	dpnames.add(String.format("%s(%d)", dd.getDepartment_name(),dd.getDepartment_id()));
					}
	    			cbdepartid.setItems(dpnames);
	    	        //--------------------------------------
		        } catch (SQLException e){
		            System.out.println("Error occurred actionReadyAddEmployee " + e);
		        }
	    	 btnAddAfEmployee.setVisible(true);
	    	 btndelEmployee.setVisible(true);
	    }
        //���ߺ��� �����ϸ� ����̵� tf�� �ֱ�
	    @FXML
	    void actionJobClicked(ActionEvent event) {
	    	if(cbJobid.getSelectionModel().getSelectedItem()!=null){
	    		String coms =  cbJobid.getSelectionModel().getSelectedItem().toString();    
		    	 tfjobid.setText(coms);
		    	 System.out.println("����̵��� ����==========>"+coms);
	    	}
	    }
	    //depart(59) ==> 59
	    private String depid(String msg){
	    	String ss="";
	    	if(msg.indexOf("(")!=-1 && msg.indexOf(")")!=-1 ){
	    		ss=msg.substring(msg.indexOf("(")+1);
	    		ss=ss.substring(0,ss.indexOf(")"));
	    	}else{
	    		ss=msg.trim();
	    	}
	    	return ss.trim();
	    }
	  //depart(59) ==> depart
	    private String dep(String msg){
	    	String ss="";
	    	if(msg.indexOf("(")!=-1){
	    		ss=msg.substring(0, msg.indexOf("("));
	    	}else{
	    		ss=msg.trim();
	    	}
	    	return ss.trim();
	    }
	    //�μ��޺����� �μ������� ������ ã��
	    @FXML
	    void actionDepartClicked(ActionEvent event) {
	    	if(cbdepartid.getSelectionModel().getSelectedItem()!=null){
	    		String departid =  cbdepartid.getSelectionModel().getSelectedItem().toString();    
	    		
		    	 tfdepartid.setText(departid);//���⸦ ������ ��.
		    	 System.out.println("�μ���(�μ����̵�)================>"+departid);
		    	 
		    	 try {
		    		 ObservableList<String> dpnames = FXCollections.observableArrayList();
		    		//�μ����� �̿��Ͽ� ������������ ã��-------------
					 ObservableList<Employee> employeess= service.findEmployeesByDepartName(dep(departid));//depart(59) ==> depart
			        for (Employee dd:employeess) {
			        	dpnames.add(String.format("%s %s[%s](%d)", dd.getFirstName(),dd.getLastName(), dd.getEmail(),dd.getEmployeeId()));
					}
			        //������ �޺��� ������ ���̵� �ֱ�
	    			//cbmanager.setItems(dpnames);
	    			//-------------------------------------
				} catch (SQLException e) {
					System.out.println("actionDepartClicked : "+e);
				}
	    	}
	    }
	    
	    void shoeJobsTable(ObservableList<String>jobs){
	    	if(cbJobid!=null){
	    		cbJobid.setItems(jobs);
	    	}
	    }
	    //������ �޺��� �����ϸ� ������ ������ tf�� �ֱ�
//	    @FXML
//	    void actionManagerClicked(ActionEvent event) {
//	    	if(cbmanager.getSelectionModel().getSelectedItem()!=null){
//	    		String manager =  cbmanager.getSelectionModel().getSelectedItem().toString();    
//		    	 tfmanagerid.setText(manager);
//		    	 System.out.println("========================>"+manager);
//	    	}
//	    }
	    
	    public void handleHelp() {
	        Alert alert = new Alert (Alert.AlertType.INFORMATION);
	        alert.setTitle("�ʼ���Ҹ� �� �Է��Ͻʽÿ�.");
	        alert.setHeaderText("�ʼ����Ȯ��");
	        alert.setContentText("�ʼ���Ҹ� �� �Է��ϼ���. �ʼ���Ҵ� �Ķ����Դϴ�..");
	        alert.show();
	    }
	    
	    
	    //emp ����
	    @FXML
	    void actionUpdateEmployee(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation Dialog");
	    	alert.setHeaderText("������ ������ �����Ͻðڽ��ϱ�?");
	    	alert.setContentText("���� �����Ͻðڽ��ϱ�?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() != ButtonType.OK){
	    	   return;  //�۾� ����
	    	} 
	    	String empId=tfempid.getText();
	    	String empfn=tffirstname.getText();
	    	String empln=tflastname.getText();
	    	String empmail=tfemail.getText();
	    	String emphire=tfhiredate.getEditor().getText();
	    	//sqldate�� ��ȯ�غ�
	    	String empphone=tfphone.getText();
	    	//-----------------------------------------------
	    	String emppct=tfcommis.getText();
	    	String empsal=tfsalary.getText();
	    	String empdepid=tfdepartid.getText(); //�����ʿ�
	    	String empjobid=tfjobid.getText();   //�����ʿ� ->jobid(10)
	    	String empmanid=tfmanagerid.getText();//�����ʿ� ->
	    	System.out.println(String.format("%s,%s,%s,%s,%s", empfn,empln,empmail,emphire,empphone));
	    	System.out.println(String.format("%s,%s,%s,%s,%s", emppct,empsal,depid(empdepid),empjobid,depid(empmanid)));
	    	
	    	if(empId.equals("") || empfn.equals("") || empln.equals("")|| empmail.equals("")||
	    			emphire.equals("")|| empjobid.equals("")|| empmanid.equals("")){
	    		handleHelp();
	    		return;
	    	}

	    	Date dhiredate= PTS.toDaeS(emphire);
	    	
	    	System.out.println("emphire-------------------"+emphire);
	    	System.out.println("dhiredate-------------------"+dhiredate);
	    	Employee emp=new Employee();
	    	if(!empId.equals("")){
	    		emp.setEmployeeId(Integer.parseInt(empId));
	    	}
	    	emp.setFirstName(empfn);
	    	emp.setLastName(empln);
	    	emp.setEmail(empmail);
	    	emp.setHireDate(dhiredate);
	    	emp.setPhoneNumber(empphone);
	    	if(!emppct.equals("")){
	    		emp.setCommissionPct(Double.parseDouble(emppct));
	    	}
	    	if(!empsal.equals("")){
	    		emp.setSalary(Double.parseDouble(empsal));
	    	}
	    	if(!empdepid.equals("")){
	    		emp.setDepartmantId(Integer.parseInt(depid(empdepid)));
	    	}
	    	emp.setJobId(empjobid);
	    	if(!empmanid.equals("")){
	    		emp.setManagerId(Integer.parseInt(depid(empmanid)));
	    	}
	    	try {
	    		System.out.println(" getFirstName------------------------------"+emp.getFirstName());
	    		System.out.println(" getLastName------------------------------"+emp.getLastName());
				boolean isS=service.updateEmployee(emp);
				if(isS){
					 System.out.println(" updateEmployee ����-------------------------------"+empId);
					 show("Update "+emp.getEmployeeId(),"���������� "+emp.getEmployeeId()+"�� �����Ͽ����ϴ�.");
		             ObservableList<Employee> empData=FXCollections.observableArrayList();
		             Employee empDat = service.findEmployeeById(""+empId);  
		             System.out.println(" actionUpdateEmployee ��ϵ� empã��-------------------------------"+empId);
		             if(empDat!=null){
		            	 empData.add(empDat);
			             showEmpoyeeTable(empData);
		             }
		 	    	 edit(false);
		 	    	 edit2(false);
			    	 clear();
				}else{
					 System.out.println(" updateEmployee ����------------------------------"+empId);
				}
			} catch (SQLException e) {
				System.out.println("�����߻�:  actionUpdateEmployee======>"+e);
			}
	    	 btnAddAfEmployee.setVisible(false);
	    	 btndelEmployee.setVisible(false);
	    }
		
	    @FXML
	    void actionDeleteEmployee(ActionEvent event){

	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation Dialog");
	    	alert.setHeaderText("������ ������ �����Ͻðڽ��ϱ�?");
	    	alert.setContentText("���� �����Ͻðڽ��ϱ�?");
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() != ButtonType.OK){
	    	   return;
	    	} 
	    	
	    	
	    	String empId=tfempid.getText();
	    	if(empId.equals("")){
	    		handleHelp();
	    		return;
	    	}
	    
	    	Employee emp=new Employee();
	    	if(!empId.equals("")){
	    		emp.setEmployeeId(Integer.parseInt(empId));
	    	}
	    	try {
				boolean isS=service.deleteEmployee(emp);
				if(isS){
					 System.out.println(" deleteEmployee ����-------------------------------"+empId);
					 show("Delete "+emp.getEmployeeId(),"���������� "+emp.getEmployeeId()+"�� �����Ͽ����ϴ�.");
		 	    	 edit(false);
		 	    	 edit2(false);
			    	 clear();
				}else{
					 System.out.println(" deleteEmployee ����------------------------------"+empId);
				}
			} catch (SQLException e) {
				System.out.println(" deleteEmployee==>"+e);
			}
	    	 btndelEmployee.setVisible(false);
	    }
	    public void show(String header, String cont){
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information Dialog");
	    	alert.setHeaderText(header);
	    	alert.setContentText(cont);
	    	alert.showAndWait();
	    }
	    @FXML
	    void actionSearchManger(ActionEvent event) {
	    	
	    	String manid=tfsearchmanager.getText();
	    	ObservableList<Employee> managers=null;
			try {
				managers = service.findManagersByName(manid);
				tvSearchManager.setItems(managers);
		    	tfsearchmanager.setText("");
			} catch (SQLException e) {

			}
	    	
	    	
	    }
	    @FXML
	    private Button btnCancelEmployee;
	    
	    @FXML
	    void actionCancelEmployee(ActionEvent event){
	    	leftsplit.setDividerPositions(0.98);
	    	edit(false);
 	    	edit2(false);
	    	clear();
	    }
	   
	    
}
