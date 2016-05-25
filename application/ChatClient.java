import java.net.*;
import java.io.*;
import java.util.Scanner;

import java.awt.*;
import java.awt.event.*;

public class ChatClient implements Runnable{
	
	public static Socket clientSocket = null;
	public static DataOutputStream out = null;
	public static ObjectOutputStream oos = null;
	public static DataInputStream in = null;
	public static ObjectInputStream ois = null;
	public static Scanner stdin = new Scanner(System.in);
	public static boolean closed = false;
	

	public static void main(String[] args) {
		
		try{
			
			//creating socket/object streams
			clientSocket = new Socket("localhost", 1500);
			out = new DataOutputStream(clientSocket.getOutputStream());
			oos = new ObjectOutputStream(out);
			in = new DataInputStream(clientSocket.getInputStream());
			ois = new ObjectInputStream(in);
			
			System.out.println("Created Streams");
			
		}catch(Exception e){e.printStackTrace();}
		
		System.out.println("Connected to host");
		
		//if everything connected successfully move on
		if(clientSocket != null && out != null && in != null){
			
			try{
				
				//create and start a new ChatClient thread to read input sent from the server
				new Thread(new ChatClient()).start();
				
				//while the connection is still open...
				//USE THIS TO WRITE STUFF
				while(!closed){
				
					//accept user input through console
					String input = stdin.nextLine();
					
					//if user writes 'quit', create a quit object and send it to the server,
						//closed is now true, and break from the while loop
					if(input.equals("quit")){
						System.out.println("Closing connections...");
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
				
				//cleaning up connections
				out.close();
				oos.close();
				in.close();
				ois.close();
				clientSocket.close();
				System.exit(0);
			
			}catch(Exception e){e.printStackTrace();}
			
		}
				
	}
	
	//the run method for the thread USE THIS FOR READING STUFF
	public void run(){
		
		Object responseLine;
		
		try{
						
			//while we are getting input
			while((responseLine = ois.readObject())!= null){
				//grab the name of the object
				if(responseLine.getClass().getName().equals("TempMessage")){
					TempMessage m = (TempMessage) responseLine;
					System.out.println(m.getMessage());
 				}
				
				if(responseLine.getClass().getName().equals("Quit")){
					break;
				}
			}
			
			
		}catch(Exception e){e.printStackTrace();}
		
	}

}
