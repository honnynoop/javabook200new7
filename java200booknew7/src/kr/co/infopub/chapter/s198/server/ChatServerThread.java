package kr.co.infopub.chapter.s198.server;
import   java.io.*;
import java.net.Socket;
import   java.util.*;

import kr.co.infopub.chapter.s198.common.Message;
public class ChatServerThread implements Runnable{
   Vector<Message>   buffer;   
   ObjectInputStream  ois;
   ObjectOutputStream oos;
   Message  mess;
   boolean  exit;
   String   name;
   public ChatServerThread(Vector<Message> v,Socket socket){
     buffer=v;
     try {
		ois=new ObjectInputStream(socket.getInputStream());
		oos=new ObjectOutputStream(socket.getOutputStream());
	} catch (IOException e) {
		
	}
    
     exit=false;
   }
   public void run(){
     while(!exit){
       try{
            mess=(Message)ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception이 발생");
       }
       int state=mess.getState();
       if(state==Message.END){
             mess.setMessage("님이 종료하셨습니다.");
             name=mess.getName();
             broadCasting();
             for (int i = 0; i < buffer.size(); i++) {
               if ( ( (Message) buffer.get(i)).getName().equals(name)) {
                 buffer.remove(i);
               }
             }
             try {
               ois.close();
               oos.close();
             }
             catch (Exception x) {}
             exit = true;
			 System.out.println(name+"님이 종료하셨습니다.");
       }else if(state==Message.INITCONTACT){

           Vector<String> userName=new Vector<>(5,1);
           mess.setObjectStream(oos);
           buffer.add(mess);
           for(int i=0;i<buffer.size();i++){
             userName.add(((Message)buffer.get(i)).getName());
           }
           mess.setUserName(userName);
           System.out.println("broadCasting 시작");
           broadCasting();
        }else{
           broadCasting();
        }//
      }
    }
    public synchronized void broadCasting(){
       for(int i=0;i<buffer.size();i++){
          try{
           ((Message)buffer.get(i)).getObjectStream().writeObject(mess);
          }catch(IOException e){
             System.out.println("broadCasting method에서 IOException.");
           e.printStackTrace();
          }
        }
    }
}//class



