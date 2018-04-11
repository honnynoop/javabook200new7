package kr.co.infopub.chapter.s183.test;
import java.sql.SQLException;
import java.util.List;
import kr.co.infopub.chapter.s183.dto.DepCountDto;
import kr.co.infopub.chapter.s183.model.EmployeeDAO;
import kr.co.infopub.chapter.s183.util.EmpUtil;
public class DepartmentTest3 {
 public static void main(String[] args) {
	EmployeeDAO ddao=new EmployeeDAO();
	try {
		// �μ����� �ִ� �μ�
		List<DepCountDto> dlists = ddao.findAllDepCounts();
		int total=0;
		for (DepCountDto dd: dlists) {
			total+=dd.getCount();
			System.out.print(EmpUtil.tname(dd));
			System.out.println("\t\t"+dd.getCount()+"��");
		}
		System.out.println("----------��--------");
		System.out.println(total+"��");
		
		int tot2=ddao.getEmployeesTotal();
		System.out.println(tot2+"��");
	} catch (SQLException e) {
		System.out.println( e);
	}
 }
}
