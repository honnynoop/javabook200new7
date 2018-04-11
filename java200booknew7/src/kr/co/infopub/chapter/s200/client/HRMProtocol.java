package kr.co.infopub.chapter.s200.client;
import java.util.*;
import kr.co.infopub.chapter.s199.common.DepCountDto;
import kr.co.infopub.chapter.s199.common.DepartmentDto;
import kr.co.infopub.chapter.s199.common.EmployeeDto;
import kr.co.infopub.chapter.s199.common.HRMRequest;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
public class HRMProtocol{
 private static int         MIDDLE_TIER_PORT = 9983;
 private HRMRequest         cmndObj;
 private Socket             handler;
 private ObjectOutputStream oos;
 private ObjectInputStream  ois;
 // 생성자로 서버에 연결
 // 객체직렬화가 가능한 ObjectOutputStream, ObjectInputStream를 생성
 public HRMProtocol (String server) throws IOException {
  try{
     handler = new Socket(server, MIDDLE_TIER_PORT);
     oos     = new ObjectOutputStream (handler.getOutputStream());
     ois     = new ObjectInputStream (handler.getInputStream());
     System.out.println("1 HRMProtocol 소켓과 스트림 생성..."+ new Date());
  }catch (IOException e) {
     System.err.println ("Error in HRMProtocol 소켓과 스트림 생성...");
     throw e;
  }
 }
 // 열려있는 Socket를 닫는 메소드
 public void close() throws IOException {
   if(oos!=null)oos.close();
   if(ois!=null)ois.close();
   if(handler!=null)handler.close();
 }
 // 서버의 protocol handler로부터 응답을 받음
 // HRMRequest내부에 HRMResponse가 있다
 public int getResponse () {
  try {
     cmndObj = (HRMRequest)ois.readObject();
     System.out.println("2 HRMProtocol HRMRequest 받기 ...");
  }catch(ClassNotFoundException e){
     System.err.println("HRMRequest를 못찾음...");
  }catch(InvalidClassException e){
     System.err.println("직렬화가 잘못됨(직렬화,Object Graph 확인)...");
  }catch(StreamCorruptedException e){
     System.err.println("Stream에 이상...");
  }catch(IOException e){
     System.err.println("IO 예외발생...");
  }catch(Exception e){
     System.err.println("그 외 예외가 발생함...");
  }
  int status = cmndObj.getResult().getStatus();
  //응답이 음수면 예외알려줌
  if (status < 0) System.err.println ("응답이 음수임..." + status);
      return (status);
 } 
 // HRMRequest 객체를 미들서버로 보냄
 private void writeCommand (HRMRequest commandObj) {
  try {        
     oos.writeObject (commandObj);
     oos.flush();
  }catch(InvalidClassException e){
     System.err.println("잘못된 캐스팅문제: Serializable -> DTO ");
  }catch(NotSerializableException e){
     System.err.println("질력화 문제(직렬화, Object Graph)");
  }catch(IOException e){
     System.err.println("Stream 통신문제");
  }
 }
 // HRMRequest에 요청 프로토콜을 넣고 미들서버로 보냄
 public void sendCommand(int command){
  System.err.println("Sending: " + command);
  cmndObj = new HRMRequest(command);
  writeCommand(cmndObj);
 }
 // HRMRequest에 요청 프로토콜을 넣고 미들서버로 보냄, 아규먼트 1개
 public void sendCommand(int command, Serializable requestObject){ 
  System.out.println("Sending: " + command + " " + requestObject);
  cmndObj = new HRMRequest(command);
  cmndObj.setRequestObject(requestObject);
  writeCommand(cmndObj);
 }
 // sendCommand를 이용하여 서버에 요청보내고 결과 받기
 public List<DepartmentDto> findAllDepartments () throws SQLException{
  sendCommand(HRMRequest.findAllDepartments);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findAllDepartments");
     throw new SQLException("Protocol error: findAllDepartments");
  }
  return (List<DepartmentDto>)cmndObj.getResult().get(0);
 }
 public  List<EmployeeDto> findAllEmployees ()  throws SQLException{
  sendCommand(HRMRequest.findAllEmployees);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findAllEmployees");
     throw new SQLException("Protocol error: findAllEmployees");
  }
  return (List<EmployeeDto>)cmndObj.getResult().get(0);
 }
 public  List<EmployeeDto> findTreeManagerInEmployee ()  throws SQLException{
  sendCommand(HRMRequest.findTreeManagerInEmployee);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findTreeManagerInEmployee");
     throw new SQLException("Protocol error: findTreeManagerInEmployee");
  }
  return (List<EmployeeDto>)cmndObj.getResult().get(0);
 }
 public  int getTreeMaxLevel () throws SQLException {
  sendCommand(HRMRequest.getTreeMaxLevel);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: getTreeMaxLevel");
     throw new SQLException("Protocol error: getTreeMaxLevel");
  }
  return (int)cmndObj.getResult().get(0);
 }
 public  List<DepCountDto> findAllDepCounts () throws SQLException{
  sendCommand(HRMRequest.findAllDepCounts);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findAllDepCounts");
     throw new SQLException("Protocol error: findAllDepCounts");
  }
  return (List<DepCountDto>)cmndObj.getResult().get(0);
 }
 public  List<EmployeeDto>
   findEmployeesByDepartName(String val) throws SQLException{
  sendCommand(HRMRequest.findEmployeesByDepartName, val);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findEmployeesByDepartName");
     throw new SQLException("Protocol error: findEmployeesByDepartName");
  }
  return (List<EmployeeDto>)cmndObj.getResult().get(0);
 }
 public List<EmployeeDto>
          findEmployeesByEmpId(String val) throws SQLException{
  sendCommand(HRMRequest.findEmployeesByEmpId,val);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findEmployeesByEmpId");
     throw new SQLException("Protocol error: findEmployeesByEmpId");
  }
  return (List<EmployeeDto>)cmndObj.getResult().get(0);
 }
 public EmployeeDto findEmployeeById(String val) throws SQLException{
  sendCommand(HRMRequest.findEmployeeById,val);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findEmployeeById");
     throw new SQLException("Protocol error: findEmployeeById");
  }
  return (EmployeeDto)cmndObj.getResult().get(0);
 }
 public List<EmployeeDto>
     findManagersByName(String searchname) throws SQLException{
  sendCommand(HRMRequest.findManagersByName,searchname);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findManagersByName");
     throw new SQLException("Protocol error: findManagersByName");
  }
  return (List<EmployeeDto>)cmndObj.getResult().get(0);
 }
 public List<String> findAllJobs() throws SQLException{
  sendCommand(HRMRequest.findAllJobs);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findAllJobs");
     throw new SQLException("Protocol error: findAllJobs");
  }
  return (List<String>)cmndObj.getResult().get(0);
 }
 public List<DepartmentDto>
                    findAllDepartments2() throws SQLException{
  sendCommand(HRMRequest.findAllDepartments2);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: findAllDepartments2");
     throw new SQLException("Protocol error: findAllDepartments2");
  }
  return (List<DepartmentDto>)cmndObj.getResult().get(0);
 }
 public int addEmployee(EmployeeDto empdto) throws SQLException{
  sendCommand(HRMRequest.addEmployee,empdto);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: addEmployee");
     throw new SQLException("Protocol error: addEmployee");
  }
  return (int)cmndObj.getResult().get(0);
 }
 public boolean updateEmployee(EmployeeDto emp) throws SQLException{
  sendCommand(HRMRequest.updateEmployee, emp);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: updateEmployee");
     throw new SQLException("Protocol error: updateEmployee");
  }
  return (boolean)cmndObj.getResult().get(0);
 }
 public boolean deleteEmployee(EmployeeDto emp) throws SQLException{
  sendCommand(HRMRequest.deleteEmployee,emp);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: deleteEmployee");
     throw new SQLException("Protocol error: deleteEmployee");
  }
  return (boolean)cmndObj.getResult().get(0);
 }
 public int getEmployeesTotal() throws SQLException{
  sendCommand(HRMRequest.getEmployeesTotal);
  int response = getResponse();
  if (response < 0){
     System.err.println("Protocol error: getEmployeesTotal");
     throw new SQLException("Protocol error: getEmployeesTotal");
  }
  return (int)cmndObj.getResult().get(0);
 }
}