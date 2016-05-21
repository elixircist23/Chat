import java.net.*;
import java.io.*;

//SERVER KEEPS LIST OF THREADS, ID'S THEM AND IF CLIENT QUITS, IT SENDS QUIT OBJECT
//AND SERVER ERASES IT FROM LIST.

public class Server{
	
	public static void main(String[] args) throws Exception{
		int port = 1500;
		Server serverSocket = new Server(port);
		serverSocket.StartServer();
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
		
		System.out.println("Waiting for connection");
		
		while(true){
			try{
				clientSocket = server.accept();
				ClientWorker clientThread = new ClientWorker(clientSocket, this);
				clientThread.run();
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static String getClassName(Object o){
		return(o.getClass().getName().toString());
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
			objectInStream = new ObjectInputStream(in);
			out = clientSocket.getOutputStream();
			objectOutStream = new ObjectOutputStream(out);
			
			System.out.println(in.getClass().getName());
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		String line;
		
		try{
			boolean serverStop = false;
			
			while(true){
				Object o = objectInStream.readObject();
				String objectName = Server.getClassName(o);
				objectOutStream.writeObject(new LoginObject("ali"));
				
				if(objectName.equals("Quit")){
					serverStop = true;
					break;
				}
				
				else{
					System.out.println(objectName);
				}
				
				
			}
			
			System.out.println("Connected ended");
			in.close();
			out.close();
			clientSocket.close();
			
			if(serverStop) server.stopServer();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
