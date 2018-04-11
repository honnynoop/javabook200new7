package kr.co.infopub.chapter.s184.test;

import java.sql.SQLException;
import java.util.List;
import kr.co.infopub.chapter.s184.dto.EmployeeDto;
import kr.co.infopub.chapter.s184.model.EmployeeDAO;
// findEmployeesByManagerId 바로 아래의 부하직원
public class EmployeeTest4 {
	public static void main(String[] args) {
		EmployeeDAO ddao=new EmployeeDAO();
		try {
			List<EmployeeDto> lists=ddao.findEmployeesByManagerId(100+"");
			for (EmployeeDto dd: lists) {
				System.out.println(dd.getEmployeeId()+"  "+dd.getFirstName()+"  "+dd.getLastName());
			}
		} catch (SQLException e) {
			System.out.println( e);
		}
	}
}
/*
CRUD-------------------------------class sample.model.EmployeeDAO.findEmployeesByManagerId()
SELECT  E.EMPLOYEE_ID EMPLOYEE_ID, D.EMPLOYEE_ID MANAGER_ID, 
  E.FIRST_NAME , 
  E.LAST_NAME, E.EMAIL, E.PHONE_NUMBER,E.HIRE_DATE 
  FROM EMPLOYEES E, EMPLOYEES D 
  WHERE D.EMPLOYEE_ID=E.MANAGER_ID AND E.MANAGER_ID=?
Data : 100
CRUD--------------------------------------2017-05-13 20:22
101  Neena  Kochhar
102  Lex  De Haan
114  Den  Raphaely
120  Matthew  Weiss
121  Adam  Fripp
122  Payam  Kaufling
123  Shanta  Vollman
124  Kevin  Mourgos
145  John  Russell
146  Karen  Partners
147  Alberto  Errazuriz
148  Gerald  Cambrault
149  Eleni  Zlotkey
201  Michael  Hartstein
*/