package model;

public class Taikhoan {
	private int idAccount;
	private String Username;
	private String Password;
	private String User;
	
	public Taikhoan() {
		super();
	}

	public Taikhoan(int idAccount, String username, String password,String user) {
		super();
		this.idAccount = idAccount;
		Username = username;
		Password = password;
		User = user;
	}
	
	public Taikhoan(int id) {
		this.idAccount = id;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
