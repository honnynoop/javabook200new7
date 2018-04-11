package kr.co.infopub.chapter.s200.client;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import kr.co.infopub.chapter.s199.common.EmployeeDto;
public class ProtocolTest {
	public static void main(String[] args) {
		try {
			HRMProtocol pro=new HRMProtocol("127.0.0.1");
			List<EmployeeDto> emps= pro.findTreeManagerInEmployee();
			for (EmployeeDto emp:emps) {
				System.out.println(emp);
			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
