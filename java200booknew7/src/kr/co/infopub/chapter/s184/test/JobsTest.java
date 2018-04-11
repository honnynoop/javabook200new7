package kr.co.infopub.chapter.s184.test;

import java.sql.SQLException;
import java.util.List;

import kr.co.infopub.chapter.s184.model.EmployeeDAO;

public class JobsTest {

	public static void main(String[] args) {
		
		EmployeeDAO ddao=new EmployeeDAO();
		try {
			List<String> lists=ddao.findAllJobs();
			System.out.println("Results------------------------------------------------");
			for (String dd: lists) {
				System.out.println(dd);
			}
			System.out.printf("¾÷¹« : %d°³\n",lists.size());
		} catch (SQLException e) {
			System.out.println( e);
		}
	}
}
