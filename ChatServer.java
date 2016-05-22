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
	public DataInputStream in = null;
	public DataOutputStream out = null;
	public ObjectInputStream ois = null;
	public ObjectOutputStream oos = null;
	
	public ClientThread(Socket clientSocket){
		this.clientSocket = clientSocket;
	}
	
	public void run(){
		
		System.out.println("Running thread");
		
		try{
		
			in = new DataInputStream(clientSocket.getInputStream());	
			ois = new ObjectInputStream(in);
			out = new DataOutputStream(clientSocket.getOutputStream());
			oos = new ObjectOutputStream(out);
			
			
			while(true){
				
				Object input = ois.readObject();
				
				if(input.getClass().getName().equals("TempMessage")){
					TempMessage msg = (TempMessage) input;
					System.out.println("CLIENT: " + msg.getMessage());
				}
								
				if(input.getClass().getName().equals(("Quit"))){
					break;
				}
				
				oos.writeObject(new TempMessage("hello"));
				
			}
			
			in.close();
			ois.close();
			out.close();
			oos.close();
			clientSocket.close();
		
		}catch(Exception e){e.printStackTrace();}
	
	}
	
}
