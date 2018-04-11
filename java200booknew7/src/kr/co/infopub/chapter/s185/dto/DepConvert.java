package kr.co.infopub.chapter.s185.dto;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
// DTO 리스트 -> 화면용 프로퍼티 리스트 -> JavaFX 트리뷰나 테이블뷰를 위한 리스트
public class DepConvert {
	// DTO를 화면용 프로퍼티 객체로 변환한다.
	public static Department toPro(DepartmentDto b){
		Department bp=new Department();
		bp.setDepartment_id(b.getDepartment_id());
		bp.setDepartment_name(b.getDepartment_name());
		bp.setLocation_id(b.getLocation_id());
		bp.setManager_id(b.getManager_id());
		return bp;
	}
	// DTO 리스트를 화면용 프로퍼티 객체 리스트로 변환한다.
	public static List<Department> toPro(List<DepartmentDto> blist){
		List<Department> bplists=new ArrayList<>();
		for(DepartmentDto b:blist){
			bplists.add(toPro(b));
		}
		return bplists;
	}
	// 화면용 프로퍼티 객체 리스트를 JavaFX 트리뷰나 테이블뷰를 위한 리스트로 변환한다.
	public static ObservableList<Department> toObservPro(List<Department> alists){
		ObservableList<Department> bList = FXCollections.observableArrayList(alists);
		return bList;
	}
	// DTO 리스트를 JavaFX 트리뷰나 테이블뷰를 위한 리스트로 변환한다.
	public static ObservableList<Department> toObservProFromDto(List<DepartmentDto> alists){
		return toObservPro(toPro(alists));
	}
}
