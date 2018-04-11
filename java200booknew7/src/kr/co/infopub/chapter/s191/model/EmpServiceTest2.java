package kr.co.infopub.chapter.s191.model;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import kr.co.infopub.chapter.s191.dto.Department;
public class EmpServiceTest2 {
 public static void main(String[] args) {
	EmployeeService service=EmployeeService.getInstance();
	try {
		ObservableList<Department> departlists=service.findAllDepartments();
		for (Department depart : departlists) {
			System.out.print(depart.getDepartment_id()+"\t");
			System.out.println(depart.getDepartment_name());
		}
	} catch (SQLException e) {
		System.out.println(e);
	}
 }
}
