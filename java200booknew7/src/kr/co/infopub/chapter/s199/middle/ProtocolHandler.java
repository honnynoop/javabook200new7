package kr.co.infopub.chapter.s199.middle;
import java.io.*;
import java.net.*;
public class  ProtocolHandler implements Runnable{
	int          listenPort    =  9983;
	int          alternatePort = listenPort;
	ServerSocket myServer      = null;
	JuryThread   juryThread;
	Socket       answerSocket;
	EmployeeService     db;
	public ProtocolHandler(){
        init();
    } 
   // 클라이언트의 접속을 기다릴 서버소켓을 연다.
   // 서버소켓에 사용자가 접속할 때까지 무한루프(블록킹) 기다린다.
   // 서버소켓에 사용자가 접속하면 소켓이 생성된다.
   public void init(){
      try{
    	 System.out.println( "1 서버소켓 생성..");
         myServer = new ServerSocket(listenPort);
         myServer.setReuseAddress(true) ; //ServerSocket port 바로 다시사용
         System.out.println( "2 db연결 ....");
		 db = EmployeeService.getInstance();
      }catch(IOException e){
         System.out.println(" 데이타베이스 서버에 연결할수 없습니다.");
		 close();
         System.exit(1);
      }catch(Exception io){
    	 close();
         System.exit(1);
         System.err.println("Unable to create Server Socket!");
      }
   }
   // 사용후 서버소켓을 닫는다.
   public void close(){
	   try {
		if(myServer!=null){
			myServer.close();
			System.out.println("ServerSocket id dead.");
		}
	} catch (IOException e) {
		
	}
   }
   // 서버소켓이 생성 되면 클라이언트의 요청을 기다린다.
   // 실제 클라이언트와 데이타베이스의 연동은 JuryThread가 담당한다.
   public void run(){
      while(true){
         try{
            answerSocket = myServer.accept();
            System.out.println( "3 사용자 접속으로 소켓생성 ......");
         }catch(IOException io){
            System.err.println(io.getMessage());
            return;
         }
         System.out.println( "4 배심원 쓰레드 작동 - 요구사항 파악준비 ........");
         juryThread = new JuryThread(answerSocket, db);
         Thread.yield();  // 핸들러 쓰레드 -> JuryThread에게 양보
      }
   }
   // 서버소켓을 열고 클라이언트의 접속을 기다린다.
   // 실제 DB와 연동은 JuryThread가 하도록한다.
   public static void main(String[] args){
      ProtocolHandler myHandler = new ProtocolHandler();
      new Thread(myHandler).start();
      System.out.println("ProtocolHandler server ready..........");
   }
}