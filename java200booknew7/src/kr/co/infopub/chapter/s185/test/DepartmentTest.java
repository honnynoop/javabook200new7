package kr.co.infopub.chapter.s185.test;
import java.sql.SQLException;
import java.util.List;
import kr.co.infopub.chapter.s185.dto.DepConvert;
import kr.co.infopub.chapter.s185.dto.Department;
import kr.co.infopub.chapter.s185.dto.DepartmentDto;
import kr.co.infopub.chapter.s185.model.EmployeeDAO;
import kr.co.infopub.chapter.s185.util.EmpUtil;
public class DepartmentTest {
 public static void main(String[] args) {
	EmployeeDAO ddao=new EmployeeDAO();
	try {
		List<DepartmentDto> blist=ddao.findAllDepartments();
		List<Department> lists=DepConvert.toObservProFromDto(blist);
		System.out.println("Results------------------------");
		for (Department dd: lists) {
			System.out.println(EmpUtil.tname(dd));
		}
		System.out.printf("직원이 있는 부서 수 : %d개\n",lists.size());
	} catch (SQLException e) {
		System.out.println( e);
	}
 }
}
