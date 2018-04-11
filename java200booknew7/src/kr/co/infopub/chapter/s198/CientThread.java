package kr.co.infopub.chapter.s198;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
public class CientThread extends Thread{
	private Socket s;
	BufferedReader sbr=null;
	public CientThread(Socket s) {
		this.s = s;
		try {
			sbr=new BufferedReader(
					new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	@Override
	public void run() {
		String str="";
		try{
			while ( ( str = sbr.readLine() ) != null){
				System.out.println(str);
			}
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
}
