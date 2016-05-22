import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client{
	public static void main(String[] args){
		
		String hostname = "192.168.1.7";
		//String hostname = "localhost";
		int port = 1500;
		
		Socket clientSocket = null;
		InputStream in = null;
		ObjectInputStream objectInStream = null;
		OutputStream out = null;
		ObjectOutputStream objectOutStream = null;
		
		try{
			clientSocket = new Socket(hostname, port);
			in = clientSocket.getInputStream();
			out = clientSocket.getOutputStream();
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		try{
			while(true){
				
				System.out.println("in client loop");
				
				objectOutStream = new ObjectOutputStream(out);
				
				Scanner stdin = new Scanner(System.in);
				String userIn = stdin.next();
				
				switch(userIn){
					case "m":
						TempMessage tmp = new TempMessage("hello");
						objectOutStream.writeObject(tmp);
						break;
				
					case "q":
						Quit q = new Quit();
						objectOutStream.writeObject(q);
						break;
				}
				
				if(userIn.equals("q")){
					break;
				}
				
				
				objectInStream = new ObjectInputStream(in);
				System.out.println(objectInStream.readObject());
							
				
			}
			
			//in.close();
			//out.close();
			//objectInStream.close();
			//objectOutStream.close();
			//clientSocket.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
