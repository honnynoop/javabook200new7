package kr.co.infopub.chapter.s184.test;

import java.sql.SQLException;
import java.util.List;

import kr.co.infopub.chapter.s184.dto.EmployeeDto;
import kr.co.infopub.chapter.s184.model.EmployeeDAO;

public class EmployeeTest {

	public static void main(String[] args) {
		EmployeeDAO ddao=new EmployeeDAO();
		try {
			List<EmployeeDto> lists=ddao.findAllEmployees();
			for (EmployeeDto dd: lists) {
				String st=String.format("%d(%s %s)", dd.getEmployeeId(),dd.getFirstName(),dd.getLastName());
				System.out.println(st);
			}
		} catch (SQLException e) {
			System.out.println( e);
		}
	}
}
