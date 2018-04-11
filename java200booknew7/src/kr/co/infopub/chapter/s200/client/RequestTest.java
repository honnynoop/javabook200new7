package kr.co.infopub.chapter.s200.client;
import java.util.List;
import java.util.Scanner;

import kr.co.infopub.chapter.s199.common.DepartmentDto;
import kr.co.infopub.chapter.s199.common.EmployeeDto;
public class RequestTest {

	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		System.out.println("----------HRM Service Protocol------------");
		kinds();
		int n=scann.nextInt();
		switch(n){
		case 1: findAllEmployees(); break;
		case 2: findAllDepartments(); break;
		default: break;
		}
	}
	public static void findAllEmployees(){
		try {
			HRMProtocol pro=new HRMProtocol("127.0.0.1");
			List<EmployeeDto> emps= pro.findAllEmployees();
			for (EmployeeDto emp:emps) {
				System.out.println(emp);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void findAllDepartments(){
		try {
			HRMProtocol pro=new HRMProtocol("127.0.0.1");
			List<DepartmentDto> emps= pro.findAllDepartments();
			for (DepartmentDto emp:emps) {
				System.out.println(emp);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void kinds(){
		String [] s={
	    "public static final int findAllEmployees=2010;           // 1",
	    "public static final int findAllDepartments=2020;         // 2",
	    "public static final int findTreeManagerInEmployee=2030;  // 3",
	    "public static final int findEmployeesByDepartName=2040;  // 4",
	    "public static final int getEmployeesTotal=2510;          // 5",
	    "public static final int findAllDepartments2=2050;        // 6",
	    "public static final int findAllDepCounts=2060;           // 7",
	    "public static final int findAllJobs=2070;                // 8",
	    "public static final int findEmployeesByManagerId=2080;   // 9",
	    "public static final int findEmployeesByEmpId=2090;       // 10",
	    "public static final int findManagersByName=2100;         // 11",
	    "public static final int getTreeMaxLevel=2520;            // 12",
	    "public static final int findAfterAdd=2530;               // 13",
	    "public static final int addEmployee=1010;                // 14",
	    "public static final int updateEmployee=3010;             // 15",
	    "public static final int updateJobHistory=3020;           // 16",
	    "public static final int deleteEmployee=4010;             // 17",
	    "public static final int findEmployeeById=2110;           // 18"
		};
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}
}
