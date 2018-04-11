package kr.co.infopub.chapter.s185.dto;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
// DTO ����Ʈ -> ȭ��� ������Ƽ ����Ʈ -> JavaFX Ʈ���䳪 ���̺�並 ���� ����Ʈ
public class DepConvert {
	// DTO�� ȭ��� ������Ƽ ��ü�� ��ȯ�Ѵ�.
	public static Department toPro(DepartmentDto b){
		Department bp=new Department();
		bp.setDepartment_id(b.getDepartment_id());
		bp.setDepartment_name(b.getDepartment_name());
		bp.setLocation_id(b.getLocation_id());
		bp.setManager_id(b.getManager_id());
		return bp;
	}
	// DTO ����Ʈ�� ȭ��� ������Ƽ ��ü ����Ʈ�� ��ȯ�Ѵ�.
	public static List<Department> toPro(List<DepartmentDto> blist){
		List<Department> bplists=new ArrayList<>();
		for(DepartmentDto b:blist){
			bplists.add(toPro(b));
		}
		return bplists;
	}
	// ȭ��� ������Ƽ ��ü ����Ʈ�� JavaFX Ʈ���䳪 ���̺�並 ���� ����Ʈ�� ��ȯ�Ѵ�.
	public static ObservableList<Department> toObservPro(List<Department> alists){
		ObservableList<Department> bList = FXCollections.observableArrayList(alists);
		return bList;
	}
	// DTO ����Ʈ�� JavaFX Ʈ���䳪 ���̺�並 ���� ����Ʈ�� ��ȯ�Ѵ�.
	public static ObservableList<Department> toObservProFromDto(List<DepartmentDto> alists){
		return toObservPro(toPro(alists));
	}
}
