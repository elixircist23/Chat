import java.net.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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
}

class ClientWorker extends Thread{
	Server server;
	Socket clientSocket;
	BufferedReader in = null;
	PrintStream out = null;
	
	public ClientWorker(Socket clientSocket, Server server){
		this.clientSocket = clientSocket;
		this.server = server;
		
		System.out.println("Connected to address: " + clientSocket.getInetAddress().getHostAddress());
		
		try{
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintStream(clientSocket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		String line;
		
		try{
			boolean serverStop = false;
			
			while(true){
				line = in.readLine();
				System.out.println("Client: " + line);
				int n = Integer.parseInt(line);
				
				if(n == -1){
					serverStop = true;
					break;
				}
				
				if(n == 0) break;
				out.println("" + n*n);
			}
			
			System.out.println("Connected ended");
			in.close();
			out.close();
			clientSocket.close();
			
			if(serverStop) server.stopServer();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
