package com.boceto.bean;

public class UserSettings {

	public static final int VIEW_REQUESTER = 1;
	public static final int VIEW_EXPERT = 2;
	
	private int preferredMainView;

	public int getPreferredMainView() {
		return preferredMainView;
	}

	public void setPreferredMainView(int preferredMainView) {
		this.preferredMainView = preferredMainView;
	}

}
