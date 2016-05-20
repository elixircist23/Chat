import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client{

	
	
	public static void main(String[] args){
		
		try{
			Socket clientSocket = new Socket("localhost", 1000);

			OutputStream outStream = clientSocket.getOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(outStream);
			
			Username username = new Username("ali");
			
			objectStream.writeObject(username);
			
			clientSocket.close();
			outStream.close();
			objectStream.close();
			
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
