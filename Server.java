import java.net.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;


public class Server extends Thread{
	
	private ServerSocket serverSocket;
	private static Map<String, SocketAddress> users = new HashMap<String, SocketAddress>();
	
	//test take out later
	users.put("ali", (SocketAddress)"127.0.0.1");

	//returns string of class type, given an arbitrary object
	public static String getObjType(Object obj){
		return(obj.getClass().getTypeName());
	}
	
	//check if user is in the arraylist users, add user, (later on delete etc)
	public static boolean userFunctions(Object obj, SocketAddress address){
		//grab the username from the object
		String username = ((Username) obj).getUsername();
		
		//is the given username a part of the dictionary?
		//	if not, add it with its address
		System.out.println("Checking username...");
		if(!users.containsKey(username)){
			users.put(username, address);
			System.out.println("Creating new user...");
			return(true);
		}
		//if it is, just return true (FOR NOW)
		else{
			System.out.println("Created new user " + username + " address " + address.toString());
			return(true);
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
				
				System.out.println("Connected to " + server.getRemoteSocketAddress().toString());

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

				System.out.println("Got an object");
				
				if(getObjType(obj).equals("Username")){
					userFunctions(obj, server.getRemoteSocketAddress());
				}

				System.out.println(users.containsKey("ali"));
				
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
		
			
	}
	
	public static void main(String[] args){
		
		int port = 1500;
		try{
			Thread t = new Server(port);
			t.start();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}
	

