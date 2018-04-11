package kr.co.infopub.chapter.s199.middle;
import java.io.*;
import java.net.*;
import java.util.*;
import kr.co.infopub.chapter.s199.common.DepCountDto;
import kr.co.infopub.chapter.s199.common.DepartmentDto;
import kr.co.infopub.chapter.s199.common.EmployeeDto;
import kr.co.infopub.chapter.s199.common.HRMRequest;
import java.sql.*;
public class JuryThread extends Thread {
   EmployeeService    activeDB;
   Socket             talkToMe;
   ObjectOutputStream sendStream;
   ObjectInputStream  recvStream;
   boolean            success = true;
   HRMRequest            command;
   /*
   ProtocolHandler로부터 Socket를 넘겨받아 ObjectOutput, InputStream를 연다.
   run() 메소드에서는 클라이언트에서 넘겨주는 HRMRequest객체를 받고 주도록 쓰레드를 start한다.
   */
   public JuryThread(Socket s, EmployeeService activeDB){
      talkToMe  = s;
      System.out.println(talkToMe.getInetAddress()+"이 붙음");
   this.activeDB  = activeDB;
   try{
     sendStream = new ObjectOutputStream(talkToMe.getOutputStream());
     recvStream = new ObjectInputStream(talkToMe.getInputStream());
     start();
   }catch(IOException e){ 
     System.err.println("Error- JuryThread 생성자");
         success = false;
         close();
      }
   }
   public void close(){
	   try{
           if(recvStream!=null )recvStream.close();
           if(sendStream!=null )sendStream.close();
           if(talkToMe!=null )talkToMe.close();
        }catch(IOException ioe){ }
   }
   /*
   run() 메소드에서는 클라이언트에서 넘겨주는 HRMRequest객체를 받아들여서 
   상수값으로 표현된 규약를 읽고 switch에서 각각을 판단하며 EmployeeService객체의 
   메소드를 호출한다. 그리고 그 메소드에서 발생할 exception을 catch 하면서 status에 
   -1, -2등등을 셋팅하면서 다시 클라이언트에 HRMRequest객체를 넘겨준다. 
   클라이언트에서 넘어온 HRMRequest객체중에 값을 갖고 있는 경우는 직렬화객체형태로 넘어
   오기때문에 그것을 받아서 메소드의 argument에 넣어주면서 처리하고, 클라이언트로 보낼 경우에는
  HRMRequest객체에 있는 HRMResponse객체에 add시키면서 보낸다.
   */
 public void run() {
  while (success) {
  try {
    command = (HRMRequest)recvStream.readObject();//먼저 읽고
  }catch(Exception e) {
	close();
	System.err.println ("클라이언트 연결이 끊어짐.......");
    return;
  }
  try{
	System.out.println("5 request 받음, protocol :"+command.getCommandValue());
	// HRMRequest에 있는 프로토콜에 대한 판단과 처리를 한다.
	// 클아이언트에서 요청은 프로토콜로 날아온다.
    switch(command.getCommandValue()){
	 case HRMRequest.findAllDepartments: {       // 2010 요청받음
		// DB에서 사원이 있는 모든 부서 리스트로 가져온다.
		List<DepartmentDto> cr =  activeDB.findAllDepartments();
		  if(cr.size() <= 0){
			 System.err.println("Error in findAllDepartments"); 
			 command.getResult().setStatus(-1);
		  }else{
			 // 정사적으로 요청을 처리하면 결과를 HRMRequest내부의
			 // HRMResponse객체에 저장한다. HRMResponse는 Vector
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.findTreeManagerInEmployee: {
		List<EmployeeDto> cr =  activeDB.findTreeManagerInEmployee();
		  if(cr.size() <= 0){
			 System.err.println("Error in findTreeManagerInEmployee"); 
			 command.getResult().setStatus(-2);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.findAllEmployees: {
		List<EmployeeDto> cr =  activeDB.findAllEmployees();
		  if(cr.size() <= 0){
			 System.err.println("Error in findAllEmployees"); 
			 command.getResult().setStatus(-3);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.getTreeMaxLevel: {
		int cr =  activeDB.getTreeMaxLevel();
		  if(cr <= 0){
			 System.err.println("Error in getTreeMaxLevel"); 
			 command.getResult().setStatus(-4);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.findAllDepCounts: {
		List<DepCountDto> cr =  activeDB.findAllDepCounts();
		  if(cr.size() <= 0){
			 System.err.println("Error in findAllDepCounts"); 
			 command.getResult().setStatus(-5);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.findEmployeesByDepartName: {
		String val="";
		Serializable objs=command.getRequestObject();
		if(objs!=null && objs instanceof String){
			val=(String)objs;
		}
		List<EmployeeDto> cr =  activeDB.findEmployeesByDepartName(val);
		  if(cr.size() <= 0){
			 System.err.println("Error in findEmployeesByDepartName"); 
			 command.getResult().setStatus(-6);
			 //return;
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.findEmployeesByEmpId: {
		String val="";
		Serializable objs=command.getRequestObject();
		if(objs!=null && objs instanceof String){
			val=(String)objs;
		}
		List<EmployeeDto> cr =  activeDB.findEmployeesByEmpId(val);
		  if(cr.size() <= 0){
			 System.err.println("Error in findEmployeesByEmpId"); 
			 command.getResult().setStatus(-7);
			 //return;
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.findEmployeeById: {
		String val="";
		Serializable objs=command.getRequestObject();
		if(objs!=null && objs instanceof String){
			val=(String)objs;
		}
		EmployeeDto cr =  activeDB.findEmployeeById(val);
		  if(cr==null){
			 System.err.println("Error in findEmployeeById"); 
			 command.getResult().setStatus(-8);
			 return;
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.findManagersByName: {
		String val="";
		Serializable objs=command.getRequestObject();
		if(objs!=null && objs instanceof String){
			val=(String)objs;
		}
		List<EmployeeDto> cr =  activeDB.findManagersByName(val);
		  if(cr.size() <= 0){
			 System.err.println("Error in findEmployeeById"); 
			 command.getResult().setStatus(-9);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.findAllJobs: {
		List<String> cr =  activeDB.findAllJobs();
		  if(cr.size() <= 0){
			 System.err.println("Error in findAllJobs"); 
			 command.getResult().setStatus(-10);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.findAllDepartments2: {
		List<DepartmentDto> cr =  activeDB.findAllDepartments2();
		  if(cr.size() <= 0){
			 System.err.println("Error in findAllDepartments2"); 
			 command.getResult().setStatus(-11);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.addEmployee: {
		EmployeeDto val=null;
		Serializable objs=command.getRequestObject();
		if(objs!=null && objs instanceof EmployeeDto){
			val=(EmployeeDto)objs;
		}else{
			System.err.println("Error in addEmployee"); 
			command.getResult().setStatus(-12);
			return;
		}
		int cr =  activeDB.addEmployee(val);
		  if(cr==0){
			 System.err.println("Error in addEmployee"); 
			 command.getResult().setStatus(-13);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.updateEmployee: {
		EmployeeDto val=null;
		Serializable objs=command.getRequestObject();
		if(objs!=null && objs instanceof EmployeeDto){
			val=(EmployeeDto)objs;
		}else{
			System.err.println("Error in updateEmployee"); 
			command.getResult().setStatus(-14);
			return;
		}
		boolean cr =  activeDB.updateEmployee(val);
		  if(cr==false){
			 System.err.println("Error in updateEmployee"); 
			 command.getResult().setStatus(-15);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.deleteEmployee: {
		EmployeeDto val=null;
		Serializable objs=command.getRequestObject();
		if(objs!=null && objs instanceof EmployeeDto){
			val=(EmployeeDto)objs;
		}else{
			System.err.println("Error in deleteEmployee"); 
			command.getResult().setStatus(-16);
			return;
		}
		boolean cr =  activeDB.deleteEmployee(val);
		  if(cr==false){
			 System.err.println("Error in deleteEmployee"); 
			 command.getResult().setStatus(-17);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
	case HRMRequest.getEmployeesTotal: {
		int cr =  activeDB.getEmployeesTotal();
		  if(cr <= 0){
			 System.err.println("Error in getEmployeesTotal"); 
			 command.getResult().setStatus(-18);
		  }else{
			 command.getResult().add(cr);
			 command.getResult().setStatus(0);
		  }
	    } break;
    default:
       // 잘못된 프로토콜을 받았을 때
       command.getResult().setStatus(-19);break;
    }
   }catch(Exception e){
      System.err.println ("Error in JuryThread's switch");
      // 그외 JuryThread의 switch 처리하다 발생
      command.getResult().setStatus(-20);
   }
   // 클라이언트 요청를 처리한 결과를 HRMRequest에 담아 클라이언로 재전송
   // 클라이언트로 보내는 HRMRequest에 HRMResponse가 있다 
   // 결국 HRMResponse를 클라이언트로 전송
   try {
      sendStream.writeObject(command);
      sendStream.flush();
      System.out.println("6 response 보냄: "+command.getResult().getStatus());
   }catch(Exception e){
      System.err.println("Error in writing response");
   }
   // 다른 JuryThread에게 양보(이 쓰레드는 요청을 끝냈으니)
   // 멀티 client -> 멀티 JuryThread
   Thread.yield(); 
  }
 }
}