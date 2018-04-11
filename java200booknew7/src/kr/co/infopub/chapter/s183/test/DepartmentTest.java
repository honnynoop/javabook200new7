package kr.co.infopub.chapter.s183.test;
import java.sql.SQLException;
import java.util.List;
import kr.co.infopub.chapter.s183.dto.DepartmentDto;
import kr.co.infopub.chapter.s183.model.EmployeeDAO;
import kr.co.infopub.chapter.s183.util.EmpUtil;
public class DepartmentTest {
 public static void main(String[] args) {
	EmployeeDAO ddao=new EmployeeDAO();
	try {
		List<DepartmentDto> lists=ddao.findAllDepartments();
		System.out.println("Results----------->>>");
		for (DepartmentDto dd: lists) {
			System.out.println(EmpUtil.tname(dd));
		}
		System.out.printf("직원이 있는 부서 수 : %d개\n",lists.size());
	} catch (SQLException e) {
		System.out.println( e);
	}
 }
}
