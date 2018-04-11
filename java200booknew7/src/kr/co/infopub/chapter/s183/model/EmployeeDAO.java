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
 // ��� ����� ��	
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
 // ����� �ִ� �μ���, �μ��� �߷ɹ��� ���� ��� NOTYET
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
 // �μ��� ���� ����� �����Ͽ� ��� �μ�
 public  List<DepartmentDto> findAllDepartments2 () throws SQLException {
	 //�μ��� ���� ����� ����
    String SQL=""+
	 " SELECT  NVL(D.DEPARTMENT_ID,0) DEPARTMENT_ID,            "
	+" NVL(D.DEPARTMENT_NAME,'NOTYET') DEPARTMENT_NAME         "
	+" FROM EMPLOYEES E FULL OUTER JOIN  DEPARTMENTS D         "
	+" ON E.DEPARTMENT_ID=D.DEPARTMENT_ID                      "
	+" GROUP BY D.DEPARTMENT_ID,D.DEPARTMENT_NAME              "
	+" ORDER BY D.DEPARTMENT_ID                                ";
	// �μ��� ���� ��� ������  
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
 // �μ����� �ִ� �μ��� �μ����� ��
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
