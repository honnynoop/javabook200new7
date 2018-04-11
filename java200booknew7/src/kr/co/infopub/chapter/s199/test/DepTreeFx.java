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
import kr.co.infopub.chapter.s200.dto.Department;
import kr.co.infopub.chapter.s200.util.EmpUtil;
// s185
public class DepTreeFx extends Application {
public void makeDepTree(TreeItem<String> front,List<Department> dlists){
	for (Department dep: dlists) {
      TreeItem<String> troots=new TreeItem<String>(EmpUtil.tname(dep));
      front.getChildren().add(troots);
	}
}	
@Override
public void start(Stage stage) {
  BorderPane vbox=new BorderPane();
  vbox.setPadding(new Insets(10, 10, 10, 10));
  try {
	    // DAO, Convert�� ���μ� ����ϰ� ���
	    EmployeeService service=EmployeeService.getInstance();
	    // ����� �ִ� �μ� ��������
		List<Department> dlists=service.findAllDepartments();
		// �μ� ������
		TreeItem<String> root = new TreeItem<String>("�μ�");
		// �μ� �����ۿ� �μ��� ���̱�
		makeDepTree(root,dlists);
		// Ʈ���信 Ʈ�������� ���̱�
		TreeView<String> treeView = new TreeView<String>(root);
	
	root.setExpanded(true);
	vbox.setCenter(treeView);
	Label label = new Label("                          ");
	vbox.setBottom(label);
	Scene scene = new Scene(vbox, 400, 800);
	stage.setScene(scene);
	stage.setTitle("Human Resouce Management System ver. 0.8");
	stage.show();
	// Ʈ������ �� �������� �����ϸ� ���õ� �������� ������ ȭ�� �ϴܿ� ���
	treeView.getSelectionModel().selectedItemProperty()
	.addListener( (observable, oldValue, newValue) -> {
		String name =newValue.getValue();
		label.setText(   EmpUtil.dep(name));
		});
   } catch (SQLException e) {
		System.out.println(e);
   }
 }
 public static void main(String[] args) {
   launch(args);
 }
}