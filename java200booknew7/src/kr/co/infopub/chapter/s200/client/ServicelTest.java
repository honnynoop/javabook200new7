package kr.co.infopub.chapter.s200.client;
import java.sql.SQLException;
import java.util.List;

import kr.co.infopub.chapter.s200.dto.Employee;
public class ServicelTest {
	public static void main(String[] args) {
		try {
			EmployeeService pro=EmployeeService.getInstance();
			List<Employee> emps= pro.findTreeManagerInEmployee();
			for (Employee emp:emps) {
				System.out.println(emp);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
