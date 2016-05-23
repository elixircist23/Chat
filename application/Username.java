//need to differentiate between regular messages and information being sent
//have to define classes for important info.
import java.net.*;
import java.io.*;

public class Username implements Serializable {
	
	String username;
	
	public Username(String u){
		username = u;
	}
	
	public String getUsername(){
		return(username);
	}
	
}
