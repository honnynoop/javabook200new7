package kr.co.infopub.chapter.s199.common;
import java.io.*;
public class HRMRequest implements Serializable {
 private int commandValue;
 private HRMResponse results;
 private Serializable requestObject;
 // CRUD 1234 - ����̹Ƿ� �빮�ڸ� �����Ѵ�
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
 //������ setCommandValue()�޼ҵ� �ʿ� ������ �����ڿ��� ó��
 public HRMRequest (int comm) {
    commandValue = comm;
    results = new HRMResponse();
 }
 //ó���� �׼��� Ÿ��(commandValue)�� ����
 public int getCommandValue() {
    return commandValue;
 }
 // ����ȭ ��ü�� ��ȯ
 public Serializable getRequestObject() {
	return requestObject;
 }
 // ����ȭ ��ü�� ��� ���� �� �ִ�- ������
 public void setRequestObject(Serializable requestObject) {
	this.requestObject = requestObject;
 }
 // ���䰴ü 
 public HRMResponse getResult() {
    return results;
 }
}