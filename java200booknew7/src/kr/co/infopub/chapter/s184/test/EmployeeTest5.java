package kr.co.infopub.chapter.s184.test;

import java.sql.SQLException;
import java.util.List;

import javafx.scene.control.TreeItem;
import kr.co.infopub.chapter.s184.dto.EmployeeDto;
import kr.co.infopub.chapter.s184.model.EmployeeDAO;
import kr.co.infopub.chapter.s184.util.EmpUtil;

public class EmployeeTest5 {
	public static void main(String[] args) {
		EmployeeDAO ddao=new EmployeeDAO();
		try {
			List<EmployeeDto> lists=ddao.findTreeManagerInEmployee();
			TreeItem<String> root = new TreeItem<String>("�Ŵ����� ����");
			int max=ddao.getTreeMaxLevel();
			makeEmpTree(root,lists,"",1,max);
			printTree(root, 1);
			
		} catch (SQLException e) {
			System.out.println( e);
		}
	}
	//��͸� �̿�
	public static void makeEmpTree(TreeItem<String> front,List<EmployeeDto> dlists, String key, int index,int max){
		if(index>max){return ; }  //max�� ������ ��
		for (EmployeeDto emp: dlists) {  //������ ������ ���� ���̴�.
			if(EmpUtil.level(emp)!=index){   //���ϴ� ������ ã��
				continue;
			}else if(index>1 && EmpUtil.level(emp)==index){   //2���ʹ� 1�� Ű���� ��
				if(EmpUtil.level(emp,index-1).equals(key)){
					TreeItem<String> aa=new TreeItem<String>(EmpUtil.tname(emp, index));
					front.getChildren().add(aa);
					makeEmpTree(aa,dlists,EmpUtil.level(emp,index),index+1,max);
				}else{
					continue;
				}
			}else if(EmpUtil.level(emp)==1){  //1�� Ű���� ���� ����
				TreeItem<String> aa=new TreeItem<String>(EmpUtil.tname(emp, 1));
				front.getChildren().add(aa);
				makeEmpTree(aa,dlists,EmpUtil.level(emp,1),2,max);
			}
		}
	}	
	public static void printTree(TreeItem<String> root , int index){
    	String tt="\t";
    	String s="";
    	for(int i=1; i<index ; i++){
    		s+=tt;
    	}
    	//System.out.println(s+""+index+" "+root.getValue());
    	System.out.println(s+""+root.getValue());
    	for(TreeItem<String> ss:root.getChildren()){
    		printTree(ss, index+1);
    	}
    }
	
}
