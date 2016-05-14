import java.net.*;
import java.io.*;

public class Server extends Thread{

	private ServerSocket serverSocket;
	
	public Server(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}
	
	public void run(){
		
		while(true){
			
			try{
								
				Socket server = serverSocket.accept();
				System.out.println("Connected to " + server.getRemoteSocketAddress());
				
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
				System.out.println("Message from client: " + inFromClient.readLine());
												
				server.close();
				
			}catch(SocketTimeoutException s){
				System.out.println("Timed out");
				break;
			}catch(IOException e){
				e.printStackTrace();
				break;
			}
			
		}
		
			
	}
	
	public static void main(String[] args){
		
		int port = 1000;
		try{
			Thread t = new Server(port);
			t.start();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}
	

