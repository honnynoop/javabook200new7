package kr.co.infopub.chapter.s198.server;
import  java.io.*;
import  java.net.*;
import  java.util.*;
import kr.co.infopub.chapter.s198.common.Message;
public class ChatServer{
   int port =5200;
   public void setPort(int port) {
	this.port = port;
   }
   Vector<Message> room=new Vector<Message>(5,3);
   ServerSocket			serverSocket;
   public void service(){
	   System.out.println("start Server service...");
     try{
        System.out.println("접속 준비중");
        serverSocket=new ServerSocket(5200);
		serverSocket.setReuseAddress(true) ;//ServerSocket port 바로 다시사용
        }catch(IOException e){
        System.out.println("서비스 준비중에 IOException 발생.");
     }
    while(true){
      try{
    	   Socket socket=serverSocket.accept();
    	   String ip=socket.getInetAddress().toString();
           System.out.println(socket.getInetAddress()+"가 붙었습니다.");
           System.out.println(ip+".");
         
           Thread t=new Thread(new ChatServerThread(room,socket));
           t.start();
         }catch(IOException e){
           System.out.println("IOException이 발생했습니다.");
      }
    }
  }
}

