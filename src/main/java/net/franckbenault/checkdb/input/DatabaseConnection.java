package net.franckbenault.checkdb.input;

public class DatabaseConnection {

	private String url;
	private String user;
	private String password;
	
	public DatabaseConnection(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}
}
