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
   ProtocolHandler�κ��� Socket�� �Ѱܹ޾� ObjectOutput, InputStream�� ����.
   run() �޼ҵ忡���� Ŭ���̾�Ʈ���� �Ѱ��ִ� HRMRequest��ü�� �ް� �ֵ��� �����带 start�Ѵ�.
   */
   public JuryThread(Socket s, EmployeeService activeDB){
      talkToMe  = s;
      System.out.println(talkToMe.getInetAddress()+"�� ����");
   this.activeDB  = activeDB;
   try{
     sendStream = new ObjectOutputStream(talkToMe.getOutputStream());
     recvStream = new ObjectInputStream(talkToMe.getInputStream());
     start();
   }catch(IOException e){ 
     System.err.println("Error- JuryThread ������");
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
   run() �޼ҵ忡���� Ŭ���̾�Ʈ���� �Ѱ��ִ� HRMRequest��ü�� �޾Ƶ鿩�� 
   ��������� ǥ���� �Ծฦ �а� switch���� ������ �Ǵ��ϸ� EmployeeService��ü�� 
   �޼ҵ带 ȣ���Ѵ�. �׸��� �� �޼ҵ忡�� �߻��� exception�� catch �ϸ鼭 status�� 
   -1, -2����� �����ϸ鼭 �ٽ� Ŭ���̾�Ʈ�� HRMRequest��ü�� �Ѱ��ش�. 
   Ŭ���̾�Ʈ���� �Ѿ�� HRMRequest��ü�߿� ���� ���� �ִ� ���� ����ȭ��ü���·� �Ѿ�
   ���⶧���� �װ��� �޾Ƽ� �޼ҵ��� argument�� �־��ָ鼭 ó���ϰ�, Ŭ���̾�Ʈ�� ���� ��쿡��
  HRMRequest��ü�� �ִ� HRMResponse��ü�� add��Ű�鼭 ������.
   */
 public void run() {
  while (success) {
  try {
    command = (HRMRequest)recvStream.readObject();//���� �а�
  }catch(Exception e) {
	close();
	System.err.println ("Ŭ���̾�Ʈ ������ ������.......");
    return;
  }
  try{
	System.out.println("5 request ����, protocol :"+command.getCommandValue());
	// HRMRequest�� �ִ� �������ݿ� ���� �Ǵܰ� ó���� �Ѵ�.
	// Ŭ���̾�Ʈ���� ��û�� �������ݷ� ���ƿ´�.
    switch(command.getCommandValue()){
	 case HRMRequest.findAllDepartments: {       // 2010 ��û����
		// DB���� ����� �ִ� ��� �μ� ����Ʈ�� �����´�.
		List<DepartmentDto> cr =  activeDB.findAllDepartments();
		  if(cr.size() <= 0){
			 System.err.println("Error in findAllDepartments"); 
			 command.getResult().setStatus(-1);
		  }else{
			 // ���������� ��û�� ó���ϸ� ����� HRMRequest������
			 // HRMResponse��ü�� �����Ѵ�. HRMResponse�� Vector
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
       // �߸��� ���������� �޾��� ��
       command.getResult().setStatus(-19);break;
    }
   }catch(Exception e){
      System.err.println ("Error in JuryThread's switch");
      // �׿� JuryThread�� switch ó���ϴ� �߻�
      command.getResult().setStatus(-20);
   }
   // Ŭ���̾�Ʈ ��û�� ó���� ����� HRMRequest�� ��� Ŭ���̾�� ������
   // Ŭ���̾�Ʈ�� ������ HRMRequest�� HRMResponse�� �ִ� 
   // �ᱹ HRMResponse�� Ŭ���̾�Ʈ�� ����
   try {
      sendStream.writeObject(command);
      sendStream.flush();
      System.out.println("6 response ����: "+command.getResult().getStatus());
   }catch(Exception e){
      System.err.println("Error in writing response");
   }
   // �ٸ� JuryThread���� �纸(�� ������� ��û�� ��������)
   // ��Ƽ client -> ��Ƽ JuryThread
   Thread.yield(); 
  }
 }
}