package kr.co.infopub.chapter.s183.model;
import kr.co.infopub.chapter.s183.dto.DepCountDto;
import kr.co.infopub.chapter.s183.dto.DepartmentDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDAO extends DataBase{
 // 모든 사원의 수	
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
    }finally{
    	close(conn, psmt, rs);
    }
    return count;
 }
 // 사원이 있는 부서만, 부서를 발령받지 못한 사원 NOTYET
 public  List<DepartmentDto> findAllDepartments () throws SQLException {
	 String SQL=""+
	  " SELECT NVL(E.DEPARTMENT_ID,0) DEPARTMENT_ID, "
	 +" NVL(D.DEPARTMENT_NAME,'NOTYET') DEPARTMENT_NAME            "
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
    }finally{
    	close(conn, psmt, rs);
    }
    return empList;
 }
 // 부서가 없는 사원을 포함하여 모든 부서
 public  List<DepartmentDto> findAllDepartments2 () throws SQLException {
	 //부서가 없는 사원도 포함
    String SQL=""+
	 " SELECT  NVL(D.DEPARTMENT_ID,0) DEPARTMENT_ID,            "
	+" NVL(D.DEPARTMENT_NAME,'NOTYET') DEPARTMENT_NAME         "
	+" FROM EMPLOYEES E FULL OUTER JOIN  DEPARTMENTS D         "
	+" ON E.DEPARTMENT_ID=D.DEPARTMENT_ID                      "
	+" GROUP BY D.DEPARTMENT_ID,D.DEPARTMENT_NAME              "
	+" ORDER BY D.DEPARTMENT_ID                                ";
	// 부서가 없는 사원 불포함  
/*    String SQL=""+
	 " SELECT  NVL(D.DEPARTMENT_ID,0) DEPARTMENT_ID,           "
	+" NVL(D.DEPARTMENT_NAME,'NOTYET') DEPARTMENT_NAME         "
	+" FROM DEPARTMENTS D              ";
*/
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
    }finally{
    	close(conn, psmt, rs);
    }
    return empList;
 }
 // 부서원이 있는 부서와 부서원의 수
 public  List<DepCountDto> findAllDepCounts () throws SQLException {
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
    }finally{
    	close(conn, psmt, rs);
    }
    return empList;
 }
}
