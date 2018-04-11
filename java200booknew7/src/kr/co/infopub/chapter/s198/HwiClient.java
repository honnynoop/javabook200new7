package kr.co.infopub.chapter.s198;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
public class HwiClient {
 public static void main(String[] args) {
	HwiClient hclient=new HwiClient();
	hclient.go();
 }
 
 int port =9907;
 String ip="127.0.0.1";
 public void go() {
	Socket s=null;
	BufferedReader br=null;
	PrintWriter pw=null;
	try {
		System.out.println("���� �����");
		s=new Socket(ip, port);
		br=new BufferedReader(
				new InputStreamReader(System.in));

		pw=new PrintWriter( 
				new OutputStreamWriter(
						s.getOutputStream()) ,true);
		String msg="";
		System.out.println("���̵� �Է��ϼ���");
		String name=br.readLine();
		
		//������
		CientThread ctr=new CientThread(s);
		ctr.start();
		
		while((msg=br.readLine())!=null){
			pw.println(name+":"+msg);
		}
		
	} catch (IOException e) {
		System.out.println(e);
	}finally{			
		try {
			pw.close();
			s.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
 }
}