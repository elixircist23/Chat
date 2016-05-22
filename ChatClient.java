import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatClient {
	
	public static Socket clientSocket = null;
	public static PrintStream out = null;
	public static Scanner stdin = new Scanner(System.in);
	public static boolean closed = false;

	public static void main(String[] args) {
		
		try{
			
			clientSocket = new Socket("localhost", 1500);
			out = new PrintStream(clientSocket.getOutputStream());
			
		}catch(Exception e){e.printStackTrace();}
		
		System.out.println("Connected to host");
		
		if(clientSocket != null && out != null){
			
			try{
						
				while(!closed){
				
					String input = stdin.next();
				
					out.println(input);
				
					if(input.equals("quit")){
						break;
					}
				
				}
				
				out.close();
				clientSocket.close();
			
			}catch(Exception e){e.printStackTrace();}
			
		}
		
	}

}
