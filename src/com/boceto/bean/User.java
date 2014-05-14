package com.boceto.bean;

public class User {

	private int id = -1;
	private String name = "";
	private String email = "";
	
	private boolean isExpert = false;
	private boolean isRequester = false;
	
	private boolean isFirstTimeExpert = false; //indica si se acaba de registrar como experto
	
	private UserSettings settings;
	
	
	public boolean isFirstTimeExpert() {
		return isFirstTimeExpert;
	}
	public void setFirstTimeExpert(boolean isFirstTimeExpert) {
		this.isFirstTimeExpert = isFirstTimeExpert;
	}
	public UserSettings getSettings() {
		return settings;
	}
	public void setSettings(UserSettings settings) {
		this.settings = settings;
	}
	public boolean isExpert() {
		return isExpert;
	}
	public void setExpert(boolean isExpert) {
		this.isExpert = isExpert;
	}
	public boolean isRequester() {
		return isRequester;
	}
	public void setRequester(boolean isRequester) {
		this.isRequester = isRequester;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
