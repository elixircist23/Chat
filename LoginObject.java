import java.io.Serializable;

public class LoginObject implements Serializable
{
	private String username;
	private String password;

	private static final long serialVersionUID = 1L;
	
	public LoginObject(String usr, String pass)
	{
		username = usr;
		password = pass;
	}
	
	public String toString()
	{
		return username;
	}

	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
}
