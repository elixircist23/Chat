import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatClient implements Runnable{
	
	public static Socket clientSocket = null;
	public static PrintStream out = null;
	public static ObjectOutputStream oos = null;
	public static DataInputStream in = null;
	public static ObjectInputStream ois = null;
	public static Scanner stdin = new Scanner(System.in);
	public static boolean closed = false;

	public static void main(String[] args) {
		
		try{
			
			//creating socket/object streams
			clientSocket = new Socket("localhost", 1500);
			out = new PrintStream(clientSocket.getOutputStream());
			oos = new ObjectOutputStream(out);
			in = new DataInputStream(clientSocket.getInputStream());
			ois = new ObjectInputStream(in);
			
			System.out.println("Created Streams");
			
		}catch(Exception e){e.printStackTrace();}
		
		System.out.println("Connected to host");
		
		//if everything connected successfully move on
		if(clientSocket != null && out != null && in != null){
			
			try{
				
				//create and start a new ChatClient thread to 
				new Thread(new ChatClient()).start();
				
				while(!closed){
				
					String input = stdin.next();
				
					if(input.equals("quit")){
						Quit q = new Quit();
						oos.writeObject(q);
						closed = true;
						break;
					}
					else{
						TempMessage msg = new TempMessage(input);
						oos.writeObject(msg);
					}
									
				}
				
				out.close();
				oos.close();
				in.close();
				ois.close();
				clientSocket.close();
				System.exit(0);
			
			}catch(Exception e){e.printStackTrace();}
			
		}
				
	}
	
	public void run(){
		
		Object responseLine;
		
		try{
						
			while((responseLine = ois.readObject())!= null){
				System.out.println(responseLine.getClass().getName());
				if(responseLine.getClass().getName().equals("Quit")){
					break;
				}
			}
			
		}catch(Exception e){e.printStackTrace();}
		
	}

}
