package model;

/*ユーザー情報を格納するクラス*/
import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private String email;
	private String pass;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return this.pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public User() {}
	
	public User(String email, String pass) {
		this.email = email;
		this.pass = pass;
	}
	
	public User(String name, String email, String pass) {
		this(email, pass);
		this.name = name;
	}
	
	
}
