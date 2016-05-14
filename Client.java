import java.net.*;
import java.io.*;

public class Client{
	
	public static void main(String[] args){
		
		String serverName = "localhost";
		int port = 1000;
		
		try{
			
			Socket client = new Socket(serverName, port);
			
			DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
			outToServer.writeBytes("Hello from client");
			
			client.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
