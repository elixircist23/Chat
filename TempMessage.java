

import java.io.Serializable;

public class TempMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	String m;
	
	public TempMessage(String Message){
		m = Message;
	}
	
	public String getMessage(){
		return(m);
	}
}
