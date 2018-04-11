package kr.co.infopub.chapter.s199.test;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.co.infopub.chapter.s200.client.EmployeeService;
import kr.co.infopub.chapter.s200.dto.Employee;
import kr.co.infopub.chapter.s200.util.EmpUtil;
// s186, s194-13 test
public class EmpTreeFx extends Application {
 public static void main(String[] args) {
  launch(args);
 }
 // Ʈ�� ���� ���� �����
 public  void makeEmpTree(TreeItem<String> front,List<Employee> dlists,
		                                      String key, int index,int max){
  if(index>max){return ; }  //max�� ������ ��
  for (Employee emp: dlists) {  //������ ������ ���� ���̴�.
	if(EmpUtil.level(emp)!=index){   //���ϴ� ������ ã��
		continue;
	}else if(index>1 && EmpUtil.level(emp)==index){   //2���ʹ� 1�� Ű���� ��
		if(EmpUtil.level(emp,index-1).equals(key)){
			TreeItem<String> aa=new TreeItem<String>(EmpUtil.tname(emp, index));
			front.getChildren().add(aa);
			makeEmpTree(aa,dlists,EmpUtil.level(emp,index),index+1,max);
		}else{
			continue;
		}
	}else if(EmpUtil.level(emp)==1){  //1�� �θ�(0)�� Ű���� ���� ����
		TreeItem<String> aa=new TreeItem<String>(EmpUtil.tname(emp, 1));
		front.getChildren().add(aa);
		makeEmpTree(aa,dlists,EmpUtil.level(emp,1),2,max);
	}
  }
 }	
 @Override
 public void start(Stage stage) {
  BorderPane vbox=new BorderPane();
  vbox.setPadding(new Insets(10, 10, 10, 10));
  int max=0;
  try {
	// DAO, Convert�� ���μ� ����ϰ� ���
    EmployeeService service=EmployeeService.getInstance();
	// ���� ū ������ �����. 
	max = service.getTreeMaxLevel();
	// ������/�μ����� Ʈ�����踦 ��´�. ����������.
	List<Employee> dlists = service.findTreeManagerInEmployee();
	// "�Ŵ����� ����" Ʈ���������� �����.
	TreeItem<String> root = new TreeItem<String>("�Ŵ����� ����");
	// "�Ŵ����� ����" Ʈ�������ۿ� ���� ���� Ʈ�� �������� ���δ�.
	makeEmpTree(root,dlists,"",1,max);
	// Ʈ���信 "�Ŵ����� ����" Ʈ���������� ���δ�.
	TreeView<String> treeView = new TreeView<String>(root);
	root.setExpanded(true);
	vbox.setCenter(treeView);
	Label label = new Label("                          ");
	vbox.setBottom(label);
	Scene scene = new Scene(vbox, 400, 800);
	stage.setScene(scene);
	stage.setTitle("Human Resouce Management System ver. 0.8");
	stage.show();
	treeView.getSelectionModel().selectedItemProperty()
	.addListener( (observable, oldValue, newValue) -> {
		String name =newValue.getValue();
		label.setText(   EmpUtil.dep(name));
	});
	} catch (SQLException e) {
		System.out.println(e);
	}
 }
}