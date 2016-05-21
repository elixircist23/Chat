import java.net.*;
import java.io.*;

//SERVER KEEPS LIST OF THREADS, ID'S THEM AND IF CLIENT QUITS, IT SENDS QUIT OBJECT
//AND SERVER ERASES IT FROM LIST.

public class Server{
	
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
	//OutputStream out = null;
	//ObjectOutputStream objectOutStream = null;
	
	
	public ClientWorker(Socket clientSocket, Server server){
		this.clientSocket = clientSocket;
		this.server = server;
		
		System.out.println("Connected to address: " + clientSocket.getInetAddress().getHostAddress());
		
		try{
			in = clientSocket.getInputStream();
			objectInStream = new ObjectInputStream(in);
			//out = clientSocket.getOutputStream();
			//objectOutStream = new ObjectOutputStream(out);
			
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
				Object o = objectInStream.readObject();
				System.out.println(o.getClass().getName());

				if(o.getClass().getName().equals("Quit")){
					break;
				}
				
			}
			
			System.out.println("Connection ended");
			objectInStream.close();
			//out.close();
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
