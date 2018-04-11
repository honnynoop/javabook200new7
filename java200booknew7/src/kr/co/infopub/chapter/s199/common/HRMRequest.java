package kr.co.infopub.chapter.s199.common;
import java.io.*;
public class HRMRequest implements Serializable {
 private int commandValue;
 private HRMResponse results;
 private Serializable requestObject;
 // CRUD 1234 - 상수이므로 대문자를 권장한다
 public static final int findAllEmployees=2010;           // 1
 public static final int findAllDepartments=2020;         // 2
 public static final int findTreeManagerInEmployee=2030;  // 3
 public static final int findEmployeesByDepartName=2040;  // 4
 public static final int getEmployeesTotal=2510;          // 5
 public static final int findAllDepartments2=2050;        // 6
 public static final int findAllDepCounts=2060;           // 7
 public static final int findAllJobs=2070;                // 8
 public static final int findEmployeesByManagerId=2080;   // 9
 public static final int findEmployeesByEmpId=2090;       // 10
 public static final int findManagersByName=2100;         // 11
 public static final int getTreeMaxLevel=2520;            // 12
 public static final int findAfterAdd=2530;               // 13
 public static final int addEmployee=1010;                // 14
 public static final int updateEmployee=3010;             // 15
 public static final int updateJobHistory=3020;           // 16
 public static final int deleteEmployee=4010;             // 17
 public static final int findEmployeeById=2110;           // 18
 //생성자 setCommandValue()메소드 필요 없도록 생성자에서 처리
 public HRMRequest (int comm) {
    commandValue = comm;
    results = new HRMResponse();
 }
 //처리할 액션의 타입(commandValue)을 리턴
 public int getCommandValue() {
    return commandValue;
 }
 // 직렬화 객체를 반환
 public Serializable getRequestObject() {
	return requestObject;
 }
 // 직렬화 객체를 모두 받을 수 있다- 다형성
 public void setRequestObject(Serializable requestObject) {
	this.requestObject = requestObject;
 }
 // 응답객체 
 public HRMResponse getResult() {
    return results;
 }
}