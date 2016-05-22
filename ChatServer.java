import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ChatServer {

	//public static ArrayList<ClientThread> threads = new ArrayList<ClientThreads>();
	public static ServerSocket serverSocket = null;
	public static Socket clientSocket = null;
	
	public static void main(String[] args){
		
		try{
			serverSocket = new ServerSocket(1500);
		}catch(Exception e){e.printStackTrace();}
		
		System.out.println("Server Socket Created");
	
		System.out.println("Waiting for connection...");
		
		while(true){
			
			try{
				clientSocket = serverSocket.accept();
			}catch(Exception e){e.printStackTrace();}
			
			System.out.println("Accepted socket");
			
			ClientThread thread = new ClientThread(clientSocket);
			thread.start();
			
		}
		
	}
	
}

class ClientThread extends Thread{
	
	public Socket clientSocket = null;
	public BufferedReader in = null;
	
	public ClientThread(Socket clientSocket){
		this.clientSocket = clientSocket;
	}
	
	public void run(){
		
		System.out.println("Running thread");
		
		try{
		
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						
			while(true){
				
				String input = in.readLine();
				System.out.println("CLIENT: " + input);
				
				if(input.equals("quit")){
					break;
				}
				
			}
			
			in.close();
			clientSocket.close();
		
		}catch(Exception e){e.printStackTrace();}
	
	}
	
}
