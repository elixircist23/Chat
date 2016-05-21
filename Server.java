import java.net.*;
import java.io.*;
import java.util.ArrayList;

//SERVER KEEPS LIST OF THREADS, ID'S THEM AND IF CLIENT QUITS, IT SENDS QUIT OBJECT
//AND SERVER ERASES IT FROM LIST.

public class Server{
	
	ArrayList<Thread> clients = new ArrayList<Thread>();
	
	public static void main(String[] args) throws Exception{
		int port = 1500;
		Server serverSocket = new Server(port);
		serverSocket.StartServer();
		
		System.out.println("Listening on port " + port);
	}
	
	ServerSocket server = null;
	Socket clientSocket = null;
	int port;
	
	public Server(int port){
		this.port = port;
	}
	
	public void stopServer(){
		System.out.println("Server shutting down");
		System.exit(0);
	}
	
	public void StartServer(){
		try{
			server = new ServerSocket(port);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println("Server Started\nWaiting for connection...");
		
		while(true){
			try{
				System.out.println("Waiting to make client thread...");
				clientSocket = server.accept();
				ClientWorker clientThread = new ClientWorker(clientSocket, this);
				clientThread.run();
				clients.add(clientThread);
				for(int i = 0; i < clients.size() ; i++){
					System.out.println(clients.get(i));
				}
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}

class ClientWorker extends Thread{
	
	Server server;
	Socket clientSocket;
	InputStream in = null;
	ObjectInputStream objectInStream = null;
	OutputStream out = null;
	ObjectOutputStream objectOutStream = null;
	
	
	public ClientWorker(Socket clientSocket, Server server){
		this.clientSocket = clientSocket;
		this.server = server;
		
		System.out.println("Connected to address: " + clientSocket.getInetAddress().getHostAddress());
		
		try{
			in = clientSocket.getInputStream();
			out = clientSocket.getOutputStream();
			
			System.out.println("Created Streams");
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		System.out.println("Running Thread");
				
		try{
			boolean serverStop = false;
			
			while(true){
				
				
				System.out.println("in server loop");
	
				objectInStream = new ObjectInputStream(in);
				Object o = objectInStream.readObject();	
				System.out.println(o.getClass().getName());
				
				if(o.getClass().getName().equals("Quit")){
					break;
				}
				
				objectOutStream = new ObjectOutputStream(out);
				Username u = new Username("eli");
				objectOutStream.writeObject(u);
				
			}
			
			System.out.println("Connection ended");
			in.close();
			objectInStream.close();
			out.close();
			objectOutStream.close();
			clientSocket.close();
			
			if(serverStop) server.stopServer();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static String getClassName(Object o){
		System.out.println("Getting object name");
		return(o.getClass().getName().toString());
	}
}
