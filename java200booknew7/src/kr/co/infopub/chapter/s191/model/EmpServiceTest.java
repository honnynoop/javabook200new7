package kr.co.infopub.chapter.s191.model;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import kr.co.infopub.chapter.s191.dto.Employee;
public class EmpServiceTest {
 public static void main(String[] args) {
	EmployeeService service=EmployeeService.getInstance();
	try {
		ObservableList<Employee> emplists=service.findAllEmployees();
		for (Employee employee : emplists) {
			System.out.print(employee.getEmployeeId()+"\t");
			System.out.print(employee.getFirstName()+"\t");
			System.out.println(employee.getLastName());
		}
	} catch (SQLException e) {
		System.out.println(e);
	}
 }
}
