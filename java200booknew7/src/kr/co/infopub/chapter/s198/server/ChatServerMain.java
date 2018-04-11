package kr.co.infopub.chapter.s198.server;
public class ChatServerMain {
	public static void main(String args[]){
		ChatServer cs=new ChatServer();
		cs.setPort(5200);
		cs.service();
	}
	
}
