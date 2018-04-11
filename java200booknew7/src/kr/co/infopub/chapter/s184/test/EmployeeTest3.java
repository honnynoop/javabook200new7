package kr.co.infopub.chapter.s184.test;

import java.sql.SQLException;
import kr.co.infopub.chapter.s184.model.EmployeeDAO;
// getTreeMaxLevel ������������ ���� ū ����
public class EmployeeTest3 {

	public static void main(String[] args) {
		EmployeeDAO employeeDAO=new EmployeeDAO();
		int max=-1;;
		try {
			max = employeeDAO.getTreeMaxLevel();
			System.out.println(max);
		} catch (SQLException e) {
			System.out.println(e);
		}
		 
	}
}
/*
CRUD-------------------------------class sample.model.EmployeeDAO.getTreeMaxLevel()
SELECT MAX(LEVEL) 
  FROM EMPLOYEES 
  START WITH  MANAGER_ID  IS NULL 
  CONNECT BY PRIOR  EMPLOYEE_ID = MANAGER_ID
CRUD--------------------------------------2017-05-13 20:50
5
*/