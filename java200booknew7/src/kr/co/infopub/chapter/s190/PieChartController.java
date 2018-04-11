package kr.co.infopub.chapter.s190;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import kr.co.infopub.chapter.s190.dto.EmpConvert;
import kr.co.infopub.chapter.s190.dto.DepCount;
import kr.co.infopub.chapter.s190.dto.DepCountConvert;
import kr.co.infopub.chapter.s190.dto.DepCountDto;
import kr.co.infopub.chapter.s190.dto.Employee;
import kr.co.infopub.chapter.s190.dto.EmployeeDto;
import kr.co.infopub.chapter.s190.model.EmployeeDAO;
import kr.co.infopub.chapter.s190.util.EmpUtil;
import kr.co.infopub.chapter.s190.util.PTS;

public class PieChartController {

//    @FXML
//    private PieChart pieChart;
    @FXML
    private AnchorPane pieAnchor;
    
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
     
    EmployeeDAO employeeDAO=new EmployeeDAO();
    
   
    public void refresh(){
    	pieAnchor.getChildren().clear();
    	initialize();
    }
    @FXML
    void initialize() {
    	
    	
    	empIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
        empNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        empLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        empEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        empPhoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        empHireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());
    	
    	try {
    		
    	   List<DepCountDto> deplist = employeeDAO.findAllDepCounts();
 		   List<DepCount> dlists =DepCountConvert.toObservProFromDto(deplist);
 		   int total=employeeDAO.getEmployeesTotal();
 		   System.out.println("test chart =============================="+dlists.size() +"   "+total);
 		   //db 쿼리로 구하는 것이 더 좋을 것이다.
// 		   int total=0;
// 		   for(DepCount dc: dlists){
// 			   total+=dc.getCount();
// 		   }
 		   //final int  ftotal=total;   
 			
 			PieChart pieChart = new PieChart();
 			pieChart.setTitle("부서별 인원수 총"+total+"명 " +PTS.toDay());
 	        pieChart.setData(getChartData(dlists));
 	        pieChart.setClockwise(true); 
 	        pieChart.setStartAngle(180);  
 	        pieChart.setLabelsVisible(true); 
 	        pieChart.setPrefWidth(800);
 	        pieChart.setPrefHeight(600); 
 	        final Label caption = new Label("");
 	        String value=
 	    	    	   "-fx-font-size: 25px;           "
 	    	    	  +"-fx-font-family: 'Arial Black';    ";

 	        caption.setStyle(value);
 	        
 	        for (final PieChart.Data data : pieChart.getData()) {
 	            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
 	                    e-> {
 	                    	    ObservableList<Employee> populateEmploye=null;
								try {
									List<EmployeeDto> edtlist=employeeDAO.findEmployeesByDepartName( EmpUtil.dep(data.getName() ));
									populateEmploye = EmpConvert.toObservProFromDto(edtlist);
									populateEmployees(populateEmploye);
								} catch (SQLException e1) {
									
								}
 	                    	   
 	                            caption.setTranslateX(e.getSceneX());
 	                            caption.setTranslateY(e.getSceneY());
 	                            String sft=String.format("%s %.2f%%(%.0f명)", 
 	                            		EmpUtil.dep(data.getName()),
 	                            		100*data.getPieValue()/total,
 	                            		data.getPieValue());
 	                            caption.setText(sft);
 	                           
 	                        }
 	                    );
 	        }
 	       pieAnchor.getChildren().addAll(pieChart,caption);
 	       
 		} catch (SQLException e) {
 			System.out.println(e);
 		}

    }
    @FXML
    private void populateEmployees (ObservableList<Employee> empData)  {
        employeeTable.setItems(empData);
    }
    
    private ObservableList<Data> getChartData(List<DepCount> dlists) {
        ObservableList<Data> answer = FXCollections.observableArrayList();
        
        for (DepCount dc: dlists) {
        	 answer.add(new PieChart.Data( dc.getDepartment_name()+"("+dc.getDepartment_id()+")", dc.getCount()  ));
    	}

        return answer;
    }
    
    
    
}
