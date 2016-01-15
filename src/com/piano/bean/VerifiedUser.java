package com.piano.bean;

public class VerifiedUser {

	private String userName;
	private boolean selected;
	
	public VerifiedUser(String userName, boolean selected) {
		super();
		this.userName = userName;
		this.selected = selected;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
