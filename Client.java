import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client{
	public static void main(String[] args){
		
		String hostname = "localhost";
		int port = 1500;
		
		Socket clientSocket = null;
		DataOutputStream out = null;
		BufferedReader in = null;
		
		try{
			clientSocket = new Socket(hostname, port);
			out = new DataOutputStream(clientSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(clientSocket == null || out == null || in == null){
			System.err.println("Error: null value in socket, in, or out stream");
			return;
		}
		
		try{
			while(true){
				System.out.println("Enter integer data, 0 to stop connection, -1 to stop server");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String input = br.readLine();
				
				out.writeBytes(input + "\n");
				
				int n = Integer.parseInt(input);
				if(n == 0 || n == -1){
					break;
				}
				
				String responseLine = in.readLine();
				System.out.println("From Server: " + responseLine);
			}
			
			out.close();
			in.close();
			clientSocket.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
