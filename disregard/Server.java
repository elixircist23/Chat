import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class Server{
	
	private static ArrayList<ClientWorker> clients = new ArrayList<ClientWorker>();
	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	
	public static void main(String[] args){
		int port = 1500;
		
		try{
			serverSocket = new ServerSocket(port);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println("Server socket created");
		
		while(true){
			try{
				clientSocket = serverSocket.accept();
				System.out.println("Connected to client: " + clientSocket.getInetAddress().getHostAddress());
				
				for(int i = 0; i < clients.size() + 1; i++){
					ClientWorker clientThread = new ClientWorker(clientSocket, clients);
					clientThread.start();
					System.out.println("Started client thread");
					clients.add(clientThread);
					break;
				}
				
			}catch(IOException e){
				e.printStackTrace();
			}	
		}
	}
}

class ClientWorker extends Thread{
	
	private Socket clientSocket;
	private ArrayList<ClientWorker> clients;
	private InputStream in = null;
	private ObjectInputStream objectInStream = null;
	private OutputStream out = null;
	private ObjectOutputStream objectOutStream = null;
	
	public ClientWorker(Socket clientSocket, ArrayList<ClientWorker> clients){
		this.clientSocket = clientSocket;
		this.clients = clients;
		
		System.out.println("Connected to address: " + clientSocket.getInetAddress().getHostAddress());
		
	}
	
	public void run(){
		
		ArrayList<ClientWorker> clients = this.clients;
		
		for(int i = 0; i < clients.size(); i++){
			System.out.println(clients.get(i));
		}
		
		try{			
			
			in = clientSocket.getInputStream();
			out = clientSocket.getOutputStream();
			objectInStream = new ObjectInputStream(in);
			objectOutStream = new ObjectOutputStream(out);
			
			objectOutStream.writeObject(new TempMessage("hello from server"));
			String type = objectInStream.readObject().getClass().getName();
			System.out.println(type);
			
			while(true){
				String line = objectInStream.readObject().getClass().getName();
				if(line.equals("Quit")){
					break;
				}
				
				for(int i = 0; i < clients.size(); i++){
					clients.get(i).objectOutStream.writeObject(new TempMessage("ok"));
				}
			}
			
			for(int i = 0; i < clients.size(); i++){
				clients.get(i).objectOutStream.writeObject(new TempMessage("bye"));
			}
			
			objectOutStream.writeObject(new TempMessage("bye #2"));
			
			for(int i = 0; i < clients.size(); i++){
				if(clients.get(i) == this){
					clients.remove(i);
				}
			}
			
			out.close();
			in.close();
			objectInStream.close();
			objectOutStream.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
