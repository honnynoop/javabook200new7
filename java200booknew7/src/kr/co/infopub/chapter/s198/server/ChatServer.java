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
        System.out.println("���� �غ���");
        serverSocket=new ServerSocket(5200);
		serverSocket.setReuseAddress(true) ;//ServerSocket port �ٷ� �ٽû��
        }catch(IOException e){
        System.out.println("���� �غ��߿� IOException �߻�.");
     }
    while(true){
      try{
    	   Socket socket=serverSocket.accept();
    	   String ip=socket.getInetAddress().toString();
           System.out.println(socket.getInetAddress()+"�� �پ����ϴ�.");
           System.out.println(ip+".");
         
           Thread t=new Thread(new ChatServerThread(room,socket));
           t.start();
         }catch(IOException e){
           System.out.println("IOException�� �߻��߽��ϴ�.");
      }
    }
  }
}

