package application;

import java.io.Serializable;

public class LoginObject implements Serializable
{
	private String username;
	private String password;

	private static final long serialVersionUID = 1L;
	
	public LoginObject(String usr)
	{
		username = usr;
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
