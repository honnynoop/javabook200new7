package kr.co.infopub.chapter.s184.model;
import kr.co.infopub.chapter.s184.dto.DepCountDto;
import kr.co.infopub.chapter.s184.dto.DepartmentDto;
import kr.co.infopub.chapter.s184.dto.EmployeeDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDAO extends DataBase{
 public  int getEmployeesTotal () throws SQLException {
    String SQL = " SELECT COUNT(*) FROM EMPLOYEES ";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    int count=0;
    try {
        conn=getConnection();
        psmt=conn.prepareStatement(SQL);
        log("3/6 getEmployeesTotal Success!!!");
        log(SQL,"getEmployeesTotal");
        rs =psmt.executeQuery();
        log("4/6 getEmployeesTotal Success!!!");
       
        if (rs.next()) {
            count=rs.getInt(1);  
        }
        log("5/6 getEmployeesTotal Success!!!");
    } catch (SQLException e) {
    	log(" getEmployeesTotal Error!!!",e);
    	throw e;
    }finally{
    	close(conn, psmt, rs);
    }
    return count;
 }
 public  List<DepartmentDto> findAllDepartments () throws SQLException {
    String SQL=""+
      " SELECT NVL(E.DEPARTMENT_ID,0) DEPARTMENT_ID, NVL(D.DEPARTMENT_NAME,'NOTYET') DEPARTMENT_NAME            "
     +" FROM EMPLOYEES E, DEPARTMENTS D            "
     +" WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID(+)            " 
     +" GROUP BY E.DEPARTMENT_ID, D.DEPARTMENT_NAME            "
     +" ORDER BY E.DEPARTMENT_ID            ";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    List<DepartmentDto> empList = new ArrayList<>();
    try {
    	 conn=getConnection();
         psmt=conn.prepareStatement(SQL);
         log(SQL,"findAllDepartments");
         log("3/6 findAllDepartments Success!!!");
         rs =psmt.executeQuery();
         log("4/6 findAllDepartments Success!!!");
         while (rs.next()) {
        	 DepartmentDto emp = new DepartmentDto();
             emp.setDepartment_id(rs.getInt("DEPARTMENT_id"));
             emp.setDepartment_name(rs.getString("DEPARTMENT_name"));
             empList.add(emp);
         }
         log("5/6 findAllDepartments Success!!!");
    } catch (SQLException e) {
    	log(" findAllDepartments Error!!!",e);
    	throw e;
    }finally{
    	close(conn, psmt, rs);
    }
    return empList;
 }
 public  List<DepartmentDto> findAllDepartments2 () throws SQLException {
    String SQL=""+
	 " SELECT  NVL(D.DEPARTMENT_ID,0) DEPARTMENT_ID,            "
	+" NVL(D.DEPARTMENT_NAME,'NOTYET') DEPARTMENT_NAME         "
	+" FROM EMPLOYEES E FULL OUTER JOIN  DEPARTMENTS D         "
	+" ON E.DEPARTMENT_ID=D.DEPARTMENT_ID                      "
	+" GROUP BY D.DEPARTMENT_ID,D.DEPARTMENT_NAME              "
	+" ORDER BY D.DEPARTMENT_ID                                ";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    List<DepartmentDto> empList = new ArrayList<>();
    try {
    	 conn=getConnection();
         psmt=conn.prepareStatement(SQL);
         log(SQL,"findAllDepartments");
         log("3/6 findAllDepartments Success!!!");
         rs =psmt.executeQuery();
         log("4/6 findAllDepartments Success!!!");
         while (rs.next()) {
        	 DepartmentDto emp = new DepartmentDto();
             emp.setDepartment_id(rs.getInt("DEPARTMENT_id"));
             emp.setDepartment_name(rs.getString("DEPARTMENT_name"));
             empList.add(emp);
         }
         log("5/6 findAllDepartments Success!!!");
    } catch (SQLException e) {
    	log(" findAllDepartments Error!!!",e);
    	throw e;
    }finally{
    	close(conn, psmt, rs);
    }
    return empList;
 }
 public  List<DepCountDto> findAllDepCounts ()   throws SQLException{
    String SQL = ""
	+" SELECT COUNT(*) COUNT, NVL(E.DEPARTMENT_ID,0) DEPARTMENT_ID,        "
	+"         NVL(D.DEPARTMENT_NAME,'NOTYET') DEPARTMENT_NAME             "
	+" FROM EMPLOYEES E, DEPARTMENTS D                                     "
	+" WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID(+)                            "
	+" GROUP BY E.DEPARTMENT_ID, D.DEPARTMENT_NAME                         "
	+" ORDER BY COUNT DESC,E.DEPARTMENT_ID ASC                             ";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    List<DepCountDto> empList = new ArrayList<>();
    try {
    	 conn=getConnection();
         psmt=conn.prepareStatement(SQL);
         log("3/6 findAllDepCounts Success!!!");
         log(SQL,"findAllDepCounts");
         rs =psmt.executeQuery();
         log("4/6 findAllDepCounts Success!!!");
         while (rs.next()) {
        	 DepCountDto emp = new DepCountDto();
             emp.setCount(rs.getInt("COUNT"));
             emp.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
             emp.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
             empList.add(emp);
         }
         log("5/6 findAllDepCounts Success!!!");
    } catch (SQLException e) {
    	log(" findAllDepCounts Error !!!",e);
    	throw e;
    }finally{
    	close(conn, psmt, rs);
    }
    return empList;
 }
 //-------------------------------------emp
 public  List<EmployeeDto> findAllEmployees ()  throws SQLException{
    String SQL = ""+
	" SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL,                   "
	+ " PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY , COMMISSION_PCT,              "
	+"  MANAGER_ID, DEPARTMENT_ID FROM EMPLOYEES ORDER BY EMPLOYEE_ID          ";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    List<EmployeeDto> empList = new ArrayList<>();
    try {
    	 conn=getConnection();
         psmt=conn.prepareStatement(SQL);
         log("3/6 findAllEmployees Success!!!");
         log(SQL,"findAllEmployees");
         rs =psmt.executeQuery();
         log("4/6 findAllEmployees Success!!!");
         while (rs.next()) {
        	 EmployeeDto emp = new EmployeeDto();
             emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
             emp.setFirstName(rs.getString("FIRST_NAME"));
             emp.setLastName(rs.getString("LAST_NAME"));
             emp.setEmail(rs.getString("EMAIL"));
             emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
             emp.setHireDate(rs.getDate("HIRE_DATE"));
             emp.setJobId(rs.getString("JOB_ID"));
             emp.setSalary(rs.getInt("SALARY"));
             emp.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
             emp.setManagerId(rs.getInt("MANAGER_ID"));
             emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
             empList.add(emp);
         }
         log("5/6 findAllEmployees Success!!!");
    } catch (SQLException e) {
    	log(" findAllEmployees Error !!!",e);
    	throw e;
    }finally{
    	close(conn, psmt, rs);
    }
    return empList;
 }
 public  List<EmployeeDto> findTreeManagerInEmployee ()  throws SQLException{
    String SQL = ""
+" SELECT                   "
+" EMPLOYEE_ID,             "
+" MANAGER_ID,              "
+" FIRST_NAME,              "
+" LAST_NAME, DEPARTMENT_ID, ORDER2              "
+" FROM ( SELECT                      "
+" 	 EMPLOYEE_ID,            "
+" 	 MANAGER_ID,             "
+" 	 FIRST_NAME,             "
+" 	 LAST_NAME, DEPARTMENT_ID, LEVEL ,             "
+" 	 SYS_CONNECT_BY_PATH(TO_CHAR(LEVEL,'FM000')||EMPLOYEE_ID,'/') ORDER2         "
+"   FROM EMPLOYEES              "
+"   START WITH  MANAGER_ID  IS NULL             "
+"   CONNECT BY PRIOR  EMPLOYEE_ID = MANAGER_ID)             "
+" ORDER BY ORDER2  ";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    List<EmployeeDto> empList = new ArrayList<>();
    try {
    	 conn=getConnection();
         psmt=conn.prepareStatement(SQL);
         log("3/6 findTreeManagerInEmployee Success!!!");
         log(SQL,"findTreeManagerInEmployee");
         rs =psmt.executeQuery();
         log("4/6 findTreeManagerInEmployee Success!!!");
         while (rs.next()) {
        	 EmployeeDto emp = new EmployeeDto();
             emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
             emp.setManagerId(rs.getInt("MANAGER_ID"));
             emp.setFirstName(rs.getString("FIRST_NAME"));
             emp.setLastName(rs.getString("LAST_NAME"));
             emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
             emp.setOrder2(rs.getString("ORDER2"));
             empList.add(emp);
         }
         log("5/6 findTreeManagerInEmployee Success!!!");
    } catch (SQLException e) {
    	 log(" findTreeManagerInEmployee Error!!!",e);
    	 throw e;
    }finally{
    	close(conn, psmt, rs);
    }
    return empList;
 }
 public  int getTreeMaxLevel () throws SQLException {
	String SQL = ""
	+" SELECT MAX(LEVEL)                         "
	+" FROM EMPLOYEES                            "
	+" START WITH  MANAGER_ID  IS NULL           "
	+" CONNECT BY PRIOR  EMPLOYEE_ID = MANAGER_ID";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    int count=0;
    try {
    	 conn=getConnection();
         psmt=conn.prepareStatement(SQL);
         log("3/6 getTreeMaxLevel Success!!!");
         log(SQL,"getTreeMaxLevel");
         rs =psmt.executeQuery();
         log("4/6 getTreeMaxLevel Success!!!");
         if (rs.next()) {
             count=rs.getInt(1);
         }
         log("5/6 getTreeMaxLevel Success!!!");
    } catch (SQLException e) {
    	log(" getTreeMaxLevel Error!!!",e);
    	 throw e;
    }finally{
    	close(conn, psmt, rs);
    }
    return count;
 }
 public  List<EmployeeDto> findEmployeesByManagerId (String empid)  
		                                           throws SQLException{
    String SQL = "SELECT "
	+" E.EMPLOYEE_ID EMPLOYEE_ID, D.EMPLOYEE_ID MANAGER_ID,           "
	+" E.FIRST_NAME ,                                                 "
	+" E.LAST_NAME, E.EMAIL, E.PHONE_NUMBER,E.HIRE_DATE               "
	+" FROM EMPLOYEES E, EMPLOYEES D                                  "
	+" WHERE D.EMPLOYEE_ID=E.MANAGER_ID AND E.MANAGER_ID=?            ";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    List<EmployeeDto> empList = new ArrayList<>();
    try {
    	 conn=getConnection();
         psmt=conn.prepareStatement(SQL);
         psmt.setString(1, empid);
         log("3/6 findEmployeesByManagerId Success!!!");
         log(SQL,"findEmployeesByManagerId",empid);
         rs =psmt.executeQuery();
         log("4/6 findEmployeesByManagerId Success!!!");
         while (rs.next()) {
        	 EmployeeDto emp = new EmployeeDto();
             emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
             emp.setFirstName(rs.getString("FIRST_NAME"));
             emp.setLastName(rs.getString("LAST_NAME"));
             emp.setEmail(rs.getString("EMAIL"));
             emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
             emp.setHireDate(rs.getDate("HIRE_DATE"));
             empList.add(emp);
         }
         log("5/6 findEmployeesByManagerId Success!!!");
    } catch (SQLException e) {
    	 log(" findEmployeesByManagerId Error!!!",e);
    	 throw e;
    }finally{
    	close(conn, psmt, rs);
    }
    return empList;
 }
 public  List<String> findAllJobs () throws SQLException {
    String SQL= " SELECT JOB_ID, JOB_TITLE FROM JOBS           ";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    List<String> jobList = new ArrayList<>();
    try {
    	 conn=getConnection();
         psmt=conn.prepareStatement(SQL);
         log(SQL,"findAllJobs");
         log("3/6 findAllJobs Success!!!");
         rs =psmt.executeQuery();
         log("4/6 findAllJobs Success!!!");
         while (rs.next()) {
             jobList.add(String.format("%s(%s)",
               rs.getString("JOB_ID"),rs.getString("JOB_TITLE")));
         }
         log("5/6 findAllJobs Success!!!");
    } catch (SQLException e) {
    	log(" findAllJobs Error!!!",e);
    }finally{
    	close(conn, psmt, rs);
    }
    return jobList;
 } 
}
