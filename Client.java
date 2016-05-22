import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client implements Runnable{
	
	private static Socket clientSocket = null;
	private static InputStream in = null;
	private static ObjectInputStream objectInStream = null;
	private static OutputStream out = null;
	private static ObjectOutputStream objectOutStream = null;
	private static BufferedReader inputLine = null;
	private static boolean closed = false;
	
	
	public static void main(String[] args) {
		
		String hostname = "localhost";
		int port = 1500;
		
		
		try{
			clientSocket = new Socket(hostname, port);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			in = clientSocket.getInputStream();
			out = clientSocket.getOutputStream();
			objectInStream = new ObjectInputStream(in);
			objectOutStream = new ObjectOutputStream(out);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(clientSocket != null && out != null && in != null){
			
			try{
				
				new Thread(new Client()).start();
				while(!closed){
					objectOutStream.writeObject(new TempMessage(inputLine.toString()));
				}
				
				out.close();
				in.close();
				objectOutStream.close();
				objectInStream.close();
				clientSocket.close();
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
		
	public void run(){
		String line = null;
		try{
			line = objectInStream.readObject().getClass().getName();
		}catch(Exception e1){e1.printStackTrace();}
		
		try{
			while(line != null){
				System.out.println(line);
				if(line.equals("Quit")){
					break;
				}
			}
			closed = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
