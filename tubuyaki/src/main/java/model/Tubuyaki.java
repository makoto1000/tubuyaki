package model;

import java.io.Serializable;

public class Tubuyaki implements Serializable{
	private int no;
	private String userName;
	private String userId;
	private String text;
	private int likes;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public Tubuyaki() {};
	
	public Tubuyaki(String userName, String text, int likes) {
		this.userName = userName;
		this.text = text;
		this.likes = likes;
	}
}

