package com.piano.orm;

import java.io.Serializable;

public abstract class AbstractUser implements Serializable{

	private int id;
	private String userName;
	private String password;
	private String school;
	private String birthdate;
	private String sex;
	
	public AbstractUser(int id, String userName, String password,String school,
			String birthdate, String sex) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.school = school;
		this.birthdate = birthdate;
		this.sex = sex;
	}
	
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
