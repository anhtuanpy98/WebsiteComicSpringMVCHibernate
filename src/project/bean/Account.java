package project.bean;

import org.hibernate.validator.constraints.NotBlank;

public class Account {
	@NotBlank(message="UserName không được để trống!")
	private String userName;
	
	@NotBlank(message="Password không được để trống!")
	private String passWord;
	
	public Account() {
		
	}
	
	public Account(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
