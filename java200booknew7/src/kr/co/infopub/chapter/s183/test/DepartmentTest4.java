package kr.co.infopub.chapter.s183.test;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.TreeItem;
import kr.co.infopub.chapter.s183.dto.DepartmentDto;
import kr.co.infopub.chapter.s183.model.EmployeeDAO;
import kr.co.infopub.chapter.s183.util.EmpUtil;
// 부서는 계층 구조가 없어서 트리를 만들 필요가 없다.
public class DepartmentTest4 {
 public static void main(String[] args) {
	EmployeeDAO ddao=new EmployeeDAO();
	try {
		List<DepartmentDto> lists=ddao.findAllDepartments();
		TreeItem<String> root = new TreeItem<String>("부서");
		makeDepTree(root,lists);
		printTree(root,1);
	} catch (SQLException e) {
		System.out.println( e);
	}
 }
 public static void makeDepTree(
		 TreeItem<String> front,List<DepartmentDto> dlists){
	for (DepartmentDto dep: dlists) {
	  TreeItem<String> troots=new TreeItem<String>(EmpUtil.tname(dep));
	  front.getChildren().add(troots);
	}
 }	
 public static void printTree(TreeItem<String> root , int index){
	String tt="\t";
	String s="";
	for(int i=1; i<index ; i++){
		s+=tt;
	}
	System.out.println(s+""+root.getValue());
	for(TreeItem<String> ss:root.getChildren()){
		printTree(ss, index+1);
	}
 }
}
