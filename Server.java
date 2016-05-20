import java.net.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;


public class Server extends Thread{
	
	private ServerSocket serverSocket;
	private Map<String, Inet4Address> users = new HashMap<String, Inet4Address>();
	
	//returns string of class type, given an arbitrary object
	public String getObj(Object obj){
		return(obj.getClass().getTypeName());
	}
	
	//check if user is in the arraylist users, add user, (later on delete etc)
	public void userFunctions(Object obj, Inet4Address address){
		//grab the username from the object
		String username = ((Username) obj).getUsername();
		
		if(!users.containsKey(username)){
			users.put(username, address);
		}
	}
	
	public Server(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(100000);
	}
	
	//main method, to run socket and keep it alive
	public void run(){
		
		while(true){
			
			try{
				//make a socket and accept connections from clients
				Socket server = serverSocket.accept();
				//create object to later on help us distinguish received objects
				Object obj = null;
				
				//input received will always be an object
				InputStream inputStream = server.getInputStream();
				ObjectInputStream objectInStream = new ObjectInputStream(inputStream);
				
				//try to read objects type
				try{
					obj = objectInStream.readObject();
				}
				catch(ClassNotFoundException c){
					c.printStackTrace();
				}
				
				System.out.println(((Username) obj).getUsername());
				
				
				
				
				//if server.get = username o, usernamelogger() etccc...
				
			}catch(IOException e){
				e.printStackTrace();
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
	

