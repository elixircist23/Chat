import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ChatServer {

	public static ArrayList<ClientThread> threads = new ArrayList<ClientThread>();
	public static ServerSocket serverSocket = null;
	public static Socket clientSocket = null;
	
	public static void main(String[] args){
		
		try{
			serverSocket = new ServerSocket(1500);
		}catch(Exception e){e.printStackTrace();}
		
		System.out.println("Server Socket Created");
	
		System.out.println("Waiting for connection...");
		
		//always accepting new client connections
		while(true){
			
			try{
				clientSocket = serverSocket.accept();
			}catch(Exception e){e.printStackTrace();}
			
			System.out.println("Accepted socket");
			
			//create new client thread, start the thread, and add it to the threads list
			ClientThread thread = new ClientThread(clientSocket, threads);
			thread.start();
			threads.add(thread);
		}
		
	}
	
}

//this class creates a thread to read THEN write to clients
class ClientThread extends Thread{
	
	public Socket clientSocket = null;
	public ArrayList<ClientThread> threads = null;
	public DataInputStream in = null;
	public DataOutputStream out = null;
	public ObjectInputStream ois = null;
	public ObjectOutputStream oos = null;
	
	public ClientThread(Socket clientSocket, ArrayList<ClientThread> threads){
		this.clientSocket = clientSocket;
		this.threads = threads;
	}
	
	public void run(){
		
		System.out.println("Running thread");
		
		ArrayList<ClientThread> threads = this.threads;
		
		try{
		
			in = new DataInputStream(clientSocket.getInputStream());	
			ois = new ObjectInputStream(in);
			out = new DataOutputStream(clientSocket.getOutputStream());
			oos = new ObjectOutputStream(out);
			
			
			while(true){
				
				//USE TO READ STUFF
				
				Object input = ois.readObject();
				
				if(input.getClass().getName().equals("TempMessage")){
					TempMessage msg = (TempMessage) input;
					System.out.println("CLIENT: " + msg.getMessage());
				}
								
				if(input.getClass().getName().equals(("Quit"))){
					break;
				}
				
				//USE TO WRITE STUFF
				
				for(int i = 0; i < threads.size(); i++){
					
					threads.get(i).oos.writeObject(input);
					
				}
				
			}
			
			in.close();
			ois.close();
			out.close();
			oos.close();
			clientSocket.close();
		
		}catch(Exception e){e.printStackTrace();}
	
	}
	
}
