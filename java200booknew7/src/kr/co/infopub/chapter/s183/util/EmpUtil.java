package kr.co.infopub.chapter.s183.util;
import kr.co.infopub.chapter.s183.dto.DepCountDto;
import kr.co.infopub.chapter.s183.dto.DepartmentDto;
public class EmpUtil {
 // (10)
 public static String q(String msg){
	return "("+msg+")";
 }
 // Administration(10)
 public static String tname(DepartmentDto ee){
	return ee.getDepartment_name()+q(ee.getDepartment_id()+"");
 }
 // Administration(10)
 public static String tname(DepCountDto ee){
	return ee.getDepartment_name()+q(ee.getDepartment_id()+"");
 }
 // /101/102/103 -> /ÀÇ °³¼ö -1 -> 2
 public static   int level(String msg){
	String [] sp=msg.split("/");
	return sp.length-1;
 }
 // /101/102/103,2 -> 102
 public static String level(String msg, int index){
	String [] sp=msg.split("/");
	return sp[index];
 }
 // helloHRD -> loHRD
 public static  String str(String msg){
	return msg.substring(3);
 }
 // helloHRD -> hel
 public  static String bfstr(String msg){
	return msg.substring(0,3);
 }
 // 100(sdfs sfsfs asdfasdf) -> 100
 public static String sname(String emp){
	return bfstr(emp);
 }
 // (100) -> 100
 public static String dep(String msg){
	String ss="";
	if(msg.indexOf("(")!=-1){
		ss=msg.substring(0, msg.indexOf("("));
	}else{
		ss=msg.trim();
	}
	return ss.trim();
 }
 // 001100(sdfs sfsfs asdfasdf)-> 100(sdfs sfsfs asdfasdf)
/* public static String tname(EmployeeDto emp, int index){
	return str(level(emp.getOrder2(),index)+name(emp));
 }
 public static int  level(EmployeeDto emp){
	return level(emp.getOrder2());
 }
 public static String level(EmployeeDto emp, int index){
	return level(emp.getOrder2(),index);
 }
 public static String name(EmployeeDto ee){
    return q(ee.getFirstName()+" "+ee.getLastName());
 }*/
}
