import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client{
	public static void main(String[] args){
		
		String hostname = "localhost";
		int port = 1500;
		
		Socket clientSocket = null;
		InputStream in = null;
		ObjectInputStream objectInStream = null;
		OutputStream out = null;
		ObjectOutputStream objectOutStream = null;
		
		try{
			clientSocket = new Socket(hostname, port);
			in = clientSocket.getInputStream();
			objectInStream = new ObjectInputStream(in);
			out = clientSocket.getOutputStream();
			objectOutStream = new ObjectOutputStream(out);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(objectInStream == null){
			System.out.println("error");
			return;
		}
		
		try{
			while(true){
				
				System.out.println("hello");
				LoginObject login = new LoginObject("ali");
				objectOutStream.writeObject(login);
				
				Quit q = new Quit();
				objectOutStream.writeObject(q);
				
				break;
				
			}
			
			objectOutStream.close();
			objectInStream.close();
			clientSocket.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
