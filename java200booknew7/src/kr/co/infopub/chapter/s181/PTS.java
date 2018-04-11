package kr.co.infopub.chapter.s181;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class PTS {
 // java.sql.Date -> "yyyy. MM. dd" 
 public static String toSDate(Date msg){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy. MM. dd");
	return sdf.format(msg);
 }
 // "yyyy. MM. dd" -> java.sql.Date
 public static Date toDaeS(String msg){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy. MM. dd");
	java.util.Date ud=new java.util.Date();
	try {
		ud=sdf.parse(msg);
	} catch (ParseException e) {
	}
   return new Date(ud.getTime());
 }
 // java.sql.Date -> "yyyy-MM-dd" 
 public static String toSDate2(Date msg){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	return sdf.format(msg);
 }
 // java.util.Date -> "yyyy-MM-dd" 
 public static String toDate3(java.util.Date msg){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	return sdf.format(msg);
 }
 // today -> "yyyy-MM-dd" 
 public static String toDay(){
	return toDate3(new java.util.Date());
 }
 // "yyyy-MM-dd" -> java.sql.Date
 public static Date toDaeS2(String msg){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date ud=new java.util.Date();
	try {
		ud=sdf.parse(msg);
	} catch (ParseException e) {
		
	}
   return new Date(ud.getTime());
 }
 // "     " -> \n 10칸 공백 -> 다음 줄
 public static  void log(String msg){
	String[] mmm=msg.split("     ");
	String ss="";
	for (int i = 0; i < mmm.length; i++) {
		if(mmm[i]!=null && !mmm[i].trim().equals("")){
			ss+= ""+mmm[i].trim()+" \n ";
		}
	}
	System.out.println("--------------");
	System.out.println(ss.trim());
	System.out.println("-------------");
 }//
 // id=? -> id='101'
 public static String  log2(String msg, String id){
	String sms=msg.replace("?", "%s");
	String ss=String.format(sms, "'"+id+"'");
	return ss;
 }
 public static void main(String[] args) {
	Date s=toDaeS("2017. 5. 12");
	String t=toSDate2(s);
	System.out.println(t);
    String SQL = ""
    		+" SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME,                "
    		+" EMAIL,PHONE_NUMBER,HIRE_DATE FROM EMPLOYEES               "
    		+" START WITH  EMPLOYEE_ID =?                                "
    		+" CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID";
	log(SQL);
	log(log2(SQL,"100"));
 }
}
