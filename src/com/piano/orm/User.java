package com.piano.orm;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String password;
	private String school;
	private String birthday;
	private String gender;
	private Integer isVerified;
	private String lastLoginTime;
	
	public User() {
		super();
	}

	public User(Integer userId, String userName, String password,String school,
			String birthday, String gender, Integer isVerified,String lastLoginTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.school = school;
		this.birthday = birthday;
		this.gender = gender;
		this.isVerified = isVerified;
		this.lastLoginTime = lastLoginTime;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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


	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public Integer getIsVerified() {
		return isVerified;
	}


	public void setIsVerified(Integer isVerified) {
		this.isVerified = isVerified;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}