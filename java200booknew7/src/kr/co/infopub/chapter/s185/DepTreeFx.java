package kr.co.infopub.chapter.s185;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.co.infopub.chapter.s185.dto.DepConvert;
import kr.co.infopub.chapter.s185.dto.Department;
import kr.co.infopub.chapter.s185.dto.DepartmentDto;
import kr.co.infopub.chapter.s185.model.EmployeeDAO;
import kr.co.infopub.chapter.s185.util.EmpUtil;
public class DepTreeFx extends Application {
EmployeeDAO employeeDAO=new EmployeeDAO();

public  void makeDepTree(TreeItem<String> front,List<Department> dlists){
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
	// 사원이 있는 부서와 아직 발령받지 사원(NOTYET)
	List<DepartmentDto> blist  = employeeDAO.findAllDepartments ();
	// JavaFX TreeItem을 위한 리스트로 변환
	List<Department> dlists=DepConvert.toObservProFromDto(blist);
	// 부서 아이템
	TreeItem<String> root = new TreeItem<String>("부서");
	// 부서 아이템에 부서들 붙이기
	makeDepTree(root,dlists);
	// 트리뷰에 트리아이템 붙이기
	TreeView<String> treeView = new TreeView<String>(root);
	
	root.setExpanded(true);
	vbox.setCenter(treeView);
	Label label = new Label("                          ");
	vbox.setBottom(label);
	Scene scene = new Scene(vbox, 400, 800);
	stage.setScene(scene);
	stage.setTitle("Human Resouce Management System ver. 0.2");
	stage.show();
	// 트리뷰의 한 아이템을 선택하면 선택된 아이템의 정보를 화면 하단에 출력
	treeView.getSelectionModel().selectedItemProperty()
	.addListener( (observable, oldValue, newValue) -> {
		String name =newValue.getValue();
		label.setText(   EmpUtil.dep(name));
		});
//	treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem>() {
//		public void changed(javafx.beans.value.ObservableValue<? extends TreeItem> observable, TreeItem oldValue, TreeItem newValue) {
//			System.out.println(newValue.getValue());
//		};
//	});
   } catch (SQLException e) {
		System.out.println(e);
   }
 }
 public static void main(String[] args) {
   launch(args);
 }
}