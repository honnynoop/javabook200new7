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
   // Ŭ���̾�Ʈ�� ������ ��ٸ� ���������� ����.
   // �������Ͽ� ����ڰ� ������ ������ ���ѷ���(���ŷ) ��ٸ���.
   // �������Ͽ� ����ڰ� �����ϸ� ������ �����ȴ�.
   public void init(){
      try{
    	 System.out.println( "1 �������� ����..");
         myServer = new ServerSocket(listenPort);
         myServer.setReuseAddress(true) ; //ServerSocket port �ٷ� �ٽû��
         System.out.println( "2 db���� ....");
		 db = EmployeeService.getInstance();
      }catch(IOException e){
         System.out.println(" ����Ÿ���̽� ������ �����Ҽ� �����ϴ�.");
		 close();
         System.exit(1);
      }catch(Exception io){
    	 close();
         System.exit(1);
         System.err.println("Unable to create Server Socket!");
      }
   }
   // ����� ���������� �ݴ´�.
   public void close(){
	   try {
		if(myServer!=null){
			myServer.close();
			System.out.println("ServerSocket id dead.");
		}
	} catch (IOException e) {
		
	}
   }
   // ���������� ���� �Ǹ� Ŭ���̾�Ʈ�� ��û�� ��ٸ���.
   // ���� Ŭ���̾�Ʈ�� ����Ÿ���̽��� ������ JuryThread�� ����Ѵ�.
   public void run(){
      while(true){
         try{
            answerSocket = myServer.accept();
            System.out.println( "3 ����� �������� ���ϻ��� ......");
         }catch(IOException io){
            System.err.println(io.getMessage());
            return;
         }
         System.out.println( "4 ��ɿ� ������ �۵� - �䱸���� �ľ��غ� ........");
         juryThread = new JuryThread(answerSocket, db);
         Thread.yield();  // �ڵ鷯 ������ -> JuryThread���� �纸
      }
   }
   // ���������� ���� Ŭ���̾�Ʈ�� ������ ��ٸ���.
   // ���� DB�� ������ JuryThread�� �ϵ����Ѵ�.
   public static void main(String[] args){
      ProtocolHandler myHandler = new ProtocolHandler();
      new Thread(myHandler).start();
      System.out.println("ProtocolHandler server ready..........");
   }
}