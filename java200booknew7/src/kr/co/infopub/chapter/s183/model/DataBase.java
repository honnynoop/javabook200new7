package kr.co.infopub.chapter.s183.model;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;
public abstract class DataBase {
 public static final String DRIVER="oracle.jdbc.driver.OracleDriver";
 public static final String URLS="jdbc:oracle:thin:@127.0.0.1:1521:xe";
 public static final String USER="hr";
 public static final String PWD="hr";
 private boolean isD=true;    // false -> x/6 log���� 
 private boolean isS=true;    // false -> SQL�� ����
 public DataBase() {
	init();   // ����̹� �ε� 1/6
 }//
 private void init(){
	try {
		Class.forName(DRIVER);
		log("1/6 Driver Loading Success!!!");
	} catch (ClassNotFoundException e) {
		log("1/6 Driver Loading Fail!!!");
	}//
 }
 //------------- JDBC 2/6, 6/6
 // DB Connection JDBC 2/6
 public Connection getConnection() throws SQLException{//
	Connection conn=null;
	conn=DriverManager.getConnection(URLS, USER,PWD);
	log("2/6 Connection Success!!!");
		return conn;
 }
 // DB Close JDBC 6/6
 public void close(Connection conn, Statement stmt,ResultSet rs){
	if(rs!=null){
		try {
			rs.close();
		} catch (SQLException e) {
			
		}
	}//
	if(stmt!=null){
		try {
			stmt.close();
		} catch (SQLException e) {
			
		}
	}//
	if(conn!=null){
		try {
			conn.close();
			log("6/6 Close Success!!!");
	} catch (SQLException e) {
		log("6/6 Close Fail: ",e);
		}
	}
 }
 //----------- log
 // ����α�
 public void log(String msg){
	if(isD){
		System.out.println(this.getClass()+": "+msg);
	}
 }
 // ���ܿ� �α�
 public void log(String msg,Exception e){
	if(isD){
		System.out.println(this.getClass()+": "+msg);
		System.out.println("============>"+e);
	}
 }
 // PTS 181, 182 SQL ������, Ŭ�����̸�.�޼����̸�����
 public void log(String msg,String methodName){
  if(isD || isS){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String[] mmm=msg.split("     ");
	String ss="";
	for (int i = 0; i < mmm.length; i++) {
		if(mmm[i]!=null && !mmm[i].trim().equals("")){
		ss+= " "+mmm[i].trim()+" \n ";
		}
	}
	System.out.println("CRUD------>>>"+this.getClass()+"."+methodName+"()");
	System.out.println(ss.trim());
	System.out.println("CRUD<<<-----"+sdf.format(new Date()));
  }
 }//
 // PTS 181, 182 SQL ������, Ŭ�����̸�.�޼����̸�, �Է� ������ ����
 public void log(String msg,String methodName,Object data){
  if(isD || isS){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String[] mmm=msg.split("     ");
	String ss="";
	for (int i = 0; i < mmm.length; i++) {
		if(mmm[i]!=null && !mmm[i].trim().equals("")){
		ss+= " "+mmm[i].trim()+" \n ";
		}
	}
	System.out.println("CRUD----->>>"+this.getClass()+"."+methodName+"()");
	System.out.println(ss.trim());
	System.out.println("Data : "+data.toString());
	System.out.println("CRUD<<<-------"+sdf.format(new Date()));
  }
 }//
 //----------- quot ���� ���� -> NULL
 // hello -> 'hello', �Է°��� ������ -> NULL
 public String quots(String msg){
  String ss="";
  if(msg==null || msg.trim().equals("")|| msg.trim().equals("0")
		                           || msg.trim().equals("0.0")){
   ss=" NULL ";
  }else{
   ss="'"+msg+"'";
  }
  return ss;
 }
 // 10 -> 10, �Է°��� ������ -> NULL
 public String quoti(String msg){
   	  String ss="";
  if(msg==null || msg.trim().equals("")|| msg.trim().equals("0")
		                               || msg.trim().equals("0.0")){
   ss=" NULL ";
  }else{
   ss=""+msg+"";
  }
  return ss;
 }
 // 10.5 -> 10.5, �Է°��� ������ -> NULL
 public String quotd(String msg){
  String ss="";
  if(msg==null || msg.trim().equals("")|| msg.trim().equals("0")
		                          || msg.trim().equals("0.0")){
  ss=" NULL ";
  }else{
  ss=""+msg+"";
  }
  return ss;
 }
}