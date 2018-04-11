package kr.co.infopub.chapter.s199.middle;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
import kr.co.infopub.chapter.s199.common.DepCountDto;
import kr.co.infopub.chapter.s199.common.DepartmentDto;
import kr.co.infopub.chapter.s199.common.EmployeeDto;
import kr.co.infopub.chapter.s200.client.HRMProtocol;
import kr.co.infopub.chapter.s200.dto.DepConvert;
import kr.co.infopub.chapter.s200.dto.DepCount;
import kr.co.infopub.chapter.s200.dto.DepCountConvert;
import kr.co.infopub.chapter.s200.dto.Department;
import kr.co.infopub.chapter.s200.dto.EmpConvert;
import kr.co.infopub.chapter.s200.dto.Employee;
public class EmployeeService { 
 // 자신을 private static 
 private static EmployeeService employeeService;
 private static EmployeeDAO employeeDAO; 
 // 생성자도 private
 private EmployeeService() {
	 employeeDAO=new EmployeeDAO();
 }
 // static -> 한번의 객체생성 
 public static EmployeeService getInstance(){
	if(employeeService==null){
		employeeService=new EmployeeService();
	}
	return employeeService;
 }//--------------DAO를 감싸고, List -> ObservableList
 public List<DepartmentDto>
                     findAllDepartments() throws SQLException{
	List<DepartmentDto> blist=employeeDAO.findAllDepartments();
	return blist;
 }
 public List<EmployeeDto> findAllEmployees() throws SQLException{
	List<EmployeeDto> blist  = employeeDAO.findAllEmployees ();
	return blist;
 }
 public List<EmployeeDto> findTreeManagerInEmployee() 
		                                   throws SQLException{
	List<EmployeeDto> bdlists= employeeDAO.findTreeManagerInEmployee();
	return bdlists;
 }
 public int getTreeMaxLevel() throws SQLException{
	return  employeeDAO.getTreeMaxLevel();
 }
 public List<DepCountDto> findAllDepCounts() throws SQLException{
    List<DepCountDto> deplist = employeeDAO.findAllDepCounts();
    return deplist;
 }
 //-------------------------여기 까지 테스트용
 public  List<EmployeeDto>
          findEmployeesByDepartName(String val) throws SQLException{
	 List<EmployeeDto> emplists=
			               employeeDAO.findEmployeesByDepartName(val);
	 return emplists;
 }
 
 public List<EmployeeDto>
                 findEmployeesByEmpId(String val) throws SQLException{
  List<EmployeeDto> elists=employeeDAO.findEmployeesByEmpId(val);//100
	return elists;
 }
 public EmployeeDto findEmployeeById(String string) throws SQLException{
	EmployeeDto edto =employeeDAO.findEmployeeById(string);
	return edto;
 }
 public List<EmployeeDto>
            findManagersByName(String searchname) throws SQLException{
	List<EmployeeDto> elists=employeeDAO.findManagersByName(searchname);
	return elists;
 }
 public List<String> findAllJobs() throws SQLException{
    List<String> jlists=employeeDAO.findAllJobs();
	return jlists;
 }
 public List<DepartmentDto>
                           findAllDepartments2() throws SQLException{
    List<DepartmentDto> edeps=employeeDAO.findAllDepartments2 ();
	return edeps;
 }
 public int addEmployee(EmployeeDto empdto) throws SQLException{
	return employeeDAO.addEmployee(empdto);
 }
 public boolean updateEmployee(EmployeeDto emp) throws SQLException{
	return employeeDAO.updateEmployee(emp);
 }
 public boolean deleteEmployee(EmployeeDto emp) throws SQLException{
	return employeeDAO.deleteEmployee(emp);
 }
 public int getEmployeesTotal() throws SQLException{
	return employeeDAO.getEmployeesTotal();
 }
}
